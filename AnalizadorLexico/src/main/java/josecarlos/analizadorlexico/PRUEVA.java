package josecarlos.analizadorlexico;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import static guru.nidi.graphviz.model.Factory.*;
import guru.nidi.graphviz.model.MutableGraph;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author emahch
 */
public class PRUEVA {

    public static void main(String[] args) {
        // Crear un grafo en memoria
        MutableGraph g = mutGraph("example1").setDirected(true).add(
            mutNode("A").addLink(mutNode("B")),
            mutNode("B").addLink(mutNode("C"))
        );
        

        // Renderizar el grafo en una imagen
        ImageIcon image = new ImageIcon(Graphviz.fromGraph(g).render(Format.PNG).toImage());

        // Crear un JFrame para mostrar la imagen
        JFrame frame = new JFrame("Graphviz Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 400));

        // Crear un JLabel con la imagen y añadirlo al JFrame
        JLabel label = new JLabel(image);
        frame.add(label);

        // Mostrar la ventana
        frame.setVisible(true);
    }

}
