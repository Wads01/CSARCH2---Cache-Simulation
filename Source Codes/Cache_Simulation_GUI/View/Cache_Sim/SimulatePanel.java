package View.Cache_Sim;
import View.Base.*;
import View.Base.Sub.*;
import java.awt.*;
import javax.swing.*;
import Controller.CacheSim;
import Model.*;

/**
 * The SimulatePanel class represents a panel for simulating cache operations.
 * It extends the BasePanel class and includes components such as a header, cache grid, and navigation buttons.
 */
public class SimulatePanel extends BasePanel{
    private boolean flag = true;
    private CacheState state;
    private CacheSim cacheSim = CacheSim.GetCacheSim();

    BasePanel exitPanel = new BasePanel(1250, 625, 100, 100);
    BaseButton exitButton = new BaseButton(13, "");
    MAL exitListener = new MAL(SimulatePanel.this, "FinishPanel");

    private BaseLabel squareLabels[];
    private BaseLabel cacheLabels[] = {
        new BaseLabel("Small", null),
        new BaseLabel("Smallest", null),
        new BaseLabel("Smallest", null),
        new BaseLabel("Small", null),
        new BaseLabel("Smallest", null),
        new BaseLabel("Smallest", null),
        new BaseLabel("Smallest", null),
        new BaseLabel("Smallest", null)
    };

    /**
     * Initializes the SimulatePanel by setting up components such as the header, cache grid, and navigation.
     */
    public void InitializePanel(){
        HeaderPanel headerPanel = new HeaderPanel("8-WAY BSA");
        BasePanel bodyPanel = new BasePanel(50, 100, 1250, 600);

        BasePanel labelPanels[] = {
            new BasePanel(50, 100, 500, 50),
            new BasePanel(850, 100, 675, 50),
            new BasePanel(850, 150, 675, 50),
            new BasePanel(50, 150, 675, 50),
            new BasePanel(50, 500, 675, 50),
            new BasePanel(850, 450, 675, 50),
            new BasePanel(850, 500, 675, 50),
            new BasePanel(50, 450, 675, 50)
        };

        BasePanel buttonPanels[] = {
            new BasePanel(475, 550, 150, 150),
            new BasePanel(725, 550, 150, 150),
            new BasePanel(250, 550, 150, 150),
            new BasePanel(950, 550, 150, 150),
            new BasePanel(0, 625, 100, 100),
        };

        BaseButton buttons[] = {
            new BaseButton(8, ""),
            new BaseButton(9, ""),
            new BaseButton(10, ""),
            new BaseButton(11, ""),
            new BaseButton(12, ""),
        };

        MAL listeners[] = {
            new MAL(SimulatePanel.this, "Backward"),
            new MAL(SimulatePanel.this, "Forward"),
            new MAL(SimulatePanel.this, "Restart"),
            new MAL(SimulatePanel.this, "Skip"),
            new MAL(SimulatePanel.this, "View"),
        };

        ImageIcon icons[] = new ImageIcon[cacheSim.GetCache().getSetSize()];
        BasePanel squarePanels[] = new BasePanel[cacheSim.GetCache().getSetSize()];
        squareLabels = new BaseLabel[cacheSim.GetCache().getSetSize()];
        BasePanel squarePanels2[] = new BasePanel[cacheSim.GetCache().getSetSize()];
        BaseLabel squareLabels2[] = new BaseLabel[cacheSim.GetCache().getSetSize()];

        for (int i = 0; i < cacheSim.GetCache().getSetSize(); i++){
            squareLabels[i] = new BaseLabel("Medium", "-1");
            squareLabels2[i] = new BaseLabel("Smallest", String.valueOf(i));
            icons[i] = new ImageIcon(getClass().getResource("/Assets/Buttons/square.png"));
            squarePanels[i] = new BasePanel(50+(150*i), 275, 150, 150);
            squarePanels2[i] = new BasePanel(100 + (150*i), 225, 50, 50);
            squareLabels[i].setBounds(0,0, 150, 150);
            squareLabels2[i].setBounds(0, 0, 50, 50);
            squarePanels[i].add(squareLabels[i]);
            squarePanels2[i].add(squareLabels2[i]);
            squareLabels[i].setIcon(icons[i]);
            this.add(squarePanels[i]);
            this.add(squarePanels2[i]);
        }

        for (int i = 0; i < 8; i++){
            labelPanels[i].add(cacheLabels[i]);
            cacheLabels[i].setHorizontalAlignment(JLabel.LEFT);
            cacheLabels[i].setBounds(0, 0, labelPanels[i].getWidth(), labelPanels[i].getHeight());
            this.add(labelPanels[i]);
        }

        for (int i = 0; i < 5; i++){
            buttonPanels[i].add(buttons[i]);
            buttons[i].addActionListener(listeners[i]);
            this.add(buttonPanels[i]);
        }
        
        exitPanel.setVisible(false);
        exitPanel.add(exitButton);
        exitButton.addActionListener(exitListener);
        this.add(exitPanel);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
    }

    /**
     * Updates the labels of the cache details based on the specified state index.
     *
     * @param stateIndex The index of the cache state to display.
     */
    public void UpdateLabels(int stateIndex){
        state = cacheSim.GetCache().getCacheStates().get(stateIndex);
        int setIndex = state.setIndex;
        int randBlockIndex = state.randBlockIndex;
        int data = state.data;
        int numSets = cacheSim.GetCache().getNumSets();
        int setSize = cacheSim.GetCache().getSetSize();

        cacheLabels[3].setText("Selected Set: " + setIndex);
        cacheLabels[5].setText("Data: " + data);
        cacheLabels[6].setText("Set Loc: " + data + " % " + numSets + " = " + (data % numSets));
        cacheLabels[7].setText("Random (0-" + (setSize - 1) + "): " + randBlockIndex);
    }

    /**
     * Initializes the labels with starting values.
     */
    public void InitializeLabels(){
        StringBuilder setStr = new StringBuilder();
        for (int i = 0; i < cacheSim.GetCache().getNumSets(); i++)
            setStr.append(i).append("   ");

        cacheLabels[0].setText("Sets: ( " + setStr.toString() + ")");
        cacheLabels[1].setText("Cache Hits: ");
        cacheLabels[2].setText("Cache MIsses: ");
        cacheLabels[3].setText("Selected Set: ");
        cacheLabels[4].setText("Total Memory Acess Time: ns");
        cacheLabels[5].setText("Data: null");
        cacheLabels[6].setText("Set Loc: 0 % 0 = 0");
    }

    /**
     * Updates the values of the cache squares based on the specified state index.
     * Also updates the cache hits, cache miss, and total memory access time.
     *
     * @param stateIndex The index of the cache state to display.
     */
    public void UpdateValues(int stateIndex){
        state = cacheSim.GetCache().getCacheStates().get(stateIndex);
        double totalAccessTime = state.totalAccessTime;
        int setIndex = state.setIndex;
        int cacheHits = state.cacheHits;
        int cacheMisses = state.cacheMisses;

        if(!flag){
            CacheSet set = state.cacheSets.get(setIndex);
            int[] block = set.getBlocks();

            for (int i = 0; i < cacheSim.GetCache().getSetSize(); i++){
                squareLabels[i].setForeground(Color.WHITE);
                squareLabels[i].setText(String.valueOf(block[i]));
            }

            cacheLabels[1].setText("Cache Hits: " + cacheHits);
            cacheLabels[2].setText("Cache MIsses: " + cacheMisses);
            cacheLabels[4].setText("Total Memory Acess Time: " + totalAccessTime + "ns");
        }
        else{
            if(stateIndex != 0)
                state = cacheSim.GetCache().getCacheStates().get(stateIndex-1);
            CacheSet set = state.cacheSets.get(setIndex);
            int[] block = set.getBlocks();

            for (int i = 0; i < cacheSim.GetCache().getSetSize(); i++){
                squareLabels[i].setForeground(Color.WHITE);
                squareLabels[i].setText(String.valueOf(block[i]));
            }
        }

        flag = !flag;
    }

    /**
     * Backtracks to the previous state based on the specified state index.
     *
     * @param stateIndex The index of the cache state to display.
     */
    public void BackTrack(int stateIndex){
        state = cacheSim.GetCache().getCacheStates().get(stateIndex);
        double totalAccessTime = state.totalAccessTime;
        int cacheHits = state.cacheHits;
        int cacheMisses = state.cacheMisses;
        int setIndex = state.setIndex;

        if ((stateIndex - 1) != -1){
            state = cacheSim.GetCache().getCacheStates().get(stateIndex - 1);
            CacheSet set = state.cacheSets.get(setIndex);
            int[] block = set.getBlocks();
            for (int i = 0; i < cacheSim.GetCache().getSetSize(); i++){
                squareLabels[i].setText(String.valueOf(block[i]));
            }
        }

        cacheLabels[1].setText("Cache Hits: " + cacheHits);
        cacheLabels[2].setText("Cache MIsses: " + cacheMisses);
        cacheLabels[4].setText("Total Memory Acess Time: " + totalAccessTime + "ns");
        flag = false;
    }

    /**
     * Updates the labels without changing the cache square values.
     *
     * @param stateIndex The index of the cache state to display.
     */
    public void UpdateLabels2(int stateIndex){
        state = cacheSim.GetCache().getCacheStates().get(stateIndex);
        double totalAccessTime = state.totalAccessTime;
        int cacheHits = state.cacheHits;
        int cacheMisses = state.cacheMisses;

        cacheLabels[1].setText("Cache Hits: " + cacheHits);
        cacheLabels[2].setText("Cache MIsses: " + cacheMisses);
        cacheLabels[4].setText("Total Memory Acess Time: " + totalAccessTime + "ns");
        flag = false;
    }
    
    /**
     * Displays the exit button.
     */
    public void ShowButton(){
        exitPanel.setVisible(true);
    }
}
