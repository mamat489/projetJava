import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class DisplayImage {


    public static void main(String[] args) {
        try {
            BufferedImage img = ImageIO.read(new File("/home/mamat/Images/bomber.jpg"));
            ImageIcon icon = new ImageIcon(img);
            JLabel label = new JLabel(icon);
            JOptionPane.showMessageDialog(null, label);

            JFrame frame=new JFrame();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}