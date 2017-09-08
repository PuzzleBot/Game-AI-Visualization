/* An abstract class which dictates how entities should move in the environment.
 * Subclasses of this class should hold the AI algorithm in updateAiEntities().*/
public abstract class EntityController{
    protected EntitySpace controlledSpace;

    public EntityController(EntitySpace assignedSpace){
        assignedSpace.bindController(this);
    }

    /*Move amount - a normalized vector indicating which direction to move*/
    public Vector2D moveUserEntity(Vector2D moveAmount){
        /*Stub*/
        return new Vector2D(0, 0);
    }

    /*To be called once per frame, contains the main AI algorithm*/
    public abstract void updateAiEntities();

    public void assignSpace(EntitySpace space){
        controlledSpace = space;
    }
}
