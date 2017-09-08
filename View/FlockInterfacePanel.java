import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/* This panel holds controls specific to the Flocking AI algorithm, allowing
 * for the keep-away radius and keep-away speed to be modified*/
public class FlockInterfacePanel extends JPanel{
    GridBagConstraints constraints;

    public FlockInterfacePanel(){
        super();
        setLayout(new GridBagLayout());

    }
}
