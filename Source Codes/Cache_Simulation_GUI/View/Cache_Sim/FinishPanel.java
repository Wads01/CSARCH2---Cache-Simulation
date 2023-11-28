package View.Cache_Sim;
import View.Base.*;
import View.Base.Sub.*;
import java.awt.*;
import javax.swing.JLabel;
import Controller.CacheSim;

/**
 * The FinishPanel class represents the panel displaying cache simulation results and statistics after completion.
 * It extends the BasePanel class and provides information such as total access count, cache hits, cache misses,
 * miss penalty, average memory access time, and total memory access time.
 */
public class FinishPanel extends BasePanel{
    private CacheSim cacheSim = CacheSim.GetCacheSim();
    private BasePanel updatePanel;

    /**
     * Constructs a new FinishPanel instance with components for displaying cache simulation results and statistics.
     */
    public FinishPanel(){
        HeaderPanel headerPanel = new HeaderPanel("Cache Details");
        BasePanel bodyPanel = new BasePanel(50, 100, 1250, 600);
        GoBackPanel goBackPanel = new GoBackPanel(FinishPanel.this);

        updatePanel = new BasePanel(50, 100, 1250, 500);
        BasePanel panel = new BasePanel(850, 625, 500, 100);

        BaseButton button = new BaseButton(1, "Continue");
        MAL listener = new MAL(FinishPanel.this, "TextGeneratePanel");
        button.addActionListener(listener);
        panel.add(button);

        this.add(panel);
        this.add(updatePanel);
        this.add(goBackPanel);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
    }

    public void UpdateVariation(){
        updatePanel.removeAll();
        int cacheHits = cacheSim.GetCache().getCacheHits();
        int cacheMisses = cacheSim.GetCache().getCacheMisses();
        int totalAccessCount = cacheHits + cacheMisses;
        int missPenalty = cacheSim.GetCache().calcMissPenalty();
        double avgMAT = cacheSim.GetCache().calcAvgMAT();
        double totalMAT = cacheSim.GetCache().calcTotalMAT();

        for(int i = 0 ; i < 6; i++){
            String strlabel = null;
            switch (i){
                case 0: strlabel = String.format("Total Access Count:  " + totalAccessCount); break;
                case 1: strlabel = String.format("Cache Hits:  " + cacheHits); break;
                case 2: strlabel = String.format("Cache Misses:  " + cacheMisses); break;
                case 3: strlabel = String.format("Miss Penalty:  " + missPenalty + "ns"); break;
                case 4: strlabel = String.format("Average Memory Access Time:  " + avgMAT + "ns"); break;
                case 5: strlabel = String.format("Total Memory Access Time:  " + totalMAT + "ns"); break;
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
