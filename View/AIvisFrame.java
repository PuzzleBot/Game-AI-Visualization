import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class AIvisFrame extends JFrame{
    private DrawPanel canvasPanel;
    private InterfacePanel controlPanel;
    private GridBagConstraints constraints;

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    public AIvisFrame(){
        super("AI Visualizer");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        /*Set width, height, and other settings for the UI layout*/
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.ipadx = 10;
        constraints.ipady = 10;

        controlPanel = new InterfacePanel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 7;
        constraints.weightx = 0.05;
        constraints.weighty = 1;
        add(controlPanel, constraints);

        canvasPanel = new DrawPanel();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 10;
        constraints.gridheight = 7;
        constraints.weightx = 0.9;
        constraints.weighty = 1;
        add(canvasPanel, constraints);

        //pack();
        setVisible(true);

        System.out.println("Window setup complete.");
    }

    public EntityController getEntityController(){
        return canvasPanel.entityController;
    }
}
