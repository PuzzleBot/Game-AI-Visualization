/*Resource used to learn Java graphics rendering: https://docs.oracle.com/javase/tutorial/2d/basic2d/index.html*/

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
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

    public Color userColor;
    public Color aiColor;

    public boolean mouseInArea;
    public boolean mouseDown;

    public DrawPanel(){
        super();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setSize(WIDTH, HEIGHT);

        entitySpace = new EntitySpace();
        entityController = new FlockController(entitySpace);
        entitySpace.bindController(entityController);

        userColor = new Color(48, 186, 255, 255);
        aiColor = new Color(255, 193, 48, 255);

        mouseInArea = false;
        mouseDown = false;

        addMouseListener(new UserMouseListener());
        addMouseMotionListener(new UserMouseMoveListener());

        setVisible(true);
        System.out.println("Canvas setup complete.");
    }

    public void recomputeAiEntities(){
        entityController.updateAiEntities();
    }

    @Override
    protected void paintComponent(Graphics g){
        Iterator<Entity> aiEntityIterator = entitySpace.getAiListIterator();
        Entity currentAiEntity = null;
        boolean iterationDone = false;

        super.paintComponent(g);

        /*Draw the entities here*/
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(aiColor);
        while(iterationDone == false){
            try{
                currentAiEntity = aiEntityIterator.next();
                g2D.fillOval((int)currentAiEntity.position.x, (int)currentAiEntity.position.y, EntitySpace.ENTITY_SIZE, EntitySpace.ENTITY_SIZE);
            }
            catch (NoSuchElementException e){
                iterationDone = true;
            }
        }
        g2D.setColor(userColor);
        g2D.fillOval((int)entitySpace.getUserPosition().x, (int)entitySpace.getUserPosition().y, EntitySpace.ENTITY_SIZE, EntitySpace.ENTITY_SIZE);
    }

    public class UserMouseListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e){

        }

        @Override
        public void mousePressed(MouseEvent e){
            mouseDown = true;
        }

        @Override
        public void mouseReleased(MouseEvent e){
            mouseDown = false;
        }

        @Override
        public void mouseEntered(MouseEvent e){
            mouseInArea = true;
        }

        @Override
        public void mouseExited(MouseEvent e){
            mouseInArea = false;
        }
    }

    public class UserMouseMoveListener implements MouseMotionListener{
        @Override
        public void mouseDragged(MouseEvent e){
            /*Set the user entity's location to the mouse's location on the panel*/
            entitySpace.setUserPosition(new Vector2D(e.getX(), e.getY()));
            //System.out.println("Mouse: " + e.getX() + ", " + e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e){

        }
    }
}
