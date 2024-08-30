package josecarlos.analizadorlexico;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author emahch
 */
public class PanelCuadro extends JPanel{
    private Token token;
    private Color color;

    public PanelCuadro(Token token) {
        this.token = token;
        pintar();
    }

    private void pintar(){
        if (this.token == null) {
            this.setBackground(Color.WHITE);
            return;
        }
        if (this.token.getColor() == null) {
            return;
        }
        this.setBackground(token.getColor());
    }
    
    public Token getToken() {
        return token;
    }
    
}
