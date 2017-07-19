public class Vector2D{
    public double x;
    public double y;

    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(Vector2D v){
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setValue(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setValue(Vector2D v){
        this.x = v.x;
        this.y = v.y;
    }

    public void add(Vector2D v){
        this.x = this.x + v.x;
        this.y = this.y + v.y;
    }

    public void add(double x, double y){
        this.x = this.x + x;
        this.y = this.y + y;
    }

    public void subtract(Vector2D v){
        this.x = this.x - v.x;
        this.y = this.y - v.y;
    }

    public void subtract(double x, double y){
        this.x = this.x - x;
        this.y = this.y - y;
    }

    public void scalarMultiply(double scalar){
        x = x * scalar;
        y = y * scalar;
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

    public boolean equals(Vector2D otherVector){
        if((Math.abs(this.x - otherVector.x) < 0.001) && (Math.abs(this.y - otherVector.y) < 0.001)){
            return true;
        }
        else{
            return false;
        }
    }
}
