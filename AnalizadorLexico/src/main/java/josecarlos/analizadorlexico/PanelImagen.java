package josecarlos.analizadorlexico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author emahch
 */
public class PanelImagen extends JPanel {

    private Dimension dimensionCuadricula;
    private int areaCuadricula;
    private JPanel[][] panelesCuadricula;
    
    /**
     * 
     * @param dimension
     */
    public PanelImagen(Dimension dimension) {
        this.dimensionCuadricula = dimension;
        this.areaCuadricula = dimension.height * dimension.width;
        this.setLayout(new GridLayout(dimension.height, dimension.width, 2, 2));
        this.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        crearCuadricula();
    }
    
    private void crearCuadricula(){
        for (int i = 0; i < areaCuadricula; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            this.add(panel);

        }
        repaint();
    }

    public Dimension getDimensionCuadricula() {
        return dimensionCuadricula;
    }

    public int getAreaCuadricula() {
        return areaCuadricula;
    }

    public JPanel[][] getPanelesCuadricula() {
        return panelesCuadricula;
    }
    
    
}
