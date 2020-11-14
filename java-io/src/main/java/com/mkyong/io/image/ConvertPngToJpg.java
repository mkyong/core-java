package com.mkyong.io.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConvertPngToJpg {

    public static void main(String[] args) throws IOException {

        Path source = Paths.get("C:\\test\\javanullpointer.png");
        Path target = Paths.get("C:\\test\\new.jpg");

        BufferedImage originalImage = ImageIO.read(source.toFile());

        // jpg needs BufferedImage.TYPE_INT_RGB
        // png needs BufferedImage.TYPE_INT_ARGB

        // create a blank, RGB, same width and height
        BufferedImage newBufferedImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        // draw a white background and puts the originalImage on it.
        newBufferedImage.createGraphics()
                .drawImage(originalImage,
                        0,
                        0,
                        Color.WHITE,
                        null);

        // save an image
        ImageIO.write(newBufferedImage, "jpg", target.toFile());

    }

}
