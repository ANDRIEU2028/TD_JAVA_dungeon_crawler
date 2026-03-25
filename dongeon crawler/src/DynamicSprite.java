import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;

public class DynamicSprite extends SolidSprite {
    protected boolean isWalking = true;
    protected double speed = 0;
    protected final int spriteSheetNumberOfColumn = 10;
    protected int timeBetweenFrame = 200;
    protected Direction direction = Direction.EAST;
    private boolean passnextLevel = false;
    private boolean passpastLevel = false;
    private int hp =5;
    private long lastDamageTime = 0;
    private final long invincibilityDelay = 2000;
    private boolean gameover = false;

    public DynamicSprite(Image image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
    }


    private void move(){
        switch (direction){
            case NORTH -> {
                this.y-=speed;
            }
            case EAST -> {
                this.x+=speed;
            }
            case WEST -> {
                this.x-=speed;
            }
            case SOUTH -> {
                this.y+=speed;
            }
        }
    }
    private void passnextlevel(){
        this.passnextLevel=true;
    }
    private void passpastlevel(){
        this.passpastLevel=true;
    }
    private void damage(){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDamageTime > invincibilityDelay) {
            this.hp--;
            this.lastDamageTime = currentTime;
            if (this.hp<= 0) {
                this.gameover=true;
            }
        }
    }
    private void heal(){
        if (this.hp< 5) {
            this.hp++;;
        }

    }



    public boolean getPassNextLevel(){
        return passnextLevel;
    }
    public boolean getPassPastLevel(){
        return passpastLevel;
    }
    public int getHP() {
        return hp;
    }
    public boolean getGameOver(){
        return gameover;
    }
    private Rectangle2D.Double getHitBox(){
        double nextX = this.x;
        double nextY = this.y;

        switch (direction) {
            case NORTH -> nextY -= speed;
            case SOUTH -> nextY += speed;
            case WEST  -> nextX -= speed;
            case EAST  -> nextX += speed;
        }

        Rectangle2D.Double hitBox = new Rectangle2D.Double(nextX+15, nextY+25, this.width-25,
                this.height-30);

        return hitBox;
    }


    public void setSpeed(double speed){
        this.speed=speed;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void setPassNextLevel(boolean passnextlevel){
        this.passnextLevel = passnextlevel;
    }
    public void setPassPastLevel(boolean passpastlevel){
        this.passpastLevel = passpastlevel;
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double hitBox =getHitBox();
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite && sprite != this) {
                Rectangle2D.Double obstacleRect = new Rectangle2D.Double(sprite.x,
                        sprite.y, sprite.width, sprite.height);
                if (hitBox.intersects(obstacleRect)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isNextStage(ArrayList<Sprite> environment){
        Rectangle2D.Double hitBox =getHitBox();
        for (Sprite sprite : environment) {
            if (sprite instanceof NextStageSprite && sprite != this) {
                Rectangle2D.Double scale = new Rectangle2D.Double(sprite.x,
                        sprite.y, sprite.width, sprite.height);
                if (hitBox.intersects(scale)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isPastStage(ArrayList<Sprite> environment) {
        Rectangle2D.Double hitBox = getHitBox();
        for (Sprite sprite : environment) {
            if (sprite instanceof PastStageSprite && sprite != this) {
                Rectangle2D.Double scale = new Rectangle2D.Double(sprite.x,
                        sprite.y, sprite.width, sprite.height);
                if (hitBox.intersects(scale)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isTrap(ArrayList<Sprite> environment){
        Rectangle2D.Double hitBox =getHitBox();
        for (Sprite sprite : environment) {
            if (sprite instanceof DamageSprite && sprite != this) {
                Rectangle2D.Double trap = new Rectangle2D.Double(sprite.x,
                        sprite.y, sprite.width, sprite.height);
                if (hitBox.intersects(trap)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isHealing(ArrayList<Sprite> environment) {
        Rectangle2D.Double hitBox =getHitBox();
        for (Sprite sprite : environment) {
            if (sprite instanceof HealSprite && sprite != this) {
                Rectangle2D.Double soin = new Rectangle2D.Double(sprite.x,
                        sprite.y, sprite.width, sprite.height);
                if (hitBox.intersects(soin)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void moveIfPossible(ArrayList<Sprite> environment){
        if (isMovingPossible(environment)){
            move();
        }
        if (isNextStage(environment)){
            passnextlevel();
        }
        if(isTrap(environment)){
            damage();
        }
        if(isHealing(environment)){
            heal();
        }
        if (isPastStage(environment)){
            passpastlevel();
        }

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