package View.Base.Sub;
import View.Base.*;
import javax.swing.*;

/**
 * The TextFieldPanel class represents a panel with a text field and an enter button.
 * It extends the BasePanel class and provides a convenient way to enter text input.
*/
public class TextFieldPanel extends BasePanel{
    BaseTextField textField;
    
    /**
     * Constructs a TextFieldPanel with the specified action command and type.
     *
     * @param actionCommand The action command associated with the enter button.
     * @param type          The type of the text field panel.
    */
    public TextFieldPanel(String actionCommand, String str){
        String imagePath = "/Assets/Buttons/textfield_button.png";
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource(imagePath));
        BaseButton textButton = new BaseButton(4, "Enter");
        textField = new BaseTextField(backgroundImage.getImage());
        MAL listener = new MAL(TextFieldPanel.this, actionCommand);

        textField.setAlignmentX(LEFT_ALIGNMENT);
        textButton.setAlignmentX(RIGHT_ALIGNMENT);
        textButton.addActionListener(listener);

        this.setBounds(0, 375, 1250, 100);
        textField.setText(str);
        this.add(textButton);
        this.add(textField);
    }

    /**
     * Gets the text entered in the text field.
     *
     * @return The text entered in the text field.
    */
    public String GetText(){
        return textField.getText();
    }
}
