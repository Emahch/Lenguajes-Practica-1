package josecarlos.analizadorlexico.enums;

import java.awt.Color;

/**
 *
 * @author emahch
 */
public enum Dato {
    CADENA("\"", Color.decode("#E51400")),
    CARACTER("'", Color.decode("#0050EF"));

    public static final Color COLOR = Color.decode("#1BA1E2");
    public static final Color DECIMAL_COLOR = Color.decode("#FFFF88");
    private final String valor;
    private final Color color;

    private Dato(String valor, Color color) {
        this.valor = valor;
        this.color = color;
    }

    @Override
    public String toString() {
        return valor;
    }

    public Color getColor() {
        return color;
    }
}
