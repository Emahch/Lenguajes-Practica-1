package josecarlos.analizadorlexico.enums;

import java.awt.Color;

/**
 *
 * @author emahch
 */
public enum OperadorAsignacion {
    SIMPLE("=", Color.decode("#41D9D4")),
    SUMA("+=", Color.decode("#FFFFFF")),
    RESTA("-=", Color.decode("#FFFFFF")),
    MULTIPLICACION("*=", Color.decode("#FFFFFF")),
    DIVISION("/=", Color.decode("#FFFFFF"));
    
    private final String valor;
    private final Color color;
    
    private OperadorAsignacion(String valor, Color color){
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
