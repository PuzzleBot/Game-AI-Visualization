public class Entity{
    public Vector2D position;
    public Vector2D velocity;
    public Vector2D acceleration;

    public Entity(double x_start, double y_start){
        position.x = x_start;
        position.y = y_start;

        velocity.x = 0;
        velocity.y = 0;

        acceleration.x = 0;
        acceleration.y = 0;
    }

    public void updateEntity(){
        position.x = position.x + velocity.x;
        position.y = position.y + velocity.y;

        velocity.x = velocity.x + acceleration.x;
        velocity.y = velocity.y + acceleration.y;
    }
}
