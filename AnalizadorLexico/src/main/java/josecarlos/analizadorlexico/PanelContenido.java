package josecarlos.analizadorlexico;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author emahch
 */
public class PanelContenido extends JPanel{

    private PanelCodigo panelCodigo;
    private PanelImagen panelImagen;
    private Dimension dimensionImagen;
    
    public PanelContenido(Dimension dimensionImagen) {
        this.dimensionImagen = dimensionImagen;
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        setLayout(new GridLayout(1, 2, 10, 10));
        panelCodigo = new PanelCodigo();
        panelImagen = new PanelImagen(dimensionImagen);
        add(panelCodigo);
        add(panelImagen);
    }

    public PanelCodigo getPanelCodigo() {
        return panelCodigo;
    }

    public PanelImagen getPanelImagen() {
        return panelImagen;
    }
}
