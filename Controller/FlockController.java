/* An entity in a flock follows two rules:
 * Move towards the leader
 * Keep a distance from other entities in the flock*/

 import java.util.Iterator;
 import java.util.ArrayList;

public class FlockController extends EntityController{
    public FlockController(EntitySpace assignedSpace){
        super(assignedSpace);

        int i;
        for(i = 0; i < 10; i++){
            assignedSpace.addAiEntity();
        }
    }

    public void updateAiEntities(){
        /*Compare each entity with all other entities in the space, and have
         *them follow the two rules of flocking*/
        Iterator<Entity> modifyEntityIterator;
        Iterator<Entity> compareEntityIterator;

        /*Not done*/
    }
}
