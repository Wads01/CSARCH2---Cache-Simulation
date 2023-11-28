package View.Base;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

/**
 * The BasePanel class represents a custom panel in the GUI.
 * It extends the JPanel class and provides additional functionality for setting the panel's properties.
*/
public class BasePanel extends JPanel{

    /**
     * Constructs a BasePanel with default properties.
    */
    public BasePanel(){
        SetBasePanel();
    }

    /**
     * Constructs a BasePanel with the specified position and size.
     *
     * @param x      The x-coordinate of the panel's position.
     * @param y      The y-coordinate of the panel's position.
     * @param width  The width of the panel.
     * @param height The height of the panel.
    */
    public BasePanel(int x, int y, int width, int height){
        this.setBounds(x, y, width, height);
        SetBasePanel();
    }

    /**
     * Sets the base properties of the panel.
     * This includes setting the layout to null and setting the background color.
    */
    public void SetBasePanel(){
        this.setLayout(null);
        this.setBackground(new ColorUIResource(0x808080));
        this.setVisible(true);
    }
}
