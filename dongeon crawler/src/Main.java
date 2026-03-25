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

    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")),
                200,300,48,50);

        renderEngine = new RenderEngine();
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level1 = new Playground("./data/level1.txt");
        Playground level2 = new Playground("./data/level2.txt");

        renderEngine.setGameEngine(gameEngine);
        renderEngine.setLevel(1);
        ArrayList<Displayable> levelSprites = level1.getSpriteList();
        for (Displayable d : levelSprites) {
            renderEngine.addToRenderList(d);
        }

        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level1.getEnvironment());
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

            if (hero.getHasFinishedLevel()) {
                renderEngine.resetRenderList();
                displayZoneFrame.setSize(790,600);
                displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                renderEngine.setLevel(2);
                for (Displayable d : level2.getSpriteList()) {
                    renderEngine.addToRenderList(d);
                }
                renderEngine.addToRenderList(hero);
                physicEngine.setEnvironment(level2.getEnvironment());

                hero.x = 150;
                hero.y = 400;
                hero.setHasFinishedLevel(false);
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
