package josecarlos.analizadorlexico.tokens;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author emahch
 */
public class Token {

    private TokenType type;
    protected String codigoColor;
    private String value;
    private Point posicionTexto;
    protected Point posicionCuadro;

    /**
     * Recibe los datos del token
     * @param type
     * @param value
     * @param fila
     * @param columna 
     */
    public Token(TokenType type, String value, int fila, int columna) {
        this.type = type;
        this.codigoColor = type.getCodigoColor();
        this.value = value;
        this.posicionTexto = new Point(columna - value.length(), fila);
    }
    
    /**
     * Recibe los datos del token especificando un codigo de color distinto al del token
     * @param type
     * @param value
     * @param fila
     * @param columna 
     */
    public Token(TokenType type, String codigoColor, String value, int fila, int columna) {
        this.type = type;
        this.codigoColor = codigoColor;
        this.value = value;
        this.posicionTexto = new Point(columna - value.length(), fila);
    }

    public void setPosicionCuadro(Point posicionCuadro) {
        this.posicionCuadro = posicionCuadro;
    }

    public Color getColor() {
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

    /**
     * Devuelve un array con los datos del token en formato reporte
     * @return 
     */
    public String[] getReporte() {
        if (posicionCuadro == null) {
            return null;
        }
        String[] reporte = new String[5];
        reporte[0] = type.name();
        reporte[1] = value;
        reporte[2] = String.valueOf(posicionTexto.y);
        reporte[3] = String.valueOf(posicionTexto.x);
        reporte[4] = "Fila: " + String.valueOf(posicionCuadro.y+1) + " " + 
                "Col: " + String.valueOf(posicionCuadro.x+1) + " " + 
                "Color: " + codigoColor;
        return reporte;
    }
}
