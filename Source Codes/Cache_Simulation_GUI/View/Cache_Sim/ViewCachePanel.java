package View.Cache_Sim;
import View.Base.*;
import View.Base.Sub.*;
import java.awt.*;
import javax.swing.*;
import Controller.CacheSim;
import Model.*;

/**
 * The ViewCachePanel class represents a panel for viewing the current state of the cache memory.
 * It extends the BasePanel class and includes components such as a header, a grid of cache squares,
 * and a "Go Back" button.
 */
public class ViewCachePanel extends BasePanel{
    private CacheState state;
    private CacheSim cacheSim = CacheSim.GetCacheSim();
    private ImageIcon icons[];
    private BasePanel squarePanels[];
    private BaseLabel squareLabels[];

    /**
     * Initializes the ViewCachePanel by setting up components such as the header, cache grid, and navigation.
     */
    public void InitializePanel(){
        HeaderPanel headerPanel = new HeaderPanel("Cache Memory View");
        BasePanel bodyPanel = new BasePanel(50, 100, 1250, 600);
        GoBackPanel goBackPanel = new GoBackPanel(ViewCachePanel.this);

        int size = cacheSim.GetCache().getNumSets() * cacheSim.GetCache().getSetSize();
        int counter = 0;
        icons = new ImageIcon[size];
        squarePanels = new BasePanel[size];
        squareLabels = new BaseLabel[size];

        BasePanel setPanel[] = new BasePanel[cacheSim.GetCache().getNumSets()];
        BaseLabel setLabel[] = new BaseLabel[cacheSim.GetCache().getNumSets()];

        for (int i = 0; i < cacheSim.GetCache().getNumSets(); i++){
            setPanel[i] = new BasePanel(125, 125+(125*i), 150, 75);
            setLabel[i] = new BaseLabel("Small", "Set: " + i);

            for (int j = 0 ; j < cacheSim.GetCache().getSetSize(); j++){
                squareLabels[counter] = new BaseLabel("Medium", null);
                icons[counter] = new ImageIcon(getClass().getResource("/Assets/Buttons/small_square.png"));
                squarePanels[counter] = new BasePanel(325+(100*j), 100+(125*i), 100, 100);
                squareLabels[counter].setBounds(0,0, 100, 100);
                squareLabels[counter].setIcon(icons[counter]);
                squarePanels[counter].add(squareLabels[counter]);
                this.add(squarePanels[counter]);

                counter++;
            }

            setLabel[i].setBounds(0, 0, 150, 75);
            setPanel[i].add(setLabel[i]);
            this.add(setPanel[i]);
        }

        this.add(goBackPanel);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);
    }

    /**
     * Updates the variation of the cache view based on the specified cache state.
     *
     * @param stateIndex The index of the cache state to display.
     */
    public void UpdateVariation(int stateIndex){
        state = cacheSim.GetCache().getCacheStates().get(stateIndex);
        int numSets = cacheSim.GetCache().getNumSets();
        int setSize = cacheSim.GetCache().getSetSize();
        int counter = 0;

        for (int i = 0; i < numSets; i++){
            CacheSet set = state.cacheSets.get(i);
            int[] blocks = set.getBlocks();
            for (int j = 0; j < setSize; j++){
                squareLabels[counter].setText(String.valueOf(blocks[j]));
                counter++;
            }
        }
    }
}
