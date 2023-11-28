package View.Base.Sub;
import View.Base.*;

/**
 * The HeaderPanel class represents a panel with a header label and a stop BGM button.
 * It extends the BasePanel class and provides a header component for the GUI.
*/
public class HeaderPanel extends BasePanel{
    
    /**
     * Constructs a HeaderPanel with the specified title.
     *
     * @param title The title to be displayed in the header.
    */
    public HeaderPanel(String title){
        BaseLabel headerLabel = new BaseLabel("Large", title);
        headerLabel.setBounds(70, 0, 1216, 75);

        BasePanel stopBGMPanel = new BasePanel(0, 0, 75, 75);
        BaseButton stopBGMButton = new BaseButton(7, "");
        MAL listener = new MAL(HeaderPanel.this, "StopBGM");
        stopBGMPanel.setBounds(0, 0, 75, 75);
        stopBGMPanel.add(stopBGMButton);
        stopBGMButton.addActionListener(listener);

        BasePanel exitPanel = new BasePanel(0, 0, 75, 75);
        BaseButton exitButton = new BaseButton(6, "");
        MAL exitlistener = new MAL(HeaderPanel.this, "Exit");
        exitPanel.setBounds(1275, 0, 75, 75);
        exitPanel.add(exitButton);
        exitButton.addActionListener(exitlistener);

        this.setBounds(0, 0, 1366, 75);
        this.add(exitPanel);
        this.add(stopBGMPanel);
        this.add(headerLabel);
    }
}
