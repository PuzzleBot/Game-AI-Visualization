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

    public void assignSpace(EntitySpace space){
        controlledSpace = space;
    }
}
