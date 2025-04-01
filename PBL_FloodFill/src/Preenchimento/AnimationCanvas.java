package Preenchimento;

import EstruturasDeDados.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class AnimationCanvas extends JPanel {
    private Node<BufferedImage> firstFrame;
    private Node<BufferedImage> lastFrame;
    private int frameCount;

    private Node<BufferedImage> currentFrame;

    private int frameDelay;

    private Timer animationTimer;

    public AnimationCanvas(int frameDelayMs) {
        firstFrame = null;
        lastFrame = null;
        currentFrame = null;
        frameCount = 0;
        frameDelay = frameDelayMs;

        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.BLACK);

        animationTimer = new Timer(frameDelay, e -> {
            if (currentFrame != null && currentFrame.next != null) {
                currentFrame = currentFrame.next;
            } else {
                currentFrame = firstFrame;
            }

            repaint();
        });
    }

    public void addFrame(BufferedImage frame) {
        Node<BufferedImage> newNode = new Node<>(frame);

        if (firstFrame == null) {
            firstFrame = newNode;
            lastFrame = newNode;
            currentFrame = newNode;
        } else {
            lastFrame.next = newNode;
            lastFrame = newNode;
        }

        frameCount++;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void startAnimation() {
        if (firstFrame != null) {
            currentFrame = firstFrame;
            animationTimer.start();
        }
    }


    public void stopAnimation() {
        animationTimer.stop();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (currentFrame != null) {
            BufferedImage image = currentFrame.data;

            int x = (getWidth() - image.getWidth()) / 2;
            int y = (getHeight() - image.getHeight()) / 2;

            g.drawImage(image, x, y, null);

            g.setColor(Color.WHITE);

            int currentFrameNumber = 1;
            Node<BufferedImage> temp = firstFrame;
            while (temp != null && temp != currentFrame) {
                currentFrameNumber++;
                temp = temp.next;
            }

            g.drawString("Frame: " + currentFrameNumber + " / " + frameCount, 10, 20);
        }
    }


    public void showInWindow(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.toFront();
        frame.requestFocus();
    }
}