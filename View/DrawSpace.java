import java.awt.Graphics;
import java.awt.Canvas;
import javax.swing.JFrame;
import java.util.ArrayList;

public class DrawSpace{
    private JFrame masterFrame;
    private Canvas masterCanvas;

    public DrawSpace(){
        masterFrame = new JFrame("AI Visualizer");
        masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        masterCanvas = new Canvas();
    }
}
