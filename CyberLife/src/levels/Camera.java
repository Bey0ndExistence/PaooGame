package levels;
import Entities.Player;

public class Camera {
    private int x;
    private int y;
    private int width;
    private int height;
    private Player target;

    public Camera(int x, int y, int width, int height, Player player) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.target = player;

    }

    public void update() {
        if (target != null) {
                x = (target.getCameraHitbox().x - width / 2);
        }
    }

    public int getX() {
        return x;
    }

}