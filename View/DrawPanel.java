import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.ArrayList;

public class DrawPanel extends JPanel{
    public Graphics panelGraphics;
    private EntitySpace entitySpace;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 650;

    public DrawPanel(){
        super();
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        add(new JButton("Test"));

        setVisible(true);
        System.out.println("Panel setup complete.");
    }

    @Override
    protected void paintComponent(Graphics g){
        /*Draw the entities here*/
        g.drawOval(200, 200, 50, 50);
    }
}
