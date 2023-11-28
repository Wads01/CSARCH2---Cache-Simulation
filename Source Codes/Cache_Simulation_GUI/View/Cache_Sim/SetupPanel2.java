package View.Cache_Sim;
import View.Base.*;
import View.Base.Sub.*;
import java.awt.*;

/**
 * The SetupPanel2 class represents the panel where the user can set up the set size for cache simulation.
 * It extends the BasePanel class and includes components for entering and validating the set size.
 */
public class SetupPanel2 extends BasePanel{

    /**
     * Constructs a new SetupPanel2 instance with components for setting up the set size in cache simulation.
     */
    public SetupPanel2(){
        HeaderPanel headerPanel = new HeaderPanel("Simulator Setup");
        BasePanel bodyPanel = new BasePanel(50, 100, 1250, 600);
        GoBackPanel goBackPanel = new GoBackPanel(SetupPanel2.this);
        BasePanel promptPanel = new BasePanel(50,250,1250,100);
        BaseLabel promptLabel = new BaseLabel("Medium", "Enter Set Size (1-8) Blocks");
        TextFieldPanel textFieldPanel = new TextFieldPanel("CheckInput2", "Enter Input Here...");

        promptLabel.setBounds(0, 0, 1250, 100);
        promptPanel.add(promptLabel);
        this.add(promptPanel);
        this.add(goBackPanel);
        this.add(textFieldPanel);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
    }
}
