import java.awt.Image;

public class MobSprite extends SolidSprite {
    private final int damagePoints;

    public MobSprite(Image image, double x, double y, double width, double height, int damagePoints) {
        super(image, x, y, width, height);
        this.damagePoints = damagePoints;
    }

    public int getDamagePoints() {
        return damagePoints;
    }
    public void attack(DynamicSprite hero) {
        hero.damage();
    }
}