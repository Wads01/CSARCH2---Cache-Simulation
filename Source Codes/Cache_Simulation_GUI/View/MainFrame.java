package View;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import View.Base.*;
import View.Base.Sub.*;
import View.Cache_Sim.*;

/**
 * The MainFrame class represents the main window of the cache memory simulation application.
 * It manages different panels using CardLayout for a dynamic user interface.
 */
public class MainFrame extends JFrame{
    private ArrayList<BasePanel> panelList;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    /**
     * Constructs a new MainFrame instance, initializing the CardLayout, panelList, and cardPanel.
     * Sets up the initial card panels and starts the main frame.
     */
    public MainFrame(){
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        panelList = new ArrayList<BasePanel>();
        this.add(cardPanel);
        SetCardPanels(panelList);

        MainFrameStart();
    }

    /**
     * Starts the MainFrame by setting up the initial settings before launch
     */
    private void MainFrameStart(){
        ImageIcon img = new ImageIcon(getClass().getResource("/Assets/logo.jpg"));
        this.setTitle("Cache Memory Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1366, 768);
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Displays a specific panel identified by its name using CardLayout.
     *
     * @param panelName The name of the panel to be displayed.
     */
    public void ShowPanel(String panelName){
        cardLayout.show(cardPanel, panelName);
    }

    /**
     * Sets up and adds different card panels to the panelList and cardPanel.
     *
     * @param panelList The list to store different card panels.
     */
    public void SetCardPanels(ArrayList<BasePanel> panelList){
        String panelNames[] = {"IntroPanel", "SetupPanel", "SetupPanel2", "WarningPanel",
                                "SuccessPanel", "ChoicePanel", "UnitPanel", "FlowPanel",
                                "DetailsPanel", "SimulatePanel", "ViewCachePanel",
                                "FinishPanel", "TextGeneratePanel"};

        AddCardPanels(panelList);
        for (int i = 0; i < panelList.size(); i++)
            cardPanel.add(panelList.get(i), panelNames[i]);

        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "IntroPanel");
    }

    /**
     * Adds different card panels to the panelList.
     *
     * @param panelList The list to store different card panels.
     */
    public void AddCardPanels(ArrayList<BasePanel> panelList){
        panelList.add(new IntroPanel());            //0
        panelList.add(new SetupPanel());            //1
        panelList.add(new SetupPanel2());           //2
        panelList.add(new WarningPanel());          //3
        panelList.add(new SuccessPanel());          //4
        panelList.add(new ChoicePanel());           //5
        panelList.add(new UnitPanel());             //6
        panelList.add(new FlowPanel());             //7
        panelList.add(new DetailsPanel());          //8
        panelList.add(new SimulatePanel());         //9
        panelList.add(new ViewCachePanel());        //10
        panelList.add(new FinishPanel());           //11
        panelList.add(new TextGeneratePanel());     //12
    }
    
    /**
     * Gets the list of card panels.
     *
     * @return The list of card panels.
     */
    public ArrayList<BasePanel> GetPanelList(){
        return panelList;
    }
}
