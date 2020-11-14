package com.mkyong.io.image;

import com.mkyong.io.utils.FileResourcesUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

// https://mkyong.com/java/how-to-resize-an-image-in-java/
public class ResizeImage {

    private static final int IMG_WIDTH = 300;
    private static final int IMG_HEIGHT = 150;

    public static void main(String[] args) throws IOException {

        /*Path source = Paths.get("C:\\test\\google.png");
        Path target = Paths.get("C:\\test\\resize.png");

        try (InputStream is = new FileInputStream(source.toFile())) {
            resize(is, target, IMG_WIDTH, IMG_HEIGHT);
        }*/

        Path target = Paths.get("C:\\test\\resize.png");
        try (InputStream is = FileResourcesUtils.getFileFromResourceFolder("images/google.png")) {
            resize(is, target, IMG_WIDTH, IMG_HEIGHT);
        }

    }

    private static void resize2(InputStream input, Path target,
                                int width, int height) throws IOException {

        BufferedImage originalImage = ImageIO.read(input);

        /**
         * https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/Image.html
         * SCALE_AREA_AVERAGING - Area Averaging image scaling algorithm.
         * SCALE_DEFAULT - Use the default image-scaling algorithm.
         * SCALE_FAST - Higher priority to scaling speed than smoothness of the scaled image.
         * SCALE_REPLICATE - Use the image scaling algorithm embodied in the ReplicateScaleFilter class.
         * SCALE_SMOOTH - Higher priority to image smoothness than scaling speed.
         */
        Image newResizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_REPLICATE);

        String s = target.getFileName().toString();
        String fileExtension = s.substring(s.lastIndexOf(".") + 1);

        // we want image in png format
        ImageIO.write(convertToBufferedImage(newResizedImage),
                fileExtension, target.toFile());

    }


    private static void resize(InputStream input, Path target,
                               int width, int height) throws IOException {

        // read an image to BufferedImage for processing
        BufferedImage originalImage = ImageIO.read(input);

        // create a new BufferedImage for drawing
        BufferedImage newResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newResizedImage.createGraphics();

        //g.setBackground(Color.WHITE);
        //g.setPaint(Color.WHITE);

        // background transparent
        g.setComposite(AlphaComposite.Src);
        g.fillRect(0, 0, width, height);

        /*
        // VALUE_RENDER_DEFAULT = good tradeoff of performance vs quality
        // VALUE_RENDER_SPEED   = prefer speed
        // VALUE_RENDER_QUALITY = prefer quality
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // controls how image pixels are filtered or resampled
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // antialiasing, on
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);*/

        Map<RenderingHints.Key, Object> hints = new HashMap<>();
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.addRenderingHints(hints);

        // puts the original image into the newResizedImage
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        String s = target.getFileName().toString();
        String fileExtension = s.substring(s.lastIndexOf(".") + 1);

        // we want image in png format
        ImageIO.write(newResizedImage, fileExtension, target.toFile());

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
