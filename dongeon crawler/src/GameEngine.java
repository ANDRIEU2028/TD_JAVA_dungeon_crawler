import java.awt.event.*;
import java.net.NoRouteToHostException;

import static javax.swing.SwingConstants.NORTH;
import static javax.swing.SwingConstants.SOUTH;

public class GameEngine implements Engine,KeyListener{
    private final DynamicSprite hero;

    public GameEngine(DynamicSprite hero){
        this.hero = hero;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setDirection(Direction.NORTH);
            case KeyEvent.VK_DOWN :
                hero.setDirection(Direction.SOUTH);
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void update() {

    }
}
