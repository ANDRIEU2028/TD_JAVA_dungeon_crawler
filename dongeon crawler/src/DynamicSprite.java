import java.awt.*;

public class DynamicSprite extends SolidSprite {
    protected boolean isWalking = true;
    protected double speed = 5;
    protected final int spriteSheetNumberOfColumn = 10;
    protected int timeBetweenFrame = 200;
    protected Direction direction = Direction.NORTH;

    public DynamicSprite(Image image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    @Override
    public void draw(Graphics g) {
        int index = (int) ((System.currentTimeMillis() / timeBetweenFrame) % spriteSheetNumberOfColumn);
        int attitude = direction.getFrameLineNumber();
        int x1 = index * (int) width;
        int y1 = attitude * (int) height;
        int x2 = (index + 1) * (int) width;
        int y2 = (attitude + 1) * (int) height;

        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                x1, y1, x2, y2, null);
    }
}