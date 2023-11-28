package View.Base;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.*;

/**
 * The BaseButton class represents a custom button in the GUI.
 * It extends the JButton class and provides additional functionality for setting the button appearance,
 * playing a click sound on button press, and defining different button types.
*/
public class BaseButton extends JButton{

    private Clip clickSoundClip;
    
    /**
     * Constructs a BaseButton with the specified button type and text.
     *
     * @param type The type of the button.
     * @param text The text displayed on the button.
    */
    public BaseButton(int type, String text){
        SetButton();

        try {
            InputStream clickSoundStream = BaseButton.class.getResourceAsStream("/Assets/Buttons/click.wav");
            if (clickSoundStream != null) {
                BufferedInputStream bufferedClickSoundStream = new BufferedInputStream(clickSoundStream);
                clickSoundClip = AudioSystem.getClip();
                clickSoundClip.open(AudioSystem.getAudioInputStream(bufferedClickSoundStream));
            }else{
                System.err.println("Click sound resource not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageIcon img = null;
        switch (type){
            case 1:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/long_button.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 500, 100);
                break;

            case 2:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/button_small.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 200, 200);
                break;

            case 3:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/short_button.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 200, 75);
                break;
            
            case 4:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/med_button.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 350, 100);
                break;
                
            case 5:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/enter_button.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 150, 150);
                break;

            case 6:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/exit_button.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 75, 75);
                break;      

            case 7:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/music_button.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 75, 75);
                break;

            case 8:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/forward_button.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 150, 150);
                break;

            case 9:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/backward_button.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 150, 150);
                break;

            case 10:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/restart_button.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 150, 150);
                break;

            case 11:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/skip_button.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 150, 150);
                break;

            case 12:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/view_button.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 100, 100);
                break;

            case 13:
                img = new ImageIcon(getClass().getResource("/Assets/Buttons/finish_button.png"));
                this.setIcon(img);
                this.setBounds(0, 0, 100, 100);
                break;
        }

        this.setText(text);
    }

    /**
     * Sets the appearance and properties of the button.
    */
    public void SetButton(){
        this.setContentAreaFilled(false);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setFocusable(false);
        this.setBorderPainted(false);
        this.setForeground(Color.white);
        this.setFont(StaticFont.GetDoctorGlitchSmallFont());
        this.setText(null);
        this.setVisible(true);   
    }

    @Override
    public void addActionListener(ActionListener listener) {
        super.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clickSoundClip != null) {
                    clickSoundClip.setFramePosition(0);
                    clickSoundClip.start();
                }

                if (listener != null) {
                    listener.actionPerformed(e);
                }
            }
        });
    }
}
