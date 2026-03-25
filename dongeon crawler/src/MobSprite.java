import java.awt.Image;
import java.util.ArrayList;

public class MobSprite extends DynamicSprite {
    private final int damagePoints;
    private double d_parcouru = 0;
    private final double d_a_parcourir = 153;

    public MobSprite(Image image, double x, double y, double width, double height, int damagePoints) {
        super(image, x, y, width, height);
        this.damagePoints = damagePoints;
        this.speed = 40;
        this.direction = Direction.EAST;
    }

    @Override
    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment) && d_parcouru < d_a_parcourir) {
            move();
            d_parcouru += speed;
        } else {
            d_parcouru = 0;
            switch (this.direction) {
                case EAST -> this.direction = Direction.SOUTH;
                case SOUTH -> this.direction = Direction.WEST;
                case WEST -> this.direction = Direction.NORTH;
                case NORTH -> this.direction = Direction.EAST;
            }
        }
    }
}