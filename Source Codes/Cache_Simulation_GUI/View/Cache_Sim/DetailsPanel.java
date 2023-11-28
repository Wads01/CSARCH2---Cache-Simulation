package View.Cache_Sim;
import View.Base.*;
import View.Base.Sub.*;
import java.awt.*;
import javax.swing.JLabel;
import Controller.CacheSim;

/**
 * The DetailsPanel class represents the panel displaying simulation details and setup information.
 * It extends the BasePanel class and provides information about cache memory, replacement algorithm, read policy,
 * program flow, cache size, cache line size, block size, set size, and main memory size.
 */
public class DetailsPanel extends BasePanel{
    private CacheSim cacheSim = CacheSim.GetCacheSim();
    private BasePanel updatePanel;

    /**
     * Constructs a new DetailsPanel instance with components for displaying simulation details and setup information.
     */
    public DetailsPanel(){
        HeaderPanel headerPanel = new HeaderPanel("Simulator Details");
        BasePanel bodyPanel = new BasePanel(50, 100, 1250, 600);
        GoBackPanel goBackPanel = new GoBackPanel(DetailsPanel.this);

        updatePanel = new BasePanel(50, 100, 1250, 500);
        BasePanel panel = new BasePanel(850, 625, 500, 100);

        BaseButton button = new BaseButton(1, "Continue");
        MAL listener = new MAL(DetailsPanel.this, "SimulatePanel");
        button.addActionListener(listener);
        panel.add(button);

        this.add(panel);
        this.add(updatePanel);
        this.add(goBackPanel);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
    }

    public void UpdateVariation(int index){
        updatePanel.removeAll();
        String[] programFlow = {"Sequential Sequence", "Random Sequence", "Mid-Repeat Blocks"};

        for(int i = 0 ; i < 9; i++){
            String strlabel = null;
            switch (i){
                case 0: strlabel = String.format("Type of Cache Memory:   8-way BSA"); break;
                case 1: strlabel = String.format("Replacement Algorithm:   random.nextInt"); break;
                case 2: strlabel = String.format("Read Policy:   Non-Load Through"); break;
                case 3: strlabel = String.format("Program Flow:   " + programFlow[index-1]); break;
                case 4: strlabel = String.format("Cache Memory Size:   512 words or 32 blocks"); break;
                case 5: strlabel = String.format("Cache Line Size:   16 words"); break;
                case 6: strlabel = String.format("Block Size:   " + cacheSim.GetBlockSize() + " words"); break;
                case 7: strlabel = String.format("Set Size:   " + cacheSim.GetSetSize() + " blocks"); break;
                case 8: strlabel = String.format("Main Memory Size:   " + cacheSim.GetMMSize() +
                                                 " blocks or " + cacheSim.GetMMSize() * cacheSim.GetBlockSize() + " words"); break;
            }
            BasePanel panel = new BasePanel(0, (55*i), 1250, 55);
            BaseLabel label = new BaseLabel("Smallest", strlabel);
            label.setBounds(0, 0, 1250, 55);
            label.setHorizontalTextPosition(JLabel.LEFT);
            label.setHorizontalAlignment(JLabel.LEFT);
            panel.add(label);
            updatePanel.add(panel);
        }
    }
}
