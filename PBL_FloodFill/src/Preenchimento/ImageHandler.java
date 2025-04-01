package Preenchimento;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageHandler {
    public int[][] loadImage(String filePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(filePath));
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] pixels = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y][x] = image.getRGB(x, y);
            }
        }
        return pixels;
    }
    public void saveImage(int[][] pixelMatrix, String outputPath) throws IOException {
        int height = pixelMatrix.length;
        int width = pixelMatrix[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, pixelMatrix[y][x]);
            }
        }
        ImageIO.write(image, "png", new File(outputPath));
    }
}
