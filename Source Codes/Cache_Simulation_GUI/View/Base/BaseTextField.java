package View.Base;
import java.awt.*;
import javax.swing.*;

/**
 * The BaseTextField class represents a custom text field in the GUI.
 * It extends the JTextField class and provides additional functionality for setting the background image.
*/
public class BaseTextField extends JTextField{
    private Image backgroundImage;

    /**
     * Constructs a BaseTextField with the specified background image.
     *
     * @param backgroundImage The background image to be displayed in the text field.
    */
    public BaseTextField(Image backgroundImage){
        this.backgroundImage = backgroundImage;
        this.setPreferredSize(new Dimension(875, 100));
        this.setBounds(375, 0, 875, 100);
        this.setFont(StaticFont.GetDoctorGlitchMediumFont());
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setOpaque(true);
        this.setForeground(Color.black);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if (backgroundImage != null){
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
