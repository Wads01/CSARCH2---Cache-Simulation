package View.Cache_Sim;
import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.*;
import javax.swing.*;
import View.Base.*;
import java.awt.*;
import View.Base.Sub.HeaderPanel;

/**
 * The IntroPanel class represents the introductory panel of the application.
 * It extends the BasePanel class and provides a start button and background music.
*/
public class IntroPanel extends BasePanel{

    private Clip bgmClip;

    /**
     * Constructs an IntroPanel with the necessary components.
    */
    public IntroPanel(){
        HeaderPanel headerPanel = new HeaderPanel("Cache Simulator");
        BasePanel bodyPanel = new BasePanel(50, 100, 1250, 600);

        ImageIcon cpu = new ImageIcon(getClass().getResource("/Assets/cpu.gif"));
        BaseButton button = new BaseButton(4, "START");
        MAL listener = new MAL(IntroPanel.this, "SetupPanel");
        BaseLabel label = new BaseLabel("", null);
        

        BasePanel panels[] = {
            new BasePanel(400, 0, 480, 420),
            new BasePanel(450, 450, 350, 100)
        };

        label.setIcon(cpu);
        label.setBounds(0,-40,480,480);
        button.addActionListener(listener);

        panels[0].add(label);
        panels[1].add(button);
        bodyPanel.add(panels[0]);
        bodyPanel.add(panels[1]);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bodyPanel, BorderLayout.CENTER);

        PlayBGM();
    }

    /**
     * Plays the background music for the panel.
    */
    public void PlayBGM() {
        try {
            InputStream bgmStream = getClass().getResourceAsStream("/Assets/bgm.wav");
            if (bgmStream != null) {
                BufferedInputStream bufferedBgmStream = new BufferedInputStream(bgmStream);
                bgmClip = AudioSystem.getClip();
                bgmClip.open(AudioSystem.getAudioInputStream(bufferedBgmStream));
                bgmClip.loop(Clip.LOOP_CONTINUOUSLY);

            }
            else{
                System.err.println("BGM resource not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Clip object representing the background music.
     * @return The Clip object representing the background music.
    */
    public Clip GetBGMClip(){
        return bgmClip;
    }
}
