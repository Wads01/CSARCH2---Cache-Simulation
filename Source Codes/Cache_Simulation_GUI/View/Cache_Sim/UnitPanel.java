package View.Cache_Sim;
import View.Base.*;
import View.Base.Sub.*;
import java.awt.*;

/**
 * The UnitPanel class represents a panel used for simulator setup where the user is prompted to enter the unit of choice.
 * It extends the BasePanel class and includes components such as a header, a prompt label, a text field for input,
 * and a "Go Back" button.
 */
public class UnitPanel extends BasePanel{
    BaseLabel promptLabel;
    TextFieldPanel textFieldPanel;

    /**
     * Constructs a new UnitPanel instance with components for user input and navigation.
     */
    public UnitPanel(){
        HeaderPanel headerPanel = new HeaderPanel("Simulator Setup");
        BasePanel bodyPanel = new BasePanel(50, 100, 1250, 600);
        GoBackPanel goBackPanel = new GoBackPanel(UnitPanel.this);
        BasePanel promptPanel = new BasePanel(50,250,1250,100);
        promptLabel = new BaseLabel("Medium", null);
        textFieldPanel = new TextFieldPanel("CheckInput3", "Enter Input Here...");

        promptLabel.setBounds(0, 0, 1250, 100);
        promptPanel.add(promptLabel);
        this.add(promptPanel);
        this.add(goBackPanel);
        this.add(textFieldPanel);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
    }

    /**
     * Updates the prompt label based on the specified index.
     *
     * @param index The index used to determine the prompt text.
     */
    public void UpdateVariation(int index){
        switch(index){
            case 0:
                promptLabel.setText("Enter Number of Words 2**(1-20)");
                break;
            case 1:
                promptLabel.setText("Enter Number of Blocks (1-32768)");
        }
    }
}
