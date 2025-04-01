import Preenchimento.AnimationCanvas;
import Preenchimento.FloodFill;
import Preenchimento.ImageHandler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ImageHandler handler = new ImageHandler();
        FloodFill floodFill = new FloodFill();

        AnimationCanvas animationCanvas = new AnimationCanvas(100);

        floodFill.setAnimationCanvas(animationCanvas);

        String caminho = "";
        int[][] imagem = null;

        while (true) {
            try {
                if (caminho.isEmpty()) {
                    System.out.print("Digite o caminho da imagem PNG: ");
                    caminho = scanner.nextLine();
                }

                imagem = handler.loadImage(caminho);
                System.out.println("Imagem carregada!");
                System.out.println("Altura: " + imagem.length + " pixels");
                System.out.println("Largura: " + imagem[0].length + " pixels");

                //System.out.println(imagem[400][400]);

                System.out.print("Informe a coordenada X: ");
                int x = scanner.nextInt();
                System.out.print("Informe a coordenada Y: ");
                int y = scanner.nextInt();
                scanner.nextLine();

                BufferedImage preview = ImageIO.read(new File(caminho));

                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        int mx = x + dx;
                        int my = y + dy;
                        if (mx >= 0 && my >= 0 && mx < preview.getWidth() && my < preview.getHeight()) {
                            preview.setRGB(mx, my, 0xFFFF0000);
                        }
                    }
                }

                ImageIO.write(preview, "png", new File("preview_coordenada.png"));

                System.out.println("Preview gerado: preview_coordenada.png");
                JFrame previewFrame = mostrarImagem("preview_coordenada.png");

                System.out.print("Deseja continuar com essa coordenada? (s/n): ");
                String confirmaCoord = scanner.nextLine();
                if (!confirmaCoord.equalsIgnoreCase("s")){
                    if (previewFrame.isDisplayable()) {
                        previewFrame.dispose();
                    }
                    continue;
                }

                previewFrame.dispose();

                System.out.println("Escolha a cor para preenchimento:");
                System.out.println("1 - Azul");
                System.out.println("2 - Amarelo");
                System.out.println("3 - Verde");
                System.out.println("4 - Vermelho");
                System.out.println("5 - Pink");
                System.out.print("Opção: ");
                int corEscolhida = Integer.parseInt(scanner.nextLine());
                int novaCor = switch (corEscolhida) {
                    case 1 -> 0xFF0000FF;
                    case 2 -> 0xFFFFFF00;
                    case 3 -> 0xFF00FF00;
                    case 4 -> 0xFFFF0000;
                    case 5 -> 0xFFFF69B4;
                    default -> throw new IllegalArgumentException("Opção inválida.");
                };

                System.out.println("A tolerância controla a semelhança de cores que serão preenchidas.");
                System.out.println("0 = apenas cores exatamente iguais");
                System.out.println("30 = cores visualmente semelhantes");
                System.out.println("100 = cores bastante diferentes");
                System.out.print("Digite a tolerância de cor (0-255, recomendado 30): ");
                int tolerance = Integer.parseInt(scanner.nextLine());
                if (tolerance < 0) tolerance = 0;
                if (tolerance > 255) tolerance = 255;
                floodFill.setColorTolerance(tolerance);
                System.out.println("Tolerância definida para: " + tolerance);

                System.out.print("Deseja usar PILHA ou FILA? (p/f): ");
                String estrutura = scanner.nextLine().toLowerCase();


                int[][] imagemFinal = handler.loadImage(caminho);
                if (estrutura.equals("f")) {
                    floodFill.fillQueue(imagemFinal, x, y, novaCor);
                } else {
                    floodFill.fillStack(imagemFinal, x, y, novaCor);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                animationCanvas.showInWindow("Flood Fill Animation");
                animationCanvas.startAnimation();

                handler.saveImage(imagemFinal, "resultado.png");
                System.out.println("Imagem salva como resultado.png");

                System.out.println("Animação sendo exibida na janela!");
                System.out.println("Total de " + animationCanvas.getFrameCount() + " frames.");

                break;

            } catch (IOException e) {
                System.out.println("Erro ao carregar ou salvar imagem: " + e.getMessage());
                caminho = "";
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido. Tente novamente.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                caminho = "";
            }
        }

        scanner.close();
    }

    public static JFrame mostrarImagem(String caminho) throws IOException {
        JFrame frame = new JFrame("Visualização");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        BufferedImage img = ImageIO.read(new File("preview_coordenada.png"));
        ImageIcon icon = new ImageIcon(img);
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        return frame;
    }
}