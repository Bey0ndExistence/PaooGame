package Game;

import Inputs.KeyboardInputs;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;




public class GamePanel extends JPanel {


    Graphics2D bg=null;

    private KeyboardInputs keyboardInputs;

    private Game game;
    public GamePanel(Game game) throws IOException {

        this.game=game;
        keyboardInputs = new KeyboardInputs(this);
        addKeyListener(keyboardInputs);
        setPanelSize();

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1920,768);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        bg = (Graphics2D) g;

        game.render(g,bg);
    }
    public void startKeyboardInputsThread() {
        keyboardInputs.start();
    }

    public Game getGame(){
        return this.game;
    }

}
