package nasaapod;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Displays NASA's Astronomy Picture of the Day using the APOD API.
 *
 * @author Justin Beringer
 */
public class ApodView {

    static final int WINDOW_WIDTH = 800;
    static final Logger LOGGER = Logger.getLogger(ApodView.class.getName());
    static Apod apod;
    
    /** 
     * Waits until image is loaded before displaying the program window. 
     **/
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeAndWait(() -> {
                apod = new Apod();
                createAndShowGUI();
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(ApodView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void createAndShowGUI() {

        JFrame frame = new JFrame("NASA Astronomy Picture of the Day");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel verticalPane = new JPanel();
        verticalPane.setLayout(new BoxLayout(verticalPane, BoxLayout.Y_AXIS));

        ImagePanel imagePanel = new ImagePanel(apod.getImage(), WINDOW_WIDTH, 0);
        imagePanel.isOpaque();

        JTextArea textArea = new JTextArea(apod.getBasicInfo());
        textArea.setEditable(false);
        textArea.setBackground(Color.white);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 17));
        textArea.setForeground(Color.black);
        //textArea.setForeground(new Color(209, 45, 0));
        textArea.setMargin(new Insets(2, 2, 2, 2));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(WINDOW_WIDTH, 150));
        scrollPane.setMaximumSize(new Dimension(WINDOW_WIDTH, 150));

        verticalPane.add(imagePanel);
        verticalPane.add(scrollPane);

        frame.add(verticalPane);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}
