package josecarlos.analizadorlexico.enums;

import java.awt.Color;

/**
 *
 * @author emahch
 */
public enum OperadorRelacional {
    IGUAL("==", Color.decode("#6A00FF")),
    DIFERENTE("<>", Color.decode("#3F2212")),
    MAYOR_QUE(">", Color.decode("#D9D441")),
    MENOR_QUE("<", Color.decode("#D94A41")),
    MAYOR_IGUAL_QUE(">=", Color.decode("#E3C800")),
    MENOR_IGUAL_QUE("<=", Color.decode("#F0A30A"));
    
    
    private final String valor;
    private final Color color;
    
    private OperadorRelacional(String valor, Color color){
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
