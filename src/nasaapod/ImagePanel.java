package nasaapod;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * This class extends JPanel and wraps around a displayed image.
 *
 * @author Justin Beringer
 */
public class ImagePanel extends JPanel {

    Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }

    /**
     * Creates a new Image, scaled according to the given parameters. If
     * only one of the height and width parameters is given, the aspect ratio is
     * maintained around it.
     */
    public ImagePanel(Image image, int width, int height) {

        if (width != 0 && height != 0) {
            this.image = image.getScaledInstance(width, height, 0);
        } else if (width != 0 && height == 0) {
            this.image = image.getScaledInstance(width, -1, 0);
        } else if (width == 0 && height != 0) {
            this.image = image.getScaledInstance(-1, height, 0);
        } else {
            this.image = image;
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(null), image.getHeight(null));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawString("test", 10, 10);
        g2d.drawImage(image, 0, 0, null);
    }

}
