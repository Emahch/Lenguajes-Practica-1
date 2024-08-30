package josecarlos.analizadorlexico;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import javax.swing.*;
import java.awt.*;
import static guru.nidi.graphviz.model.Factory.*;

public class LexerGraphvizExample {
    private lexico lexico;
    private MutableGraph graph;

    public LexerGraphvizExample() {
        lexico = new lexico();
        graph = createGraph();
    }

    private MutableGraph createGraph() {
        // Crear el grafo inicial del DFA
        MutableGraph g = mutGraph("DFA").setDirected(true)
            .add(mutNode("q0").addLink(mutNode("q1")),
                 mutNode("q1").addLink(mutNode("q2")),
                 mutNode("q2").addLink(mutNode("q1"),
                                       mutNode("q2")));
        return g;
    }

    private void renderGraph() {
        // Renderizar el grafo en una imagen
        ImageIcon image = new ImageIcon(Graphviz.fromGraph(graph).render(Format.PNG).toImage());

        // Crear y mostrar el JFrame con la imagen
        JFrame frame = new JFrame("DFA Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 600));
        
        JLabel label = new JLabel(image);
        frame.add(label);
        
        frame.setVisible(true);
    }

    public void analyzeString(String input) {
        for (char c : input.toCharArray()) {
            lexico.nextState(c);
        }
        updateGraph();  // Actualizar el grafo al finalizar todo el análisis
    }

    private void updateGraph() {
        // Actualizar la representación del grafo para mostrar el estado final
        String currentState = lexico.getCurrentState();
        graph.nodes().forEach(node -> {
            if (node.name().toString().equals(currentState)) {
                node.add(guru.nidi.graphviz.attribute.Attributes.attr("color", "red"));
            } else {
                node.add(guru.nidi.graphviz.attribute.Attributes.attr("color", "black"));
            }
        });

        // Renderizar el grafo actualizado solo una vez después del análisis
        renderGraph();
    }

    public static void main(String[] args) {
        LexerGraphvizExample example = new LexerGraphvizExample();
        example.analyzeString("abab");
    }
}
