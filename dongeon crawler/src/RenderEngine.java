import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class RenderEngine extends JPanel implements Engine {

    private List<Displayable> renderList;

    public RenderEngine() {
        this.renderList = new ArrayList<>();
    }

    @Override
    public void update() {
    }

    @Override
    protected void paintComponent(Graphics g) {
    }
}