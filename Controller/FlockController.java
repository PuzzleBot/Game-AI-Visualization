/* An entity in a flock follows two rules:
 * Move towards the leader
 * Keep a distance from other entities in the flock*/

public class FlockController extends EntityController{
    public FlockController(EntitySpace assignedSpace){
        super(assignedSpace);

        int i;
        for(i = 0; i < 10; i++){
            assignedSpace.addAiEntity();
        }
    }
}
