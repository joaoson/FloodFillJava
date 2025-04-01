package Preenchimento;

import EstruturasDeDados.Pending;
import EstruturasDeDados.Queue;
import EstruturasDeDados.Stack;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FloodFill {
    private int frameCounter = 0;
    private int modifiedCounter = 0;
    private final int SAVE_INTERVAL = 30;
    private AnimationCanvas canvas;
    private ImageHandler imageHandler = new ImageHandler();
    private int colorTolerance = 5;

    public void setAnimationCanvas(AnimationCanvas canvas) {
        this.canvas = canvas;
    }

    public void setColorTolerance(int tolerance) {
        this.colorTolerance = tolerance;
    }

    public void fillStack(int[][] image, int x, int y, int newColor) throws IOException {
        Stack<PixelPosition> stack = new Stack<>();
        int originalColor = image[y][x];
        fill(image, stack, x, y, originalColor, newColor);
    }

    public void fillQueue(int[][] image, int x, int y, int newColor) throws IOException {
        Queue<PixelPosition> queue = new Queue<>();
        int originalColor = image[y][x];
        fill(image, queue, x, y, originalColor, newColor);
    }

    private void fill(int[][] image, Pending<PixelPosition> structure, int x, int y, int originalColor, int newColor) throws IOException {
        if (originalColor == newColor) return;
        structure.add(new PixelPosition(x, y, originalColor));

        File directory = new File("./imagens");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        while (!structure.empty()) {
            PixelPosition p = structure.remove();
            int px = p.x;
            int py = p.y;

            if (px < 0 || py < 0 || py >= image.length || px >= image[0].length) continue;
            if (!isColorWithinTolerance(image[py][px], originalColor, colorTolerance)) continue;

            image[py][px] = newColor;
            modifiedCounter++;

            if (modifiedCounter % SAVE_INTERVAL == 0) {
                frameCounter++;

                int[][] frameCopy = makeImageCopy(image);

                String frameName = String.format("./imagens/frame_%04d.png", frameCounter);
                imageHandler.saveImage(frameCopy, frameName);

                if (canvas != null) {
                    BufferedImage frameImage = convertToBufferedImage(frameCopy);
                    canvas.addFrame(frameImage);
                }
            }

            structure.add(new PixelPosition(px + 1, py, originalColor));
            structure.add(new PixelPosition(px - 1, py, originalColor));
            structure.add(new PixelPosition(px, py + 1, originalColor));
            structure.add(new PixelPosition(px, py - 1, originalColor));
        }
    }

    private boolean isColorWithinTolerance(int color1, int color2, int tolerance) {
        int r1 = (color1 >> 16) & 0xff;
        int g1 = (color1 >> 8) & 0xff;
        int b1 = color1 & 0xff;

        int r2 = (color2 >> 16) & 0xff;
        int g2 = (color2 >> 8) & 0xff;
        int b2 = color2 & 0xff;

        int diff = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
        return diff <= tolerance;
    }

    private int[][] makeImageCopy(int[][] original) {
        int[][] copy = new int[original.length][original[0].length];
        for (int y = 0; y < original.length; y++) {
            System.arraycopy(original[y], 0, copy[y], 0, original[y].length);
        }
        return copy;
    }

    private BufferedImage convertToBufferedImage(int[][] pixels) {
        int height = pixels.length;
        int width = pixels[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, pixels[y][x]);
            }
        }

        return image;
    }
}