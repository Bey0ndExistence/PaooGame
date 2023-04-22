package Game;

import Entities.Player;
import levels.LevelManager;

import java.awt.*;
import java.io.IOException;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private LevelManager levelManager;
    private Player player;
    private final int FPS_SET =144;

    private long lastCheck=0;
    private final int UPS_SET = 200;

    public Game() throws IOException {
        initClasses();
        gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(this.gamePanel);
        gamePanel.requestFocus();
        gamePanel.startKeyboardInputsThread();
        //System.out.println("Game running");
        startGameLoop();

    }

    private void initClasses() throws IOException {
        player = new Player(1100,200,240,240);
        levelManager= new LevelManager(this);


    }

    private void startGameLoop(){
        this.gameThread= new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update(levelManager.getHitBoxes());
        levelManager.update();

    }

    public void render(Graphics g, Graphics2D bg){
        levelManager.drawBG(bg);
        levelManager.drawTiles(g);
        player.render(g);

    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if(deltaF >= 1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
              // System.out.println("FPS: " + frames + "| UPS: " + updates);
                frames = 0;
                updates =0;

            }
        }
    }

    public Player getPlayer() {
        return player;
    }
}
