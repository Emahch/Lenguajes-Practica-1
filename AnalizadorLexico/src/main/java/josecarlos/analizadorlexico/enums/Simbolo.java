package josecarlos.analizadorlexico.enums;

import java.awt.Color;

/**
 *
 * @author emahch
 */
public enum Simbolo {
    PARENTESIS_ABIERTO("(", Color.decode("#9AD8DB")),
    PARENTESIS_CERRADO(")", Color.decode("#9AD8DB")),
    LLAVE_ABIERTA("{", Color.decode("#DBD29A")),
    LLAVE_CERRADA("}", Color.decode("#DBD29A")),
    CORCHETE_ABIERTO("[", Color.decode("#DBA49A")),
    CORCHETE_CERRADO("]", Color.decode("#DBA49A")),
    COMA(",", Color.decode("#B79ADB")),
    PUNTO(".", Color.decode("#9ADBA6"));
    
    private final String valor;
    private final Color color;
    
    private Simbolo(String valor, Color color){
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
