package josecarlos.analizadorlexico.utilidades;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author emahch
 */
public class ExportImportFiles {

    private File archivoSeleccionado;

    public ExportImportFiles(boolean directories) throws SolicitudCanceladaException {
        if (directories) {
            mostrarDialog(JFileChooser.DIRECTORIES_ONLY);
        } else {
            mostrarDialog(JFileChooser.FILES_ONLY);
        }
    }

    /**
     * Muestra un file chooser que permite al usuario seleccionar el archivo o carpeta
     * @param mode
     * @throws SolicitudCanceladaException si se cancela la solicitud
     */
    private void mostrarDialog(int mode) throws SolicitudCanceladaException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        if (mode == JFileChooser.DIRECTORIES_ONLY) {
            fileChooser.setDialogTitle("Guardar Imagen");
        } else if (mode == JFileChooser.FILES_ONLY) {
            fileChooser.setDialogTitle("Cargar Archivo");
        } else {
            return;
        }
        fileChooser.setFileSelectionMode(mode);

        int opcion = fileChooser.showOpenDialog(new JFrame());

        if (opcion == JFileChooser.APPROVE_OPTION) {
            archivoSeleccionado = fileChooser.getSelectedFile();
        } else {
            throw new SolicitudCanceladaException("Se ha cancelado la solicitud");
        }
    }

    /**
     * Lee el archivo de entrada y lo convierte a una cadena de texto
     * @return string texto del archivo de entrada
     */
    public String recibirArchivoEntrada() {
        try (FileReader fileReader = new FileReader(archivoSeleccionado); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String textoRecibido = "";
            String linea = bufferedReader.readLine();
            while (linea != null) {
                textoRecibido = textoRecibido + linea + "\n";
                linea = bufferedReader.readLine();
            }
            return textoRecibido;
        } catch (IOException e) {
            System.out.println("Error al recibir el archivo de entrada");
            return null;
        }
    }

    /**
     * se encarga de crear la imagen en base a un panel ingresado
     * @param panel 
     */
    public void createImage(JPanel panel) {
        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.print(g);
        g.dispose();
        try {
            File outputfile = new File(archivoSeleccionado.getAbsolutePath() + File.separator +"Analizador.png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
        }
    }
}
