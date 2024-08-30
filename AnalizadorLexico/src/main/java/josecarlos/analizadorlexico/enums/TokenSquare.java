package josecarlos.analizadorlexico.enums;

import java.awt.Point;
import josecarlos.analizadorlexico.Token;
import josecarlos.analizadorlexico.TokenType;

/**
 *
 * @author emahch
 */
public class TokenSquare extends Token{
    private String Color;
    private Point posicionCuadricula;

    public TokenSquare(String value, String Color) {
        super(TokenType.SQUARE, value);
        this.Color = Color;
    }
    
    public TokenSquare(String value, String Color, String fila, String columna) {
        super(TokenType.SQUARE, value);
        this.Color = Color;
    }
    
}
