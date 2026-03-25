import java.awt.event.*;
import java.net.NoRouteToHostException;

import static javax.swing.SwingConstants.NORTH;
import static javax.swing.SwingConstants.SOUTH;

public class GameEngine implements Engine,KeyListener{
    private final DynamicSprite hero;
    private boolean game_start = false;
    private double speed_i=5;
    private double stop=0;


    public GameEngine(DynamicSprite hero){
        this.hero = hero;
    }

    public boolean getStart(){
        return game_start;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setSpeed(speed_i);
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN :
                hero.setSpeed(speed_i);
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setSpeed(speed_i);
                hero.setDirection(Direction.EAST);
                break;
            case KeyEvent.VK_LEFT:
                hero.setSpeed(speed_i);
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_ENTER:
                game_start=true;
                break;
            case KeyEvent.VK_SPACE:
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setSpeed(stop);
                break;
            case KeyEvent.VK_DOWN :
                hero.setSpeed(stop);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setSpeed(stop);
                break;
            case KeyEvent.VK_LEFT:
                hero.setSpeed(stop);
                break;
            case KeyEvent.VK_SPACE:
                hero.setSpeed(speed_i);
                break;
        }

    }

    @Override
    public void update() {

    }
}
