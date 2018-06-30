package Teste;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.net.URL;
import javax.swing.ImageIcon;
/**
 *
 * @author BrunoCA-xD
 */
public class JImagePanel extends javax.swing.JLayeredPane {

    private Image image = null;
    private ImageIcon ImgIco = null;

    public JImagePanel() {
        super();
    }

    public JImagePanel(LayoutManager lm) {
        super();
        setLayout(lm);
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (Exception e) {
            throw e;
        } finally {
            super.paintComponent(g);
        }
    }

    /**
     *
     * @param image Represents the image to be the background
     */
    public void setBackground(Image image) {
        this.image = image;
    }

    /**
     *
     * @param imageURL Represents the image path to be the background
     */
    public void setBackground(URL imageURL) {
        this.ImgIco = new ImageIcon(imageURL); //create a new image icon 
        this.image = ImgIco.getImage(); // sets the image
        setOpaque(false);//ensures that the opaque property is false

    }
}
