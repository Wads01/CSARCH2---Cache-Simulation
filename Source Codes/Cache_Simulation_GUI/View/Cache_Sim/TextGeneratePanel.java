package View.Cache_Sim;
import View.Base.*;
import View.Base.Sub.*;
import java.awt.*;

/**
 * The TextGeneratePanel class represents the panel where the user can choose to generate or not generate text.
 * It extends the BasePanel class and includes components for generating or skipping the text generation process.
 */
public class TextGeneratePanel extends BasePanel{
    
    /**
     * Constructs a new TextGeneratePanel instance with components for generating or skipping text generation.
     */
    public TextGeneratePanel(){
        HeaderPanel headerPanel = new HeaderPanel("Text Generate Panel");
        BasePanel bodyPanel = new BasePanel(50, 100, 1250, 600);
        GoBackPanel goBackPanel = new GoBackPanel(TextGeneratePanel.this);

        BasePanel panels[] = {
            new BasePanel(50, 200, 500, 100),
            new BasePanel(650, 200, 500, 100),
        };

        BaseButton buttons[] = {
            new BaseButton(1, "Generate"),
            new BaseButton(1, "Dont Generate"),
        };

        MAL listeners[] = {
            new MAL(TextGeneratePanel.this, "Generate"),
            new MAL(TextGeneratePanel.this, "Exit"),
        };

        for (int i = 0; i < 2; i++){
            panels[i].add(buttons[i]);
            bodyPanel.add(panels[i]);
            buttons[i].addActionListener(listeners[i]);
        }

        this.add(goBackPanel);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
    }
}
