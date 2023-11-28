package View.Cache_Sim;
import View.Base.*;
import View.Base.Sub.*;
import java.awt.*;

/**
 * The SetupPanel class represents the panel where the user can set up the block size for cache simulation.
 * It extends the BasePanel class and includes components for entering and validating the block size.
 */
public class SetupPanel extends BasePanel{

    /**
     * Constructs a new SetupPanel instance with components for setting up the block size in cache simulation.
     */
    public SetupPanel(){
        HeaderPanel headerPanel = new HeaderPanel("Simulator Setup");
        BasePanel bodyPanel = new BasePanel(50, 100, 1250, 600);
        BasePanel promptPanel = new BasePanel(50,250,1250,100);
        BaseLabel promptLabel = new BaseLabel("Medium", "Enter Valid Block Size 2**(1-5) Words");
        TextFieldPanel textFieldPanel = new TextFieldPanel("CheckInput", "Enter Input Here...");

        promptLabel.setBounds(0, 0, 1250, 100);
        promptPanel.add(promptLabel);
        this.add(promptPanel);
        this.add(textFieldPanel);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
    }
}
