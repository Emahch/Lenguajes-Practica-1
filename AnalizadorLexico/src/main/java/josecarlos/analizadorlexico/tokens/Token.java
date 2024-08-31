package josecarlos.analizadorlexico.tokens;

import josecarlos.analizadorlexico.tokens.TokenType;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author emahch
 */

public class Token {
    private TokenType type;
    private String value;
    private Point posicionTexto;

    public Token(TokenType type, String value, int fila, int columna) {
        this.type = type;
        this.value = value;
        this.posicionTexto = new Point(columna-value.length(), fila);
    }

    public Color getColor(){
        return this.type.getColor();
    }

    public Point getPosicionTexto() {
        return posicionTexto;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return String.format("Token{type=%s, value='%s'}", type, value);
    }
}
