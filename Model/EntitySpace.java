import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* A class which represents a 2D environment which entities exist in.
 * It also provides the user with an entity under their control, which
 * may be moved to the mouse's position by clicking.*/
public class EntitySpace{
    public static final int ENTITY_SIZE = 10;

    protected ArrayList<Entity> aiEntities;
    protected Entity userEntity;
    protected EntityController controller;
    protected Lock aiEntitiesLock;

    private int queuedEntityCountChange;
    private int trueEntityCount;

    protected Random rng;

    protected int leftBoundary;
    protected int rightBoundary;
    protected int topBoundary;
    protected int bottomBoundary;

    public boolean boundariesEnabled;

    public EntitySpace(){
        aiEntities = new ArrayList<Entity>();
        userEntity = new Entity(200, 200);
        aiEntitiesLock = new ReentrantLock();

        queuedEntityCountChange = 0;
        trueEntityCount = 0;

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
        if(aiEntitiesLock.tryLock()){
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
            aiEntitiesLock.unlock();
        }
    }

    public void setAiVelocity(int index, Vector2D vel){
        if(aiEntitiesLock.tryLock()){
            aiEntities.get(index).velocity.x = vel.x;
            aiEntities.get(index).velocity.y = vel.y;
            aiEntitiesLock.unlock();
        }
    }

    public void setAiAcceleration(int index, Vector2D accel){
        if(aiEntitiesLock.tryLock()){
            aiEntities.get(index).acceleration.x = accel.x;
            aiEntities.get(index).acceleration.y = accel.y;
            aiEntitiesLock.unlock();
        }
    }

    public Iterator<Entity> getAiListIterator(){
        return aiEntities.iterator();
    }

    public void bindController(EntityController controller){
        this.controller = controller;
        controller.assignSpace(this);
    }

    public int getNumberOfAiEntities(){
        return trueEntityCount;
    }

    public void addAiEntity(){
        /*Add an entity with no velocity or acceleration, at a random position*/
        if(aiEntitiesLock.tryLock()){
            aiEntities.add(new Entity(rng.nextInt(rightBoundary), rng.nextInt(bottomBoundary)));
            aiEntitiesLock.unlock();
        }
        else{
            /*If the ai entities array is locked, queue a change to try again later*/
            queuedEntityCountChange++;
        }

        trueEntityCount = aiEntities.size() + queuedEntityCountChange;
    }

    public void deleteOldestAiEntity() throws IndexOutOfBoundsException{
        /*Remove the oldest ai entity*/
        if(trueEntityCount <= 0){
            throw new IndexOutOfBoundsException();
        }

        if(aiEntitiesLock.tryLock()){
            aiEntities.remove(0);
            aiEntitiesLock.unlock();
        }
        else{
            /*If the ai entities array is locked, queue a change to try again later*/
            queuedEntityCountChange--;
        }

        trueEntityCount = aiEntities.size() + queuedEntityCountChange;
    }

    public void attemptQueuedCountChange(){
        /*Try to add/remove entities which weren't able to be added/removed when a change was requested*/
        if(queuedEntityCountChange > 0){
            int entitiesToAdd = queuedEntityCountChange;
            int i;
            for(i = 0; i < entitiesToAdd; i++){
                addAiEntity();
                queuedEntityCountChange--;
            }
        }
        else if(queuedEntityCountChange < 0){
            int entitiesToRemove = Math.abs(queuedEntityCountChange);
            int i;
            for(i = 0; i < entitiesToRemove; i++){
                deleteOldestAiEntity();
                queuedEntityCountChange++;
            }
        }
    }

    public void setNumberOfAiEntities(int numberOfAiEntities){
        if(numberOfAiEntities > aiEntities.size()){
            /*Number of ai entities wanted is greater than current number - add entities until
             *the number is reached*/
            int i;
            for(i = (numberOfAiEntities - aiEntities.size()); i > 0; i--){
                addAiEntity();
            }
        }
        else if(numberOfAiEntities < aiEntities.size()){
            /*Number of ai entities wanted is less than current number - remove entities until
             *the number is reached*/
            int i;
            for(i = (aiEntities.size() - numberOfAiEntities); i > 0; i--){
                try{
                    deleteOldestAiEntity();
                }
                catch (IndexOutOfBoundsException e){

                }
            }
        }
    }

    public boolean aiTryLock(){
        return aiEntitiesLock.tryLock();
    }

    public void aiUnlock(){
        aiEntitiesLock.unlock();
    }
}
