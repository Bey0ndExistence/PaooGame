package Entities;

import Utils.LoadSave;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


import static Utils.Constants.PlayerConstants.*;


public class Player extends Entity{

    private BufferedImage[][] animationSheet;
    private BufferedImage img;
    private int aniTick=0,aniIndex=0;
    private final int aniSpeed=27;
    private int playerAction = IDLE;
    private boolean moving =false;
    private boolean duck = false;

    private boolean shooting = false;
    private long cooldownStart = 0;
    private boolean cooldown = false;


    private boolean left, up, right ;

    private boolean inAir = false;

    private boolean goingLeft = false, goingRight = false;

    private float xspeed, yspeed;

    private Rectangle cameraHitbox;

    private boolean secondGun=false;

    private int LastCameraX;

    public Player(float x, float y,int width, int height) throws IOException {
        super(x, y,width,height);
        loadAnimations();
        initHitbox(x+50 ,y  , width/2, height);
        cameraHitbox = new Rectangle(1200,200,200,200);
    }

    public Rectangle getCameraHitbox(){
        return cameraHitbox;
    }

    public void update(TilesHitBox[] wall){
        updatePos(wall);
        UpdateAnimation();
        setAnimation();
    }
    public void render(Graphics g){
        g.drawImage(this.animationSheet[playerAction][this.aniIndex],(int)x,(int)y,width, height, null);
        //drawHitbox(g);
        //g.drawRect(hitbox.x,hitbox.y,width,height);
    }



    public void loadAnimations() throws IOException{

         this.img =LoadSave.getInstance().getAtlas(LoadSave.PLAYER_ATLAS);

            this.animationSheet= new BufferedImage[14][8];
            for(int i=0; i<animationSheet.length;i++)
                for(int j=0; j< animationSheet[i].length;j++) {
                    this.animationSheet[i][j] = this.img.getSubimage(j * 240, i * 240, 240, 240);

                }
    }

    public void UpdateAnimation() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            if (!duck) {
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(playerAction)) {
                    aniIndex = 0;
                }
            } else {
                if (cooldown) {
                    // pause the animation on the last frame

                    if (aniIndex >= GetSpriteAmount(DUCK) - 1) {
                        aniIndex = GetSpriteAmount(DUCK) - 1;
                    }
                    else{
                        aniIndex++;
                    }
                    // check if cooldown is over
                    if (System.currentTimeMillis() - cooldownStart >= 500) {
                        cooldown = false;
                    }
                } else {
                    aniIndex++;
                    if (aniIndex >= GetSpriteAmount(DUCK)) {
                        aniIndex = 0;
                        this.duck = false;
                    }
                }
            }
        }
    }


    public void setDucking(){
        if (!cooldown) {
            this.duck = true;
            this.shooting = false;
            this.moving=false;
            this.cooldown = true;
            this.cooldownStart = System.currentTimeMillis();
        }
    }

    public void setShooting(){
        this.shooting =true;
    }

    public void setNonShooting(){
        this.shooting =false;
    }

    private void updatePos(TilesHitBox[] walls){
        this.moving = false;

        if(left && right || !left && !right) xspeed *= 0.8;
        else if (left && !right && !inAir) {
            this.moving = true;
            xspeed--;
        }
        else if (right && !left && !inAir) {
            this.moving = true;
            xspeed++;

        }

        if(xspeed > 0 && xspeed < 0.25) xspeed =0;
        if(xspeed < 0 && xspeed > -0.25) xspeed =0;

        if(xspeed > 1) xspeed = 1;
        if(xspeed < -1) xspeed = -1;

        if(up){
            this.moving = false;
            this.inAir = true;
            hitbox.y++;

            for(TilesHitBox wall: walls) {
                if (wall.getHitbox().intersects(getHitbox())) {
                    yspeed = -5;

                }
            }
            hitbox.y--;
        }

        yspeed += 0.05;

        LastCameraX = cameraHitbox.x;
        // Horizontal Collision
        boolean ok = true;
        if (xspeed > 0) { // move to the right
            for (TilesHitBox wall : walls) {
                if (hitbox.x + hitbox.width + xspeed >= wall.getHitbox().x &&
                        hitbox.x + hitbox.width <= wall.getHitbox().x &&
                        hitbox.y < wall.getHitbox().y + wall.getHitbox().height &&
                        hitbox.y + hitbox.height > wall.getHitbox().y) {
                            if(wall instanceof Guns ) {
                                ((Guns) wall).isVisible = false;
                                secondGun =true;
                                ((Guns) wall).setHitboxNull();
                            }
                            else
                            {
                                ok = false;
                                break;
                            }
                }
            }
            if (ok) {
                cameraHitbox.x += xspeed;
            }
        } else if (xspeed < 0) { // move to the left
            for (TilesHitBox wall : walls) {
                if (hitbox.x + xspeed <= wall.getHitbox().x + wall.getHitbox().width &&
                        hitbox.x >= wall.getHitbox().x + wall.getHitbox().width &&
                        hitbox.y < wall.getHitbox().y + wall.getHitbox().height &&
                        hitbox.y + hitbox.height > wall.getHitbox().y)  {
                    if(wall instanceof Guns ) {
                        ((Guns) wall).isVisible = false;
                        secondGun =true;
                        ((Guns) wall).setHitboxNull();
                    }
                    else
                    {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) {
                cameraHitbox.x += xspeed;
            }
        }

        hitbox.y += yspeed;
        for(TilesHitBox wall:walls) {
            if (getHitbox().intersects(wall.getHitbox())) {
                hitbox.y -= yspeed;
                while (!wall.getHitbox().intersects(getHitbox()))
                    hitbox.y += Math.signum(yspeed);
                hitbox.y -= Math.signum(yspeed);
                yspeed = 0;
                inAir = false;

                y = hitbox.y;
            }
        }

        y += yspeed;
        hitbox.y = (int) y ;

        if(y>800){
            playerReset();
        }
    }

    private void playerReset(){
        cameraHitbox.x = 1200;
        xspeed=0;
        yspeed=0;

    }


    public void setRight(boolean right) {
        this.right = right;
    }


    public void setUp(boolean up) {
        this.up = up;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }


    public void setGoingLeft(){
        goingLeft= true;
        goingRight =false;
    }

    public void setGoingRight(){
        goingRight = true;
        goingLeft = false;
    }

    private void setAnimation() {
        int previousPlayerAction = this.playerAction;
        if(moving) {
            if(goingRight)
                this.playerAction = RUN;
            else
                this.playerAction = RUN_LEFT;
        }
        else if(this.duck) {
            this.playerAction = DUCK;
        }
        else if(this.inAir){
                if((cameraHitbox.x - LastCameraX) > 0)
                    this.playerAction = JUMP;
                else if((cameraHitbox.x - LastCameraX) <0)
                    this.playerAction = JUMP_LEFT;
                else
                    this.playerAction =JUMP;
        }
        else if(this.shooting){
            if(!secondGun)
                this.playerAction = SHOOT1;
            else
                this.playerAction = SHOOTcombuster;
        }
        else {
            this.playerAction = IDLE;
        }

        if(previousPlayerAction != this.playerAction) {
            this.aniIndex = 0;
            this.aniTick =  0;
        }
    }


}
