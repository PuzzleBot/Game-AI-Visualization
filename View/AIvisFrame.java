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
        canvasPanel = new DrawPanel();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 9;
        constraints.gridheight = 7;
        add(canvasPanel, constraints);

        controlPanel = new InterfacePanel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 7;
        add(controlPanel, constraints);

        //pack();
        setVisible(true);

        System.out.println("Window setup complete.");
    }
}
