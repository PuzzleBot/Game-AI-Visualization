/*An exception to be thrown when an invalid value is used as input.*/
public class InvalidValueException extends Exception{
    public InvalidValueException(String msg){
        super(msg);
    }
}
