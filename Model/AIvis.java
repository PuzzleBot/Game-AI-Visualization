import java.util.Timer;
import java.util.TimerTask;

public class AIvis{
    public static AIvisFrame mainWindow;
    public static Timer timer;
    public static UpdateViewTask loopTask;

    public static void main(String args[]){
        mainWindow = new AIvisFrame();
        timer = new Timer(true);
        loopTask = new UpdateViewTask(mainWindow.getCanvas());

        /*Begin updating the display after 1 second, repeating 33 times per second*/
        timer.scheduleAtFixedRate(loopTask, 100, 3);
    }
}
