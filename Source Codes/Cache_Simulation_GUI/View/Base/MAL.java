package View.Base;
import View.MainFrame;
import View.Cache_Sim.*;
import View.Base.Sub.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import Controller.CacheSim;

/**
 * The MAL (Main Action Listener) class handles user actions in the cache memory simulation application.
 * It implements the ActionListener interface and defines actions for various buttons and events.
 */
public class MAL implements ActionListener{
    private CacheSim cacheSim = CacheSim.GetCacheSim();
    private static Stack<String> panelStack = new Stack<>();
    private static boolean bool = false;
    private static boolean bool2 = false;
    private ArrayList<BasePanel> panelList;
    private BasePanel basePanel;
    private String actionCommand;
    private MainFrame mainFrame;
    private int index = -1;
    private static int counter = -1;
    private static int modulo = 0;

    /**
     * Constructs a new MAL instance with the specified BasePanel and action command.
     *
     * @param basePanel      The BasePanel associated with the ActionListener.
     * @param actionCommand  The action command associated with the ActionListener.
     */
    public MAL(BasePanel basePanel, String actionCommand){
        this.basePanel = basePanel;
        this.actionCommand = actionCommand;
    }

    /**
     * Constructs a new MAL instance with the specified BasePanel, action command, and index.
     *
     * @param basePanel      The BasePanel associated with the ActionListener.
     * @param actionCommand  The action command associated with the ActionListener.
     * @param index          The index associated with the ActionListener.
     */
    public MAL(BasePanel basePanel, String actionCommand, int index){
        this.basePanel = basePanel;
        this.actionCommand = actionCommand;
        this.index = index;
    }

    /**
     * Handles action events triggered by buttons and events in the GUI.
     * A switch case catches the actionCommand sent by the button which
     * progresses the program
     * 
     * @param e The ActionEvent object representing the event.
     */
    @Override
    public void actionPerformed(ActionEvent e){
        this.mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(basePanel);
        this.panelList = mainFrame.GetPanelList();
        IntroPanel introPanel = (IntroPanel) mainFrame.GetPanelList().get(0);
        UnitPanel unitPanel = (UnitPanel) mainFrame.GetPanelList().get(6);
        DetailsPanel detailsPanel = (DetailsPanel) mainFrame.GetPanelList().get(8);
        SimulatePanel simulatePanel = (SimulatePanel) mainFrame.GetPanelList().get(9);
        ViewCachePanel viewCachePanel = (ViewCachePanel) mainFrame.GetPanelList().get(10);
        FinishPanel finishPanel = (FinishPanel) mainFrame.GetPanelList().get(11);

        switch(actionCommand){
            case "Exit":
                System.exit(0);
                break;

            case "Go Back":
                GoBack(mainFrame);
                break;

            case "StopBGM":
                if (introPanel.GetBGMClip() != null && introPanel.GetBGMClip().isRunning())
                    introPanel.GetBGMClip().stop();
                else
                    introPanel.GetBGMClip().start();
                break;

            case "CheckInput":
                if (checkInput(0))
                    Show("SetupPanel2");
                else
                    ShowAlert(0, "Invalid Input");
                break;

            case "CheckInput2":
                if (checkInput(1))
                    Show("ChoicePanel");
                else
                    ShowAlert(0, "Invalid Input");
                break;

            case "SetMemWord":
                    bool = false;
                    unitPanel.UpdateVariation(0);
                    Show("UnitPanel");
                break;

            case "SetMemBlock":
                    bool = true;
                    unitPanel.UpdateVariation(1);
                    Show("UnitPanel");
                break;

            case "CheckInput3":
                if (bool){
                    if (checkInput(3))
                        Show("FlowPanel");
                    else
                        ShowAlert(0, "Invalid Input");
                }
                else{
                    if (checkInput(2))
                        Show("FlowPanel");
                    else
                        ShowAlert(0, "Invalid Input");
                }
                break;

            case "DetailsPanel":
                bool = false;
                cacheSim.SetProgramFlow(index);
                detailsPanel.UpdateVariation(index);
                Show(actionCommand);
                break;

            case "SimulatePanel":
                if (!bool){
                    cacheSim.InitializeCache_and_Memory();
                    cacheSim.startProgramFlow();
                    simulatePanel.InitializePanel();
                    viewCachePanel.InitializePanel();
                    bool = true;
                    simulatePanel.InitializeLabels();
                    Show(actionCommand);
                }
                break;

            case "View":
                viewCachePanel.UpdateVariation(counter);
                Show("ViewCachePanel");
                break;

            case "Forward":
                if(counter < cacheSim.GetCache().getCacheStates().size() - 1){
                    if(modulo % 2 == 0)
                        counter++;
                    simulatePanel.UpdateLabels(counter);
                    simulatePanel.UpdateValues(counter);
                    modulo++;
                }
                else if (!bool2){
                    simulatePanel.UpdateLabels(counter);
                    simulatePanel.UpdateValues(counter);
                    bool2 = true;
                }
                break;

            case "Backward":
                if(counter > 0){
                    bool2 = false;
                    counter--;
                    simulatePanel.UpdateLabels(counter);
                    simulatePanel.BackTrack(counter);
                    modulo = 1;
                }
                break;

            case "Skip":
                if (counter != cacheSim.GetCache().getCacheStates().size() - 1){
                    bool2 = false;
                    simulatePanel.ShowButton();
                    counter = (cacheSim.GetCache().getCacheStates().size() - 1);
                    simulatePanel.UpdateLabels(counter);
                    simulatePanel.UpdateValues(counter);
                    simulatePanel.UpdateLabels2(counter);
                    modulo = 1;
                }
                break;

            case "Restart":
                if (counter != 0){
                    bool2 = false;
                    counter = 0;
                    simulatePanel.UpdateLabels(counter);
                    simulatePanel.UpdateValues(counter);
                    simulatePanel.UpdateLabels2(counter);
                    modulo = 1;
                }
                break;

            case "FinishPanel":
                finishPanel.UpdateVariation();
                Show(actionCommand);
                break;

            case "Generate":
                cacheSim.generateTextFile();
                System.exit(0);
                break;

            default:
                Show(actionCommand);
        }
    }

    /**
     * Retrieves and removes items from the panelStack.
     * 
     * @param count The number of items to retrieve and remove.
     * @return The retrieved item from the panelStack.
     */
    public String Pop(int count){
        String str = null;
        for (int i = 0; i < count; i++)
            str = panelStack.pop();
        return str;
    }

    /**
     * Shows a panel and pushes it onto the panelStack.
     * 
     * @param actionCommand The action command associated with the panel to show.
     * @return Always returns false.
     */
    public void Show(String actionCommand){
        panelStack.push(actionCommand);
        mainFrame.ShowPanel(actionCommand);

    }

    /**
     * Goes back to the previous panel and updates the panelStack.
     *
     * @param mainFrame The MainFrame instance.
     */
    public void GoBack(MainFrame mainFrame){
        String prevPanel = Pop(2);
        panelStack.push(prevPanel);
        mainFrame.ShowPanel(prevPanel);
    }

    /**
     * Shows an alert panel with the specified type and message.
     *
     * @param type    The type of alert panel (0 for Warning, 1 for Success).
     * @param display The message to be displayed in the alert panel.
     */
    public void ShowAlert(int type, String display){
        if(type == 0){
            WarningPanel warningPanel = (WarningPanel) panelList.get(3);
            warningPanel.UpdateVariation(display);
            Show("WarningPanel");
        }
        else if (type == 1){
            SuccessPanel successPanel = (SuccessPanel) panelList.get(4);
            successPanel.UpdateVariation(display);
            Show("SuccessPanel");
        }
    }

    /**
     * Receives the values got from the text field panels and
     * initializes the settings of the cache and program flow accordingly
     * 
     * @param type  The type of check that will be performed based on user input
     */
    private boolean checkInput(int type){
        TextFieldPanel textFieldPanel = (TextFieldPanel) basePanel;
        String textInput = textFieldPanel.GetText();
        int input;

        try{
            input = Integer.parseInt(textInput);
            switch(type){
                case 0:
                    if (input < 1 || input > 5)
                        return false;
                    cacheSim.SetBlockSize(input);
                    break;
                case 1:
                    if (input < 1 || input > 8)
                        return false;
                    cacheSim.SetSetSize(input);
                    break;
                case 2:
                    if (input < 1 || input > 20)
                        return false;
                    cacheSim.SetMMSize((int) (Math.pow(2, input) / cacheSim.GetBlockSize()));
                    break;
                case 3:
                    if (input < 1 || input > 32768)
                        return false;
                    cacheSim.SetMMSize(input);
                    break;
            }
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}