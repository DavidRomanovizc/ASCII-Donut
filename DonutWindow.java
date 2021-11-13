package me.tylermoser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DonutWindow extends JFrame {

    JLabel label;
    ImageIcon donutImageIcon;
    int imageWidth;
    int imageHeight;

    public DonutWindow(int imageWidth, int imageHeight) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Donut Rendering");
        setSize(1280, 720);
        donutImageIcon = new ImageIcon();
        label = new JLabel(donutImageIcon);
        add(label, BorderLayout.CENTER);
        setVisible(true);
    }

    public void updateDonut(int[] pixelData) {
        BufferedImage image = new BufferedImage(imageWidth, imageHeight + 1, BufferedImage.TYPE_INT_ARGB);
        int row = 0;
        for (int i = 0; i < imageWidth * imageHeight; i++) {
            if (i % imageWidth > 0) {
                int rgbValue = Math.max(pixelData[i], 0);
                int rgb = new Color(rgbValue, rgbValue, rgbValue).getRGB();
                image.setRGB(i % imageWidth, row, rgb);
            } else {
                row++;
            }
        }
        donutImageIcon.setImage(image);
        label.repaint();
    }
}
