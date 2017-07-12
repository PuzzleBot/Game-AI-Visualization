import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class InterfacePanel extends JPanel{
    public static final int WIDTH = 100;
    public static final int HEIGHT = 650;

    private JComboBox aiChoiceMenu;
    private String[] aiChoices;

    private GridBagConstraints constraints;

    public InterfacePanel(){
        super();
        setLayout(new GridBagLayout());
        setSize(WIDTH, HEIGHT);

        aiChoices = new String[1];
        aiChoices[0] = "Emergent Behaviour - Flocking";
        aiChoiceMenu = new JComboBox(aiChoices);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 7;
        add(aiChoiceMenu, constraints);

        setVisible(true);
    }
}
