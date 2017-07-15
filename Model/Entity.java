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
}
