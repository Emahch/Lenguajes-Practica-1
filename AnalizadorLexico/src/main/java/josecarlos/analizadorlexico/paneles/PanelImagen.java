package josecarlos.analizadorlexico.paneles;

import josecarlos.analizadorlexico.tokens.Token;
import josecarlos.analizadorlexico.tokens.TokenSquare;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
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
        this.setLayout(new GridLayout(dimension.height, dimension.width));
        this.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        limpiarCuadricula();
        pintarCuadricula();
    }

    private void limpiarCuadricula() {
        for (int i = 0; i < dimensionCuadricula.height; i++) {
            for (int j = 0; j < dimensionCuadricula.width; j++) {
                panelesCuadricula[i][j] = new PanelCuadro(null, i, j);
            }
        }
    }

    public void generarCuadricula(List<Token> tokens) {
        limpiarCuadricula();
        int contador = 0;
        for (int i = 0; i < dimensionCuadricula.height; i++) {
            for (int j = 0; j < dimensionCuadricula.width; j++) {
                if (contador >= tokens.size()) {
                    break;
                }
                Token token = tokens.get(contador);
                contador++;
                if (token instanceof TokenSquare tokenSquare) {
                    Point posicionCuadricula = tokenSquare.getPosicionCuadro();
                    if (posicionCuadricula != null) {
                        if (isPosicionValida(posicionCuadricula)) {
                            int fila = posicionCuadricula.y - 1;
                            int columna = posicionCuadricula.x - 1;
                            panelesCuadricula[fila][columna] = new PanelCuadro(token, fila, columna);
                            j--;
                        }
                    } else {
                        panelesCuadricula[i][j] = new PanelCuadro(token, i, j);
                    }
                } else if (panelesCuadricula[i][j].getToken() == null && token != null) {
                    panelesCuadricula[i][j] = new PanelCuadro(token, i, j);
                }
            }
        }
        pintarCuadricula();
    }

    private void pintarCuadricula() {
        this.setBackground(Color.WHITE);
        removeAll();
        for (PanelCuadro[] panelCuadros : panelesCuadricula) {
            for (PanelCuadro panelCuadro : panelCuadros) {
                this.add(panelCuadro);
            }
        }
        repaint();
    }

    private boolean isPosicionValida(Point posicionCuadricula) {
        return posicionCuadricula.x > 0 && posicionCuadricula.x <= this.dimensionCuadricula.width
                && posicionCuadricula.y > 0 && posicionCuadricula.y <= this.dimensionCuadricula.height;
    }

    public Dimension getDimensionCuadricula() {
        return dimensionCuadricula;
    }

    public JPanel[][] getPanelesCuadricula() {
        return panelesCuadricula;
    }

}
