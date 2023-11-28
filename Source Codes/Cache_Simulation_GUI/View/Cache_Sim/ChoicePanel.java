package View.Cache_Sim;
import View.Base.*;
import View.Base.Sub.*;
import java.awt.*;

/**
 * The ChoicePanel class represents the panel where the user selects the unit of measurement for the main memory size.
 * It extends the BasePanel class and provides buttons for choosing between words and blocks as the unit.
 */
public class ChoicePanel extends BasePanel{

    /**
     * Constructs a new ChoicePanel instance with buttons for choosing the unit of measurement for the main memory size.
     */
    public ChoicePanel(){
        HeaderPanel headerPanel = new HeaderPanel("Simulator Setup");
        BasePanel bodyPanel = new BasePanel(50, 100, 1250, 600);
        GoBackPanel goBackPanel = new GoBackPanel(ChoicePanel.this);
        BasePanel promptPanel = new BasePanel(50,150,1250,100);
        BaseLabel promptLabel = new BaseLabel("Medium", "Choose Main Memory Size Unit");

        BasePanel panels[] = {
            new BasePanel(200, 350, 350, 100),
            new BasePanel(800, 350, 350, 100)
        };

        BaseButton buttons[] = {
            new BaseButton(4, "Words"),
            new BaseButton(4, "Blocks")
        };

        MAL listeners[] = {
            new MAL(ChoicePanel.this, "SetMemWord"),
            new MAL(ChoicePanel.this, "SetMemBlock")
        };


        for (int i = 0; i < 2; i++){
            panels[i].add(buttons[i]);
            buttons[i].addActionListener(listeners[i]);
            this.add(panels[i]);
        }

        promptLabel.setBounds(0, 0, 1250, 100);
        promptPanel.add(promptLabel);
        this.add(promptPanel);
        this.add(goBackPanel);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
    }
}
