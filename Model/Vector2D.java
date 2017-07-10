public class Vector2D{
    public double x;
    public double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double dot(Vector2D otherVector){
        return (x * otherVector.x) + (y * otherVector.y);
    }
}
