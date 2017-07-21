/* An entity in a flock follows two rules:
 * Move towards the leader
 * Keep a distance from other entities in the flock*/

 import java.util.Iterator;
 import java.util.NoSuchElementException;
 import java.util.ArrayList;

public class FlockController extends EntityController{
    public double separationDistance;
    public double velocitySetting;

    public boolean useAccelerationInstead;

    public FlockController(EntitySpace assignedSpace){
        super(assignedSpace);
        separationDistance = 30;
        velocitySetting = 0.8;

        int i;
        for(i = 0; i < 40; i++){
            assignedSpace.addAiEntity();
        }

        useAccelerationInstead = false;
    }

    public void updateAiEntities(){
        /*Compare each entity with all other entities in the space, and have
         *them follow the two rules of flocking*/
        Iterator<Entity> modifyEntityIterator = controlledSpace.getAiListIterator();
        Iterator<Entity> compareEntityIterator;
        Entity modifyEntity;
        Entity compareEntity;
        Vector2D calculatedVelocity = new Vector2D(0, 0);
        int i;
        int j;
        boolean modifyIterationDone = false;
        boolean compareIterationDone = false;

        controlledSpace.aiReadLock();

        /*Basically a nested for loop*/
        i = 0;
        while(modifyIterationDone == false){
            try{
                modifyEntity = modifyEntityIterator.next();
                compareEntityIterator = controlledSpace.getAiListIterator();
                compareIterationDone = false;
                calculatedVelocity.setValue(0, 0);
                j = 0;
                while(compareIterationDone == false){
                    try{
                        compareEntity = compareEntityIterator.next();

                        /*Do not compare an entity with itself*/
                        if(i != j){
                            /*Accelerate away if the entities are too close*/
                            if(modifyEntity.computeDistanceFrom(compareEntity) < separationDistance){
                                Vector2D directionAway = new Vector2D(modifyEntity.position);
                                directionAway.subtract(compareEntity.position);
                                directionAway.normalize();
                                directionAway.scalarMultiply(velocitySetting * 1.1);

                                calculatedVelocity.add(directionAway);
                            }
                        }
                    }
                    catch (NoSuchElementException e){
                        /*Move towards the user, but keep a certain distance away*/
                        if(modifyEntity.computeDistanceFrom(controlledSpace.getUserEntity()) > separationDistance){
                            Vector2D directionToward = new Vector2D(controlledSpace.getUserPosition());
                            directionToward.subtract(modifyEntity.position);
                            directionToward.normalize();
                            directionToward.scalarMultiply(velocitySetting);

                            calculatedVelocity.add(directionToward);
                        }
                        else{
                            Vector2D directionAway = new Vector2D(modifyEntity.position);
                            directionAway.subtract(controlledSpace.getUserPosition());
                            directionAway.normalize();
                            directionAway.scalarMultiply(velocitySetting * 1.1);

                            calculatedVelocity.add(directionAway);
                        }

                        /*Alter the entities' acceleration or velocity?*/
                        if(useAccelerationInstead){
                            modifyEntity.acceleration.setValue(calculatedVelocity);
                        }
                        else{
                            modifyEntity.velocity.setValue(calculatedVelocity);
                        }

                        compareIterationDone = true;
                    }
                    j++;
                }
            }
            catch (NoSuchElementException e){
                modifyIterationDone = true;
            }
            i++;
        }

        /*Update the positions and velocities of all ai entities*/
        controlledSpace.aiReadUnlock();
        controlledSpace.updateAllAiPositions();
    }
}
