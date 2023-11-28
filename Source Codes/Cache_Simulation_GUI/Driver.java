import javax.swing.SwingUtilities;
import View.MainFrame;

/**
 * The Driver class serves as the entry point for the GUI-based cache simulation program.
 * It creates an instance of the MainFrame class within the Swing event dispatch thread.
 */
public class Driver{
    /**
     * The main method of the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}