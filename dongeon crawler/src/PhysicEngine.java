import java.util.ArrayList;

public class PhysicEngine implements Engine{
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList <Sprite> environment;

    public PhysicEngine() {
        this.movingSpriteList = new ArrayList<>();
        this.environment = new ArrayList<>();
    }
    public void addToMovingSpriteList(DynamicSprite sprite) {
        this.movingSpriteList.add(sprite);
    }

    public void setEnvironment(ArrayList<Sprite> environment){
        this.environment=environment;
    }
    @Override
    public void update() {
        for(DynamicSprite dynamicSprite : movingSpriteList){
            dynamicSprite.moveIfPossible(environment);
        }
    }
}

