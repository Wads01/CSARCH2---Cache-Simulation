package View.Base;
import java.awt.*;
import java.io.*;


/**
 * The StaticDoctorGlitchFont class provides static methods and variables for accessing different sizes of the DoctorGlitch font.
 * It loads the font from a resource file and registers it with the local graphics environment.
*/
public class StaticFont{
    private static Font DoctorGlitch_smallest;
    private static Font DoctorGlitch_small;
    private static Font DoctorGlitch_medium;
    private static Font DoctorGlitch_large;

    static{
        try{
            InputStream fontStream = StaticFont.class.getResourceAsStream("/Assets/Font/CrackedCode.ttf");
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(baseFont);

            DoctorGlitch_smallest = baseFont.deriveFont(50f);
            DoctorGlitch_small = baseFont.deriveFont(60f);
            DoctorGlitch_medium = baseFont.deriveFont(80f);
            DoctorGlitch_large = baseFont.deriveFont(85f);
        }
        catch(IOException | FontFormatException e){
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the DoctorGlitch font with the smallest size.
     *
     * @return The smallest size DoctorGlitch font.
    */
    public static Font GetDoctorGlitchSmallestFont() {
        return DoctorGlitch_smallest;
    }

    /**
     * Retrieves the DoctorGlitch font with a small size.
     *
     * @return The small size DoctorGlitch font.
    */
    public static Font GetDoctorGlitchSmallFont() {
        return DoctorGlitch_small;
    }

    /**
     * Retrieves the DoctorGlitch font with a medium size.
     *
     * @return The medium size DoctorGlitch font.
    */
    public static Font GetDoctorGlitchMediumFont() {
        return DoctorGlitch_medium;
    }

    /**
     * Retrieves the DoctorGlitch font with a large size.
     *
     * @return The large size DoctorGlitch font.
    */
    public static Font GetDoctorGlitchLargeFont() {
        return DoctorGlitch_large;
    }
}
