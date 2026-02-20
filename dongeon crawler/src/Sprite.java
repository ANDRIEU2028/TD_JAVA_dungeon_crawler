import java.awt.Graphics;
import java.awt.Image;

public class Sprite implements Displayable {
    protected Image image;
    protected double x;
    protected double y;
    protected double weight;
    protected double height;

    public Sprite(Image image, double x, double y, double weight, double height){
        this.image = image;
        this.x =  x;
        this.y = y;
        this.weight = weight;
        this.height = height;
    }

    @Override
    public void draw(Graphics g){
        g.drawImage (image, (int)x, (int)y, (int)weight, (int)height,null);
    }
}



