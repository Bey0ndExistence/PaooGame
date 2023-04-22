package Inputs;

import Game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener, Runnable {
    private GamePanel gamePanel;
    private Thread thread;
    private boolean running;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        thread = new Thread(this);
    }

    public void start() {
        running = true;
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(true);
                System.out.println("am apasat W");
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(true);
                gamePanel.getGame().getPlayer().setGoingLeft();
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDucking();
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(true);
                gamePanel.getGame().getPlayer().setGoingRight();
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getPlayer().setShooting();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getPlayer().setNonShooting();
                break;
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}