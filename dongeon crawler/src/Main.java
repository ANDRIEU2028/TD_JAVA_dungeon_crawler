import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

public class Main {

    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    GameEngine GameEngine;

    public Main() throws Exception{
        displayZoneFrame= new JFrame("Java Labs");
        displayZoneFrame.setSize(400,600);
        displayZoneFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        renderEngine = new RenderEngine();
        Sprite arbre = new Sprite(ImageIO.read(new File("./img/tree.png")),200,300,64,64);

        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png"))
                ,200,300,48,50);

        GameEngine = new GameEngine(hero);
        renderEngine.addToRenderList(arbre);
        renderEngine.addToRenderList(hero);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer GameEngine = new Timer(50,(time)-> GameEngine.update());
        renderTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.addKeyListener(GameEngine);
        displayZoneFrame.setVisible(true);

    }
    public static void main (String[] args) throws Exception {
        Main main = new Main();
    }
}
