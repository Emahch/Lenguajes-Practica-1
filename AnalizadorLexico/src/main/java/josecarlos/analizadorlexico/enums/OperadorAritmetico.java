package josecarlos.analizadorlexico.enums;

import java.awt.Color;

/**
 *
 * @author emahch
 */
public enum OperadorAritmetico {
    SUMA("+", Color.decode("#FF33FF")),
    RESTA("-", Color.decode("#C19A6B")),
    EXPONENTE("^", Color.decode("#FCD0B4")),
    DIVISION("/", Color.decode("#B4D941")),
    MODULO("Mod", Color.decode("#D9AB41")),
    MULTIPLICACION("*", Color.decode("#D80073"));
    
    private final String valor;
    private final Color color;
    
    private OperadorAritmetico(String valor, Color color){
        this.valor = valor;
        this.color = color;
    }
    
    @Override
    public String toString(){
        return valor;
    }

    public Color getColor() {
        return color;
    }
}
