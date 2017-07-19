/* An entity in a flock follows two rules:
 * Move towards the leader
 * Keep a distance from other entities in the flock*/

 import java.util.Iterator;
 import java.util.NoSuchElementException;
 import java.util.ArrayList;

public class FlockController extends EntityController{
    public double separationDistance;
    public double accelerationSetting;

    public FlockController(EntitySpace assignedSpace){
        super(assignedSpace);
        separationDistance = 20;
        accelerationSetting = 0.1;

        int i;
        for(i = 0; i < 10; i++){
            assignedSpace.addAiEntity();
        }
    }

    public void updateAiEntities(){
        /*Compare each entity with all other entities in the space, and have
         *them follow the two rules of flocking*/
        Iterator<Entity> modifyEntityIterator = controlledSpace.getAiListIterator();
        Iterator<Entity> compareEntityIterator;
        Entity modifyEntity;
        Entity compareEntity;
        Vector2D calculatedAcceleration = new Vector2D(0, 0);
        int i;
        int j;
        boolean modifyIterationDone = false;
        boolean compareIterationDone = false;

        /*Basically a nested for loop*/
        i = 0;
        while(modifyIterationDone == false){
            try{
                modifyEntity = modifyEntityIterator.next();
                compareEntityIterator = controlledSpace.getAiListIterator();
                compareIterationDone = false;
                calculatedAcceleration.setValue(0, 0);
                j = 0;
                while(compareIterationDone == false){
                    try{
                        compareEntity = compareEntityIterator.next();

                        /*Accelerate away if the entities are too close*/
                    }
                    catch (NoSuchElementException e){
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
    }
}
