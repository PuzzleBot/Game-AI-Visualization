/* A class representing an entity - a thing existing in the environment
 * which may move and interact with other things in the environment.
 * An entity always has a position in the environment, and may have an
 * acceleration and velocity to dictate how its position changes over time.*/
public class Entity{
    public Vector2D position;
    public Vector2D velocity;
    public Vector2D acceleration;

    public Entity(double x_start, double y_start){
        position = new Vector2D(x_start, y_start);

        velocity = new Vector2D(0, 0);

        acceleration = new Vector2D(0, 0);
    }

    public void updateEntity(){
        position.x = position.x + velocity.x;
        position.y = position.y + velocity.y;

        velocity.x = velocity.x + acceleration.x;
        velocity.y = velocity.y + acceleration.y;
    }

    public double computeDistanceFrom(Entity otherEntity){
        /*Pythagorean Theorem*/
        return Math.sqrt(Math.pow(otherEntity.position.x - position.x, 2) + Math.pow(otherEntity.position.y - position.y, 2));
    }
}
