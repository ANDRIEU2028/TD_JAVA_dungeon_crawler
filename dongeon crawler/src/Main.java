import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    Level actual_level=Level.LEVEL1;
    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs");
        Level[] levels=Level.values();

        displayZoneFrame.setSize(actual_level.getWindowwidht(),actual_level.getWindowheight());
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")),
                200,300,48,50);

        MobSprite mob = new MobSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")),400,200,48,50,2);

        renderEngine = new RenderEngine();
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level = new Playground(actual_level.getPath());

        renderEngine.setGameEngine(gameEngine);
        renderEngine.setLevel(actual_level);
        ArrayList<Displayable> levelSprites = level.getSpriteList();
        for (Displayable d : levelSprites) {
            renderEngine.addToRenderList(d);
        }

        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getEnvironment());
        renderEngine.setHero(hero);



        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> {
            if(!gameEngine.getStart())
                return;

            if (hero.getGameOver()) {
                renderTimer.stop();
                gameTimer.stop();
                return;
            }
            physicEngine.update();

            if (hero.getPassNextLevel()) {
                Level next_level=levels[actual_level.getFrameLineNumber()+1];
                renderEngine.resetRenderList();
                actual_level=levels[next_level.getFrameLineNumber()];

                displayZoneFrame.setSize(next_level.getWindowwidht(),next_level.getWindowheight());
                displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                renderEngine.setLevel(next_level);
                Playground new_level = new Playground(next_level.getPath());
                for (Displayable d : new_level.getSpriteList()) {
                    renderEngine.addToRenderList(d);
                }
                ArrayList<Sprite> environment = new_level.getEnvironment();
                renderEngine.addToRenderList(hero);
                if(next_level==Level.LEVEL2){
                    renderEngine.addToRenderList(mob);
                    physicEngine.addToMovingSpriteList(mob);
                    environment.add(mob);
                }

                physicEngine.setEnvironment(environment);
                physicEngine.setEnvironment(new_level.getEnvironment());
                hero.x = hero.x - 20;
                hero.setPassNextLevel(false);
            }
            if (hero.getPassPastLevel()) {
                Level next_level=levels[actual_level.getFrameLineNumber()-1];
                renderEngine.resetRenderList();
                actual_level=levels[next_level.getFrameLineNumber()];

                displayZoneFrame.setSize(next_level.getWindowwidht(),next_level.getWindowheight());
                displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                renderEngine.setLevel(next_level);
                Playground new_level = new Playground(next_level.getPath());
                for (Displayable d : new_level.getSpriteList()) {
                    renderEngine.addToRenderList(d);
                }
                renderEngine.addToRenderList(hero);
                physicEngine.setEnvironment(new_level.getEnvironment());
                hero.x = hero.x - 20 ;

                hero.setPassPastLevel(false);
            }
        });

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();
        displayZoneFrame.addKeyListener(gameEngine);

    }

    public static void main (String[] args) throws Exception {
        Main main = new Main();
    }
}
