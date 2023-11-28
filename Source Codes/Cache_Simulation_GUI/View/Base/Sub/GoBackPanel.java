package View.Base.Sub;
import View.Base.*;

/**
 * The GoBackPanel class represents a panel with a "Go Back" button.
 * It extends the BasePanel class and provides a convenient way to create a panel for navigation purposes.
*/
public class GoBackPanel extends BasePanel{

    /**
     * Constructs a GoBackPanel with the specified base panel.
     *
     * @param basePanel The base panel to navigate back to when the "Go Back" button is clicked.
    */
    public GoBackPanel(BasePanel basePanel){
        this.setBounds(0, 625, 500, 100);

        BaseButton exitButton = new BaseButton(1, "Go Back");
        MAL listener = new MAL(basePanel, "Go Back");
        exitButton.addActionListener(listener);

        this.add(exitButton);        
    }
}
