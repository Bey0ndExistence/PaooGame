package Entities;
import java.awt.*;

public abstract class Entity {

    protected float x,y;
    protected int width, height;
    protected Rectangle hitbox;

    public Entity(float x, float y, int width, int height){
        this.x= x;
        this.y =y;
        this.width = width;
        this.height = height;

    }

    public float getX(){
        return x;
    }

    public   void drawHitbox(Graphics g){
        //for debugging the hitbox
        g.setColor(Color.PINK);
        g.drawRect(hitbox.x,hitbox.y,hitbox.width,hitbox.height);

    }

    public void initHitbox(float x, float y, int width, int height){
        hitbox = new Rectangle((int) x, (int) y,width,height);
    }

    public Rectangle getHitbox(){
        return  hitbox;
    }


}
