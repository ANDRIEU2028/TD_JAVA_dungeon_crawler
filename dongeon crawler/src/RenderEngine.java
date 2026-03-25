import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class RenderEngine extends JPanel implements Engine {
    private List<Displayable> renderList;
    private DynamicSprite hero;
    private long time_start=System.currentTimeMillis();
    private int level;
    private GameEngine gameEngine;



    public RenderEngine() {
        this.renderList = new ArrayList<>();
    }

    public void setRenderList(List<Displayable> renderList) {
        this.renderList = renderList;
    }
    public void setHero(DynamicSprite hero) {
        this.hero = hero;
    }
    public void setLevel(int level){
        this.level=level;
    }
    public void setGameEngine(GameEngine gameEngine){
        this.gameEngine=gameEngine;
    }

    public void addToRenderList(Displayable displayable) {
        this.renderList.add(displayable);
    }

    public void resetRenderList() {
        this.renderList.clear();
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable d : renderList) {
            d.draw(g);
        }
        if (gameEngine !=null && !gameEngine.getStart()) {
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial Rounded", Font.BOLD, 50)); // Police
            g.drawString("GAME" , 120, 245);
            g.drawString("START" , 115, 295);
            g.setColor(Color.CYAN);
            g.setFont(new Font("Arial Rounded", Font.BOLD, 20));
            g.drawString("appuyez sur entrée" , 105, 345);
        }
        else if (hero != null) {
            if(this.level==1){
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 400, 20);
                g.setFont(new Font("Arial Rounded", Font.BOLD, 10));
                g.setColor(Color.WHITE);
                g.drawString("Vie : " + hero.getHP(), 20, 15);
                g.drawString("Time : " + (int)((System.currentTimeMillis() - time_start) / 1000) + " s",
                        300, 15);
                if(hero.getGameOver()){
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, 400, 600);
                    g.setColor(Color.RED);
                    g.setFont(new Font("Arial Rounded", Font.BOLD, 50)); // Police
                    g.drawString("GAME" , 120, 275);
                    g.drawString("OVER" , 125, 325);
                }
            }
            if(this.level==2){
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 790, 20);
                g.setFont(new Font("Arial Rounded", Font.BOLD, 10));
                g.setColor(Color.WHITE);
                g.drawString("Vie : " + hero.getHP(), 20, 15);
                g.drawString("Time : " + (int)((System.currentTimeMillis() - time_start) / 1000) + " s",
                        700, 15);
                if(hero.getGameOver()){
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, 790, 600);
                    g.setColor(Color.RED);
                    g.setFont(new Font("Arial Rounded", Font.BOLD, 50)); // Police
                    g.drawString("GAME" , 320, 275);
                    g.drawString("OVER" , 325, 325);
                }
            }

        }

    }

}