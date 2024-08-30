package josecarlos.analizadorlexico.enums;

import java.awt.Color;
import java.awt.Point;
import josecarlos.analizadorlexico.Token;
import josecarlos.analizadorlexico.TokenType;

/**
 *
 * @author emahch
 */
public class TokenSquare extends Token{
    private String color;
    private Point posicionCuadricula;

    public TokenSquare(String value, String Color) {
        super(TokenType.SQUARE, value);
        this.color = Color;
    }
    
    public TokenSquare(String value, String Color, String fila, String columna) {
        super(TokenType.SQUARE, value);
        this.color = Color;
    }
    
    @Override
    public Color getColor(){
        try {
            return Color.decode(color);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
}
