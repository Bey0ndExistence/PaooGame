package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Guns extends TilesHitBox{

    public boolean isVisible = true;
    public Guns(float x, float y, int width, int height, BufferedImage image) {
        super(x, y, width, height, image);
    }

    public void setHitboxNull(){
        this.hitbox.x =0;
        this.hitbox.y =-200;
        this.width =0;
        this.height =0;
    }
    public void drawGun(Graphics g) {
        // check if isVisible is true before drawing the Gun instance on the screen
        if (isVisible) {
            g.drawImage(image,(int) getHitbox_X(), (int) y, null);
        }
    }
}
