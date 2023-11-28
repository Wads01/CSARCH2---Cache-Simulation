package View.Base.Sub;
import View.Base.*;
import java.awt.*;
import javax.swing.*;

/**
 * The SuccessPanel class represents a panel that displays a success message and a continue button.
 * It extends the BasePanel class and provides a visual indication of a successful operation.
*/
public class SuccessPanel extends BasePanel{
    private BaseLabel label;

    /**
     * Constructs a SuccessPanel with an empty success message initially.
    */
    public SuccessPanel(){
        label = new BaseLabel("Large", "");
        BaseButton button = new BaseButton(1, "Continue");

        BasePanel panels[] = {
            new BasePanel(0, 0, 1366, 550),
            new BasePanel(400, 575, 500, 100)
        };

        label.setForeground(Color.BLUE);
        label.setBounds(JLabel.CENTER, JLabel.CENTER, 1366, 700);

        panels[0].add(label);
        panels[1].add(button);

        for (int i = 0; i < 2; i ++)
            this.add(panels[i]);

        MAL listener = new MAL(SuccessPanel.this, "Go Back");
        button.addActionListener(listener);
    }

    /**
     * Updates the success message displayed in the panel.
     *
     * @param text The new success message.
    */
    public void UpdateVariation(String text){
        label.setText(text);
    }
}
