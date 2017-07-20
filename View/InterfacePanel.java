import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class InterfacePanel extends JPanel{
    public static final int WIDTH = 100;
    public static final int HEIGHT = 650;

    private JComboBox aiChoiceMenu;
    private String[] aiChoices;
    private JTextField aiNumberField;
    private JButton removeOneAiButton;
    private JButton addOneAiButton;

    private GridBagConstraints constraints;

    public InterfacePanel(){
        super();
        setLayout(new GridBagLayout());
        //setSize(WIDTH, HEIGHT);

        aiChoices = new String[2];
        aiChoices[0] = "Emergent Behaviour - Flocking";
        aiChoices[1] = "A* Algorithm";
        aiChoiceMenu = new JComboBox(aiChoices);

        /*AI controller choice box*/
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 0.2;
        constraints.ipadx = 10;
        constraints.ipady = 10;
        add(aiChoiceMenu, constraints);

        /*Add/remove AI entities*/
        removeOneAiButton = new JButton("-");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.25;
        constraints.weighty = 0.3;
        add(removeOneAiButton, constraints);

        aiNumberField = new JTextField("30");
        aiNumberField.setSize(30, 5);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.3;
        add(aiNumberField, constraints);

        addOneAiButton = new JButton("+");
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.25;
        constraints.weighty = 0.3;
        add(addOneAiButton, constraints);

        setVisible(true);
    }
}
