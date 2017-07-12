/*Resource used to learn Java graphics rendering: https://docs.oracle.com/javase/tutorial/2d/basic2d/index.html*/

import java.awt.Graphics;
import java.awt.Graphics2D;
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
        setBackground(Color.WHITE);

        setVisible(true);
        System.out.println("Panel setup complete.");
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        /*Draw the entities here*/
        Graphics2D g2D = (Graphics2D)g;
        g2D.drawOval(200, 200, 50, 50);
        g2D.drawOval(0, 0, 10, 10);
    }
}
