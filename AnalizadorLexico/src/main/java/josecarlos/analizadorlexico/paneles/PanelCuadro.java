package josecarlos.analizadorlexico.paneles;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;
import static guru.nidi.graphviz.attribute.Attributes.attr;
import guru.nidi.graphviz.model.MutableGraph;
import josecarlos.analizadorlexico.tokens.Token;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author emahch
 */
public class PanelCuadro extends JPanel{
    private Token token;
    private int filaCuadro;
    private int columnaCuadro;

    public PanelCuadro(Token token, int filaCuadro, int columnaCuadro) {
        this.filaCuadro = filaCuadro + 1;
        this.columnaCuadro = columnaCuadro + 1;
        this.token = token;
        pintar();
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generarDiagrama();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cambiarBorde();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                restaurarBorde();
            }
        });
    }
    
    private void cambiarBorde(){
        if (this.token == null) {
            return;
        }
        this.setBorder(new LineBorder(Color.BLACK,1,true));
    }
    
    private void restaurarBorde(){
        this.setBorder(null);
    }
    
    private void generarDiagrama(){
        if (this.token == null) {
            return;
        }
        MutableGraph g = mutGraph(token.toString()).setDirected(true);
        
        for (int i = 0; i < token.getValue().length(); i++) {
            String value = token.getValue();
            String char1 = String.valueOf(value.charAt(i));
            String char2;
            try {
                char2 = String.valueOf(value.charAt(i+1));
            } catch (Exception e) {
                char2 = "End";
            }
            g.add(mutNode(char1).addLink(mutNode(char2)));
        }
        g.graphAttrs().add(attr("rankdir", "LR"));
        
        ImageIcon imagen = new ImageIcon(Graphviz.fromGraph(g).render(Format.PNG).toImage());

        mostrarDiagrama(imagen);
    }
    
    private void mostrarDiagrama(ImageIcon imagen){
        JFrame frame = new JFrame(token.getType().name() + " | " + token.getValue());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(2,1));
        frame.setBackground(Color.WHITE);

        JLabel labelDescripcion = new JLabel("Fila: " + token.getPosicionTexto().y + " Columna: " + token.getPosicionTexto().x + " -> "
                    + "Cuadro[" + filaCuadro + ", " + columnaCuadro + "]");
        labelDescripcion.setBackground(Color.WHITE);
        labelDescripcion.setOpaque(true);
        labelDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabel labelImagen = new JLabel(imagen);
        frame.add(labelDescripcion);
        frame.add(labelImagen);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
