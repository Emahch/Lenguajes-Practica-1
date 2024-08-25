package josecarlos.analizadorlexico.enums;

import java.awt.Color;

/**
 *
 * @author emahch
 */
public enum OperadorLogico {
    AND(Color.decode("#414ED9")),
    OR(Color.decode("#41D95D")),
    NOT(Color.decode("#A741D9"));
    
    private final Color color;
    
    private OperadorLogico (Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
