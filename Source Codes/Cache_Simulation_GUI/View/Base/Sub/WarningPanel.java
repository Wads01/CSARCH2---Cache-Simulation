package View.Base.Sub;
import View.Base.*;
import java.awt.*;
import javax.swing.*;

/**
 * The WarningPanel class represents a panel that displays a warning message and a continue button.
 * It extends the BasePanel class and provides a visual indication of a warning or cautionary message.
*/
public class WarningPanel extends BasePanel{
    private BaseLabel label;

    /**
     * Constructs a WarningPanel with an empty warning message initially.
    */    
    public WarningPanel(){
        label = new BaseLabel("Large", "");
        BaseButton button = new BaseButton(1, "Continue");

        BasePanel panels[] = {
            new BasePanel(0, 0, 1366, 550),
            new BasePanel(400, 575, 500, 100)
        };

        label.setForeground(Color.BLACK);
        label.setBounds(JLabel.CENTER, JLabel.CENTER, 1366, 700);

        panels[0].add(label);
        panels[1].add(button);

        for (int i = 0; i < 2; i ++)
            this.add(panels[i]);

        MAL listener = new MAL(WarningPanel.this, "Go Back");
        button.addActionListener(listener);
    }

    /**
     * Updates the warning message displayed in the panel.
     *
     * @param text The new warning message.
    */
    public void UpdateVariation(String text){
        label.setText("!" + text + "!");
    }
}
