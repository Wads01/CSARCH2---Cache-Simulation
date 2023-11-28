package View.Cache_Sim;
import View.Base.*;
import View.Base.Sub.*;
import java.awt.*;

/**
 * The FlowPanel class represents the panel where the user can select the program flow for cache simulation setup.
 * It extends the BasePanel class and provides buttons to choose between different program flows, such as sequential,
 * random, and mid-repeat. The selected program flow will lead to the DetailsPanel for further configuration.
 */
public class FlowPanel extends BasePanel{
    
    /**
     * Constructs a new FlowPanel instance with components for selecting the program flow in cache simulation setup.
     */
    public FlowPanel(){
        HeaderPanel headerPanel = new HeaderPanel("Simulator Setup");
        BasePanel bodyPanel = new BasePanel(50, 100, 1250, 600);
        GoBackPanel goBackPanel = new GoBackPanel(FlowPanel.this);
        BasePanel promptPanel = new BasePanel(50,150,1250,100);
        BaseLabel promptLabel = new BaseLabel("Medium", "Select Program Flow");

        BasePanel panels[] = {
            new BasePanel(100, 350, 500, 100),
            new BasePanel(725, 350, 500, 100),
            new BasePanel(400, 500, 500, 100)
        };

        BaseButton buttons[] = {
            new BaseButton(1, "Sequential"),
            new BaseButton(1, "Random"),
            new BaseButton(1, "Mid-Repeat")
        };

        MAL listeners[] = {
            new MAL(FlowPanel.this, "DetailsPanel", 1),
            new MAL(FlowPanel.this, "DetailsPanel", 2),
            new MAL(FlowPanel.this, "DetailsPanel", 3)
        };

        for (int i = 0; i < 3; i++){
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
