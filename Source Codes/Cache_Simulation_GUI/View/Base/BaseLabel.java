package View.Base;
import java.awt.*;
import javax.swing.*;

/**
 * The BaseLabel class represents a custom label in the GUI.
 * It extends the JLabel class and provides additional functionality for setting the font size and alignment.
*/
public class BaseLabel extends JLabel{
    
    /**
     * Constructs a BaseLabel with the specified font size and text.
     *
     * @param fontSize The font size of the label.
     * @param text     The text displayed on the label.
    */
    public BaseLabel(String fontSize, String text){
        SetFontType(fontSize);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setOpaque(false);
        this.setForeground(Color.white);
        this.setText(text);
        this.setVisible(true);
    }

    /**
     * Sets the font type based on the specified font size.
     *
     * @param fontSize The font size of the label.
    */
    public void SetFontType(String fontSize){
        switch (fontSize){
            case "Smallest": this.setFont(StaticFont.GetDoctorGlitchSmallestFont()); break;
            case "Small": this.setFont(StaticFont.GetDoctorGlitchSmallFont()); break;
            case "Medium": this.setFont(StaticFont.GetDoctorGlitchMediumFont()); break;
            case "Large": this.setFont(StaticFont.GetDoctorGlitchLargeFont());
        }
    }
}
