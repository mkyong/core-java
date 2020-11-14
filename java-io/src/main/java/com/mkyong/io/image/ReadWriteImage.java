package com.mkyong.io.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadWriteImage {

    // Google logo url
    private static final String GOOGLE_LOGO =
            "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";

    public static void main(String[] args) {

        try {

            URL url = new URL(GOOGLE_LOGO);

            // all suported formats
            // String writerNames[] = ImageIO.getWriterFormatNames();
            // Arrays.stream(writerNames).forEach(System.out::println);

            // read an image from a file
            //BufferedImage image = ImageIO.read(new File("c:\\image\\mypic.jpg"));

            // read an image from url
            BufferedImage image = ImageIO.read(url);

            // resize image to 300x150
            Image scaledImage = image.getScaledInstance(300, 150, Image.SCALE_DEFAULT);

            // save the resize image aka thumbnail
            ImageIO.write(
                    convertToBufferedImage(scaledImage),
                    "png",
                    new File("C:\\test\\google.png"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done");

    }

    public static BufferedImage convertToBufferedImage(Image img) {

        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bi = new BufferedImage(
                img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = bi.createGraphics();
        graphics2D.drawImage(img, 0, 0, null);
        graphics2D.dispose();

        return bi;
    }

}
