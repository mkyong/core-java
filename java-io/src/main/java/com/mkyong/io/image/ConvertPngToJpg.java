package com.mkyong.io.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConvertPngToJpg {

    public static void main(String[] args) throws IOException {

        Path source = Paths.get("C:\\test\\google.png");
        Path target = Paths.get("C:\\test\\resize.png");

        BufferedImage originalImage = ImageIO.read(source.toFile());

        // jpg needs BufferedImage.TYPE_INT_RGB
        // png needs BufferedImage.TYPE_INT_ARGB

        // create a blank, RGB, same width and height, and a white background
        BufferedImage finalImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        finalImage.createGraphics()
                .drawImage(originalImage, 0, 0, Color.WHITE, null);

        /*Graphics2D g = finalImage.createGraphics();
        g.setBackground(Color.WHITE);
        g.fillRect(0, 0, width, height);*/
        
        /*Path target = Paths.get("C:\\test\\resize.png");
        try (InputStream is = FileResourcesUtils.getFileFromResourceFolder("images/google.png")) {
            resize(is, target, IMG_WIDTH, IMG_HEIGHT);
        }*/

    }
}
