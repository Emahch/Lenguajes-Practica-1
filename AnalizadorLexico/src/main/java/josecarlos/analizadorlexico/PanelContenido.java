package josecarlos.analizadorlexico;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author emahch
 */
public class PanelContenido extends JPanel{

    private PanelCodigo panelCodigo;
    private PanelImagen panelImagen;
    
    public PanelContenido() {
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        setLayout(new GridLayout(1, 2, 10, 10));
        panelCodigo = new PanelCodigo();
        panelImagen = new PanelImagen();
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
