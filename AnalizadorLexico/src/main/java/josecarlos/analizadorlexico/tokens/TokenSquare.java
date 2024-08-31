package josecarlos.analizadorlexico.tokens;

import josecarlos.analizadorlexico.tokens.TokenType;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author emahch
 */
public class TokenSquare extends Token {

    private String color;
    private Point posicionCuadricula;

    public TokenSquare(String value, String Color, int fila, int columna, int filaCuadricula, int columnaCuadricula) {
        super(TokenType.SQUARE, value, fila, columna);
        this.color = Color;
        this.posicionCuadricula = new Point(columnaCuadricula, filaCuadricula);
    }

    public TokenSquare(String value, String Color, int fila, int columna) {
        super(TokenType.SQUARE, value, fila, columna);
        this.color = Color;
    }

    public Point getPosicionCuadricula() {
        return posicionCuadricula;
    }

    @Override
    public Color getColor() {
        try {
            return Color.decode(color);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
