package josecarlos.analizadorlexico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author emahch
 */
public class PanelImagen extends JPanel {

    private Dimension dimensionCuadricula;
    private PanelCuadro[][] panelesCuadricula;

    /**
     *
     * @param dimension
     */
    public PanelImagen(Dimension dimension) {
        this.dimensionCuadricula = dimension;
        this.panelesCuadricula = new PanelCuadro[dimension.height][dimension.width];
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(dimension.height, dimension.width, 2, 2));
        this.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        limpiarCuadricula();
    }

    private void limpiarCuadricula() {
        for (int i = 0; i < dimensionCuadricula.height; i++) {
            for (int j = 0; j < dimensionCuadricula.width; j++) {
                panelesCuadricula[i][j] = new PanelCuadro(null);
                this.add(panelesCuadricula[i][j]);
            }
        }
    }

    public void generarCuadricula(List<Token> tokens) {
        removeAll();
        this.setBackground(Color.WHITE);
        int contador = 0;
        for (int i = 0; i < dimensionCuadricula.height; i++) {
            for (int j = 0; j < dimensionCuadricula.width; j++) {
                if (contador >= tokens.size()) {
                    panelesCuadricula[i][j] = new PanelCuadro(null);
                    this.add(panelesCuadricula[i][j]);
                } else {
                    panelesCuadricula[i][j] = new PanelCuadro(tokens.get(contador));
                    this.add(panelesCuadricula[i][j]);
                    contador++;
                }
            }
        }
        repaint();
    }

    public Dimension getDimensionCuadricula() {
        return dimensionCuadricula;
    }

    public JPanel[][] getPanelesCuadricula() {
        return panelesCuadricula;
    }

}
