import java.awt.*;
import javax.swing.*;
public class ImageCanvas extends JComponent{
    Image image;
    ImageCanvas(Image image) {
        setSize(image.getWidth(this), image.getHeight(this));
        this.image = image;
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);
    }
}
