import java.util.ArrayList;

public class EntitySpace{
    private ArrayList<Entity> aiEntities;
    private Entity userEntity;

    public EntitySpace(){
        aiEntities = new ArrayList<Entity>();
        userEntity = new Entity(0, 0);
    }
}
