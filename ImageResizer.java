import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ImageResizer {

    public static void main(String[] args){
        String directoryPath = "";
        String imagePath = "";
        String imageName = "";
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Insert target directory path:");
        directoryPath = inputScanner.nextLine();

        createDirectory(directoryPath);

        System.out.println("Insert image path:");
        imagePath = inputScanner.nextLine();

        System.out.println("Insert image name:");
        imageName = inputScanner.nextLine();

        resizeImage(directoryPath, imagePath, imageName);
    }
    private static void resizeImage(String directoryPath, String sourceImagePath, String imageName){
        // Resizing is made following the rules found on developer.android site:
        // https://developer.android.com/training/multiscreen/screendensities

        /*
        To create alternative bitmap drawables for different densities,
        you should follow the 3:4:6:8:12:16 scaling ratio between the six primary densities.
        For example, if you have a bitmap drawable that's 48x48 pixels for medium-density screens, all the different sizes should be:

            36x36 (0.75x) for low-density (ldpi)
            48x48 (1.0x baseline) for medium-density (mdpi)
            72x72 (1.5x) for high-density (hdpi)
            96x96 (2.0x) for extra-high-density (xhdpi)
            144x144 (3.0x) for extra-extra-high-density (xxhdpi)
            192x192 (4.0x) for extra-extra-extra-high-density (xxxhdpi)
         */

        if (new File(directoryPath + "\\drawable-mdpi\\" + imageName + ".png").exists()){
            System.out.println("A file with that name already exist.");
            return;
        }

        BufferedImage sourceImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);

        try {
            sourceImage = ImageIO.read(new File(sourceImagePath));
        } catch (IOException e) {
            System.out.println("I cannot read that image.");
        }
        try {
            ImageIO.write(sourceImage, "png",
                    new File(directoryPath + "\\drawable-mdpi\\" + imageName + ".png"));
        } catch (IOException e) {
            System.out.println("Error saving image in drawable-mdpi folder");
        }
        int height = sourceImage.getHeight();
        int width = sourceImage.getWidth();

        Image newImage = sourceImage.getScaledInstance(width * 75 / 100, height * 75 / 100, Image.SCALE_SMOOTH);
        try {
            ImageIO.write(toBufferedImage(newImage), "png",
                    new File(directoryPath + "\\drawable-ldpi\\" + imageName + ".png"));
        } catch (IOException e) {
            System.out.println("Error saving image in drawable-ldpi folder");
        }

        newImage = sourceImage.getScaledInstance((int)Math.round(width * 1.5), (int)Math.round(height * 1.5), Image.SCALE_SMOOTH);
        try {
            ImageIO.write(toBufferedImage(newImage), "png",
                    new File(directoryPath + "\\drawable-hdpi\\" + imageName + ".png"));
        } catch (IOException e) {
            System.out.println("Error saving image in drawable-hdpi folder");
        }

        newImage = sourceImage.getScaledInstance((int)Math.round(width * 2), (int)Math.round(height * 2), Image.SCALE_SMOOTH);
        try {
            ImageIO.write(toBufferedImage(newImage), "png",
                    new File(directoryPath + "\\drawable-xhdpi\\" + imageName + ".png"));
        } catch (IOException e) {
            System.out.println("Error saving image in drawable-xhdpi folder");
        }

        newImage = sourceImage.getScaledInstance((int)Math.round(width * 3), (int)Math.round(height * 3), Image.SCALE_SMOOTH);
        try {
            ImageIO.write(toBufferedImage(newImage), "png",
                    new File(directoryPath + "\\drawable-xxhdpi\\" + imageName + ".png"));
        } catch (IOException e) {
            System.out.println("Error saving image in drawable-xxhdpi folder");
        }

        newImage = sourceImage.getScaledInstance((int)Math.round(width * 4), (int)Math.round(height * 4), Image.SCALE_SMOOTH);
        try {
            ImageIO.write(toBufferedImage(newImage), "png",
                    new File(directoryPath + "\\drawable-xxxhdpi\\" + imageName + ".png"));
        } catch (IOException e) {
            System.out.println("Error saving image in drawable-xxxhdpi folder");
        }
    }

    private static void createDirectory(String path){
        File direcory = new File(path);

        if (!direcory.exists()){
            System.out.println("This directory does not exist.");
            return;
        }
        try {
            Files.createDirectory(Paths.get(path, "drawable-ldpi"));
            Files.createDirectory(Paths.get(path, "drawable-mdpi"));
            Files.createDirectory(Paths.get(path, "drawable-hdpi"));
            Files.createDirectory(Paths.get(path, "drawable-xhdpi"));
            Files.createDirectory(Paths.get(path, "drawable-xxhdpi"));
            Files.createDirectory(Paths.get(path, "drawable-xxxhdpi"));
        } catch (IOException e) {
            System.out.println("Nothing changed, directories already exists.");
        }
    }

    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
