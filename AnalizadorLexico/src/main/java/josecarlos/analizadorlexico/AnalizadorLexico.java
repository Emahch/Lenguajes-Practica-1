package josecarlos.analizadorlexico;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author emahch
 */
public class AnalizadorLexico {

    public static void main(String[] args) {
        FramePrincipal frame = new FramePrincipal();
        frame.setVisible(true);
        
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//        }
//        AnalizadorLexico analizador = new AnalizadorLexico();
//        analizador.createImage(panel);
    }

    public void createImage(JPanel panel) {
        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.print(g);
        g.dispose();
        try {
            File outputfile = new File("saved.png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            // handle exception
        }
    }
}
