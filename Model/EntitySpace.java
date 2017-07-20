import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class EntitySpace{
    public static final int ENTITY_SIZE = 10;

    protected ArrayList<Entity> aiEntities;
    protected Entity userEntity;
    protected EntityController controller;

    protected Random rng;

    protected int leftBoundary;
    protected int rightBoundary;
    protected int topBoundary;
    protected int bottomBoundary;

    public boolean boundariesEnabled;

    public EntitySpace(){
        aiEntities = new ArrayList<Entity>();
        userEntity = new Entity(200, 200);
        rng = new Random();

        leftBoundary = 0;
        rightBoundary = 800;
        topBoundary = 0;
        bottomBoundary = 600;

        boundariesEnabled = false;
    }

    public int getHorizontalSize(){
        return rightBoundary - leftBoundary;
    }

    public int getVerticalSize(){
        return bottomBoundary - topBoundary;
    }

    /*Sets an entity's position, but ensures its within the boundaries*/
    protected void setEntityPosition(Entity entity, Vector2D pos){
        entity.position.x = pos.x;
        entity.position.y = pos.y;

        if(boundariesEnabled == true){
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
    }

    public void setUserPosition(Vector2D pos){
        setEntityPosition(userEntity, pos);
    }

    public Vector2D getUserPosition(){
        return userEntity.position;
    }

    public Entity getUserEntity(){
        return userEntity;
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

    public void updateAllAiPositions(){
        Iterator<Entity> iterator = aiEntities.iterator();
        boolean iterationDone = false;

        while(iterationDone == false){
            try{
                Entity targetEntity = iterator.next();
                Vector2D newPosition = new Vector2D(targetEntity.position.x + targetEntity.velocity.x, targetEntity.position.y + targetEntity.velocity.y);
                setEntityPosition(targetEntity, newPosition);

                targetEntity.velocity.x = targetEntity.velocity.x + targetEntity.acceleration.x;
                targetEntity.velocity.y = targetEntity.velocity.y + targetEntity.acceleration.y;
            }
            catch (NoSuchElementException e){
                iterationDone = true;
            }
        }
    }

    public void setAiVelocity(int index, Vector2D vel){
        aiEntities.get(index).velocity.x = vel.x;
        aiEntities.get(index).velocity.y = vel.y;
    }

    public void setAiAcceleration(int index, Vector2D accel){
        aiEntities.get(index).acceleration.x = accel.x;
        aiEntities.get(index).acceleration.y = accel.y;
    }

    public Iterator<Entity> getAiListIterator(){
        return aiEntities.iterator();
    }

    public void bindController(EntityController controller){
        this.controller = controller;
        controller.assignSpace(this);
    }
}
