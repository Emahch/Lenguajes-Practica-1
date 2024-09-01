package josecarlos.analizadorlexico.tokens;

import josecarlos.analizadorlexico.tokens.TokenType;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author emahch
 */
public class TokenSquare extends Token {

    public TokenSquare(String value, String color, int fila, int columna, int filaCuadricula, int columnaCuadricula) {
        super(TokenType.SQUARE, color, value, fila, columna);
        this.posicionCuadro = new Point(columnaCuadricula, filaCuadricula);
    }
    
    public TokenSquare(String value, String color, int fila, int columna) {
        super(TokenType.SQUARE , color, value, fila, columna);
    }

    public Point getPosicionCuadro() {
        return posicionCuadro;
    }

    @Override
    public Color getColor() {
        try {
            return Color.decode(codigoColor);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
