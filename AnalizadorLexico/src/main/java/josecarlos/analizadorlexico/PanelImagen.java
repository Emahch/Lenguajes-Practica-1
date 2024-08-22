package josecarlos.analizadorlexico;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author emahch
 */
public class PanelImagen extends JPanel{

    public PanelImagen() {
        this.setLayout(new GridLayout(5, 5,2,2));
        
        for (int i = 0; i < 25; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            this.add(panel);

        }
        repaint();
    }
    
    
}
