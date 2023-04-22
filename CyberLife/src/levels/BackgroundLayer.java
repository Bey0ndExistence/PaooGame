package levels;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class BackgroundLayer {
    private BufferedImage image;
    private double parallaxFactor;
    private int x;
    private int y;

    public BackgroundLayer(BufferedImage image, double parallaxFactor, float x, float y) {
        this.image = image;
        this.parallaxFactor = parallaxFactor;
        this.x = (int) x;
        this.y = (int) y;
    }

    public void update(Camera camera) {
        x = (int) (-camera.getX() * parallaxFactor);

    }

    public void draw(Graphics2D g2d) {
       // System.out.println("BackgroundLayer"+ "Drawing at x=" + x + ", y=" + y);
        g2d.drawImage(image, x, y, null);
    }
}
