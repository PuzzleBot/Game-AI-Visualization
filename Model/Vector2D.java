public class Vector2D{
    public double x;
    public double y;

    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setValue(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double dot(Vector2D otherVector){
        return (x * otherVector.x) + (y * otherVector.y);
    }

    public void normalize(){
        double magnitude = computeMagnitude();
        x = x / magnitude;
        y = y / magnitude;
    }

    public double computeMagnitude(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
