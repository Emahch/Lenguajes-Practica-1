package josecarlos.analizadorlexico;

/**
 *
 * @author emahch
 */

public class Token {
    private TokenType type;
    private String value;
    private int columna;
    private int fila;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Token{type=%s, value='%s'}", type, value);
    }
}
