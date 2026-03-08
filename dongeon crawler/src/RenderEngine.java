import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class RenderEngine extends JPanel implements Engine {

    private List<Displayable> renderList;

    public RenderEngine() {
        this.renderList = new ArrayList<>();
    }
    public void setRenderList(List<Displayable> renderList) {
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable) {
        this.renderList.add(displayable);
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
    }
}