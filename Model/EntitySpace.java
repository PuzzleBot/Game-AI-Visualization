import java.util.ArrayList;
import java.util.Random;

public class EntitySpace{
    public static final int ENTITY_SIZE = 5;

    protected ArrayList<Entity> aiEntities;
    protected Entity userEntity;
    protected EntityController controller;

    protected Random rng;

    protected int leftBoundary;
    protected int rightBoundary;
    protected int topBoundary;
    protected int bottomBoundary;

    public EntitySpace(){
        aiEntities = new ArrayList<Entity>();
        userEntity = new Entity(0, 0);
        rng = new Random();

        leftBoundary = 0;
        rightBoundary = 800;
        topBoundary = 0;
        bottomBoundary = 600;
    }

    public int getHorizontalSize(){
        return rightBoundary;
    }

    public int getVerticalSize(){
        return bottomBoundary;
    }

    /*Sets an entity's position, but ensures its within the boundaries*/
    private void setEntityPosition(Entity entity, Vector2D pos){
        entity.position.x = pos.x;
        entity.position.y = pos.y;

        if(entity.position.x < leftBoundary){
            entity.position.x = leftBoundary;
        }
        else if(entity.position.x > rightBoundary){
            entity.position.x = rightBoundary;
        }

        if(entity.position.y < topBoundary){
            entity.position.y = topBoundary;
        }
        else if(entity.position.y > bottomBoundary){
            entity.position.y = bottomBoundary;
        }
    }

    public void setUserPosition(Vector2D pos){
        setEntityPosition(userEntity, pos);
    }

    public Vector2D getUserPosition(){
        return userEntity.position;
    }

    public void addAiEntity(){
        /*Add an entity with no velocity or acceleration, at a random position*/
        aiEntities.add(new Entity(rng.nextInt(rightBoundary), rng.nextInt(bottomBoundary)));
    }

    public void setAiPosition(int index, Vector2D pos){
        setEntityPosition(aiEntities.get(index), pos);
    }

    public Vector2D getAiPosition(int index){
        return aiEntities.get(index).position;
    }

    public void updateAiPosition(int index){
        Entity targetEntity = aiEntities.get(index);
        Vector2D newPosition = new Vector2D(targetEntity.position.x + targetEntity.velocity.x, targetEntity.position.y + targetEntity.velocity.y);
        setEntityPosition(targetEntity, newPosition);

        targetEntity.velocity.x = targetEntity.velocity.x + targetEntity.acceleration.x;
        targetEntity.velocity.y = targetEntity.velocity.y + targetEntity.acceleration.y;
    }

    public void setAiVelocity(int index, Vector2D vel){
        aiEntities.get(index).velocity.x = vel.x;
        aiEntities.get(index).velocity.y = vel.y;
    }

    public void setAiAcceleration(int index, Vector2D accel){
        aiEntities.get(index).acceleration.x = accel.x;
        aiEntities.get(index).acceleration.y = accel.y;
    }

    public ArrayList<Entity> getAiList(){
        return aiEntities;
    }

    public void bindController(EntityController controller){
        this.controller = controller;
        controller.assignSpace(this);
    }
}
