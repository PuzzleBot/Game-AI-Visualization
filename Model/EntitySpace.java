import java.util.ArrayList;

public class EntitySpace{
    private ArrayList<Entity> aiEntities;
    private Entity userEntity;

    protected int leftBoundary;
    protected int rightBoundary;
    protected int topBoundary;
    protected int bottomBoundary;

    public EntitySpace(){
        aiEntities = new ArrayList<Entity>();
        userEntity = new Entity(0, 0);

        leftBoundary = 0;
        rightBoundary = 600;
        topBoundary = 0;
        bottomBoundary = 400;
    }

    public void setUserPosition(Vector2D pos){
        userEntity.position.x = pos.x;
        userEntity.position.y = pos.y;
    }

    public Vector2D getUserPosition(){
        return userEntity.position;
    }

    public void setAiPosition(int index, Vector2D pos){
        aiEntities.get(index).position.x = pos.x;
        aiEntities.get(index).position.y = pos.y;
    }

    public Vector2D getAiPosition(int index){
        return aiEntities.get(index).position;
    }

    public void updateAiPosition(int index){
        Entity targetEntity = aiEntities.get(index);
        targetEntity.position.x = targetEntity.position.x + targetEntity.velocity.x;
        targetEntity.position.y = targetEntity.position.y + targetEntity.velocity.y;

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
}
