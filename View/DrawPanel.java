/*Resource used to learn Java graphics rendering: https://docs.oracle.com/javase/tutorial/2d/basic2d/index.html*/

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DrawPanel extends JPanel{
    public Graphics panelGraphics;
    public EntitySpace entitySpace;
    public EntityController entityController;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 650;

    public DrawPanel(){
        super();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        entitySpace = new EntitySpace();
        entityController = new FlockController(entitySpace);
        entitySpace.bindController(entityController);

        setVisible(true);
        System.out.println("Canvas setup complete.");
    }

    public void recomputeAiEntities(){
        
    }

    @Override
    protected void paintComponent(Graphics g){
        Iterator<Entity> aiEntityIterator = entitySpace.getAiList().iterator();
        Entity currentAiEntity = null;
        boolean iterationDone = false;

        super.paintComponent(g);

        /*Draw the entities here*/
        Graphics2D g2D = (Graphics2D)g;
        while(iterationDone == false){
            try{
                currentAiEntity = aiEntityIterator.next();
                g2D.fillOval((int)currentAiEntity.position.x, (int)currentAiEntity.position.y, 10, 10);
            }
            catch (NoSuchElementException e){
                iterationDone = true;
            }
        }
        g2D.fillOval(200, 200, 50, 50);
        g2D.fillOval(0, 0, 10, 10);
    }
}
