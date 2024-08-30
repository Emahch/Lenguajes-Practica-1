package josecarlos.analizadorlexico;

import java.util.ArrayList;
import java.util.List;
import josecarlos.analizadorlexico.enums.PalabraReservada;
import josecarlos.analizadorlexico.enums.PalabraReservadaEspecial;
import josecarlos.analizadorlexico.enums.TokenSquare;

public class Analizador {

    private String textoIngresado;
    private int currentIndex;
    private int currentLine;
    private int currentColumn;

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public Analizador(String textoIngresado) {
        this.textoIngresado = textoIngresado;
        this.currentIndex = 0;
        this.currentLine = 1;
        this.currentColumn = 0;
    }

    private char charActual() {
        if (currentIndex >= textoIngresado.length()) {
            return '\0';  // Fin de la cadena
        }
        return textoIngresado.charAt(currentIndex);
    }

    private void avanzar() {
        currentIndex++;
        currentColumn++;
    }

    private void retroceder(int marcadorIndice) {
        currentColumn = currentColumn - (currentIndex - marcadorIndice);
        currentIndex = marcadorIndice;
    }

    public List<Token> generarTokens() {
        List<Token> tokens = new ArrayList<>();

        while (currentIndex < textoIngresado.length()) {
            char current = charActual();

            if (Character.isWhitespace(current)) {
                avanzar();  // Ignorar espacios en blanco
            } else if (Character.isLetter(current)) {
                tokens.add(identificarPalabra()); // Identifica si es palabra reservada, identificador o algun operador
            } else if (Character.isDigit(current)) {
                tokens.add(identificarNumero()); // Identifica si es numero o decimal
            } else if (esOperadorAritmeticoOAsignacionCompuesta(current)) {
                tokens.add(identificarOperadorOAsignacionCompuesta());
            } else if (esOperadorRelacionalOAsignacionSimple(current)) {
                tokens.add(identificarOperadorOAsignacionSimple());
            } else if (esSimbolo(current)) {
                tokens.add(identificarSimbolo());
            } else if (current == '\n') {
                avanzar();
                currentLine++;
                currentColumn = 0;
            } else if (current == '\'') {
                tokens.add(identificarCaracterOComentario());
            } else if (current == '"') {
                tokens.add(identificarCadena());
            } else {
                tokens.add(null);
                avanzar();
            }
        }

        return tokens;
    }

    private Token identificarPalabra() {
        StringBuilder string = new StringBuilder();
        char current = charActual();

        while (Character.isLetterOrDigit(current) || current == '_') {
            string.append(current);
            avanzar();
            current = charActual();
        }
        String palabra = string.toString();
        int marcadorIndice = currentIndex;

        if (current == '.') {
            Token token = identificarPalabraReservadaEspecial(string);
            if (token != null) {
                return token;
            }
            retroceder(marcadorIndice);
            string = new StringBuilder(palabra);
            token = identificarFuncionSquare(string);
            if (token != null) {
                return token;
            }
            retroceder(marcadorIndice);
        } else {
            if (esPalabraReservada(palabra)) {
                return new Token(TokenType.PALABRA_RESERVADA, palabra);
            } else if (palabra.equals("Mod")) {
                return new Token(TokenType.OPERADOR_ARITMETICO_MODULO, palabra);
            } else if (palabra.equals("And")) {
                return new Token(TokenType.OPERADOR_LOGICO_AND, palabra);
            } else if (palabra.equals("Or")) {
                return new Token(TokenType.OPERADOR_LOGICO_OR, palabra);
            } else if (palabra.equals("Not")) {
                return new Token(TokenType.OPERADOR_LOGICO_NOT, palabra);
            } else if (palabra.equals("True") || palabra.equals("False")) {
                return new Token(TokenType.BOOLEANO, palabra);
            }
        }
        return new Token(TokenType.IDENTIFICADOR, palabra);
    }

    private Token identificarPalabraReservadaEspecial(StringBuilder string) {
        char current = charActual();
        do {
            string.append(current);
            avanzar();
            current = charActual();
        } while (Character.isLetter(current));

        String palabra = string.toString();
        if (esPalabraReservadaEspecial(palabra)) {
            return new Token(TokenType.PALABRA_RESERVADA, palabra);
        } else {
            return null;
        }
    }

    private Token identificarFuncionSquare(StringBuilder string) {
        char current = charActual();

        do {
            string.append(current);
            avanzar();
            current = charActual();
        } while (Character.isLetter(current));

        String palabra = string.toString();

        if (current == '(') {
            if (palabra.equals("Square.Color")) {
                string.append(current);
                avanzar();
                Token token = identificarParametrosSquare(string);
                return token;
            }
        }
        return null;
    }

    private Token identificarParametrosSquare(StringBuilder string) {
        char current = charActual();
        StringBuilder color = new StringBuilder();

        while (Character.isWhitespace(current)) {
            avanzar();
            current = charActual();
        }
        if (current == '#') {
            do {
                string.append(current);
                color.append(current);
                avanzar();
                current = charActual();
            } while (Character.isLetterOrDigit(current));

            while (Character.isWhitespace(current)) {
                avanzar();
                current = charActual();
            }

            if (current == ',') {
                string.append(current);
                avanzar();
                return identificarFilaColumnaSquare(string, color.toString());
            } else if (current == ')') {
                string.append(current);
                avanzar();
                return new TokenSquare(String.valueOf(string), String.valueOf(color));
            }
        }
        return null;
    }

    private Token identificarFilaColumnaSquare(StringBuilder string, String color) {
        char current = charActual();
        StringBuilder fila = new StringBuilder();
        StringBuilder columna = new StringBuilder();

        while (Character.isWhitespace(current)) {
            avanzar();
            current = charActual();
        }

        if (!Character.isDigit(current)) {
            return null;
        }

        while (Character.isDigit(current)) {
            string.append(current);
            fila.append(current);
            avanzar();
            current = charActual();
        }

        while (Character.isWhitespace(current)) {
            avanzar();
            current = charActual();
        }

        if (current != ',') {
            return null;
        }
        string.append(current);
        avanzar();
        current = charActual();

        while (Character.isWhitespace(current)) {
            avanzar();
            current = charActual();
        }

        if (!Character.isDigit(current)) {
            return null;
        }

        while (Character.isDigit(current)) {
            string.append(current);
            columna.append(current);
            avanzar();
            current = charActual();
        }

        while (Character.isWhitespace(current)) {
            avanzar();
            current = charActual();
        }

        if (current == ')') {
            string.append(current);
            avanzar();
            return new TokenSquare(string.toString(), color, fila.toString(), columna.toString());
        }
        return null;
    }

    private Token identificarNumero() {
        StringBuilder string = new StringBuilder();
        char current = charActual();
        boolean esDecimal = false;

        while (Character.isDigit(current) || (current == '.' && !esDecimal)) {
            if (current == '.') {
                esDecimal = true;
            }
            string.append(current);
            avanzar();
            current = charActual();
        }

        if (esDecimal) {
            return new Token(TokenType.NUMERO_DECIMAL, string.toString());
        } else {
            return new Token(TokenType.NUMERO_ENTERO, string.toString());
        }
    }

    private Token identificarSimbolo() {
        char current = charActual();
        avanzar();

        switch (current) {
            case '(', ')' -> {
                return new Token(TokenType.SIMBOLO_PARENTESIS, String.valueOf(current));
            }
            case '{', '}' -> {
                return new Token(TokenType.SIMBOLO_LLAVE, String.valueOf(current));
            }
            case '[', ']' -> {
                return new Token(TokenType.SIMBOLO_CORCHETE, String.valueOf(current));
            }
            case ',' -> {
                return new Token(TokenType.SIGNO_COMA, String.valueOf(current));
            }
            case '.' -> {
                return new Token(TokenType.SIGNO_PUNTO, String.valueOf(current));
            }
            default -> {
                return null;
            }
        }
    }

    private Token identificarOperadorOAsignacionCompuesta() {
        StringBuilder string = new StringBuilder();
        string.append(charActual());
        avanzar();

        if (string.toString().equals("^")) {
            return new Token(TokenType.OPERADOR_ARITMETICO_EXPONENTE, string.toString());
        }

        char siguiente = charActual();
        if (siguiente == '=') {
            string.append(siguiente);
            avanzar();
            return new Token(TokenType.OPERADOR_ASIGNACION_COMPUESTA, string.toString());
        } else {
            switch (string.charAt(0)) {
                case '+' -> {
                    return new Token(TokenType.OPERADOR_ARITMETICO_SUMA, string.toString());
                }
                case '-' -> {
                    return new Token(TokenType.OPERADOR_ARITMETICO_RESTA, string.toString());
                }
                case '/' -> {
                    return new Token(TokenType.OPERADOR_ARITMETICO_DIVISION, string.toString());
                }
                case '*' -> {
                    return new Token(TokenType.OPERADOR_ARITMETICO_MULTIPLICACION, string.toString());
                }
                default -> {
                    return null;
                }
            }
        }
    }

    private Token identificarOperadorOAsignacionSimple() {
        StringBuilder string = new StringBuilder();
        string.append(charActual());
        avanzar();

        char siguiente = charActual();

        if (string.charAt(0) == '=') {
            if (siguiente == '=') {
                string.append(siguiente);
                avanzar();
                return new Token(TokenType.OPERADOR_RELACIONAL_IGUAL, string.toString());
            } else {
                return new Token(TokenType.OPERADOR_ASIGNACION_SIMPLE, string.toString());
            }
        } else if (string.charAt(0) == '<') {
            if (siguiente == '>') {
                string.append(siguiente);
                avanzar();
                return new Token(TokenType.OPERADOR_RELACIONAL_DIFERENTE, string.toString());
            } else if (siguiente == '=') {
                string.append(siguiente);
                avanzar();
                return new Token(TokenType.OPERADOR_RELACIONAL_MENOR_IGUAL_QUE, string.toString());
            } else {
                return new Token(TokenType.OPERADOR_RELACIONAL_MENOR_QUE, string.toString());
            }
        } else if (string.charAt(0) == '>') {
            if (siguiente == '=') {
                string.append(siguiente);
                avanzar();
                return new Token(TokenType.OPERADOR_RELACIONAL_MAYOR_IGUAL_QUE, string.toString());
            } else {
                return new Token(TokenType.OPERADOR_RELACIONAL_MAYOR_QUE, string.toString());
            }
        }
        return null;
    }

    private Token identificarCaracterOComentario() {
        StringBuilder string = new StringBuilder();
        char current = charActual();
        string.append(current);
        avanzar();
        current = charActual();

        String caracterInicio = string.toString();
        int marcadorIndice = currentIndex;

        if (current != '\n' && !Character.isWhitespace(current) && current != '\'' && current != '\0') {
            string.append(current);
            avanzar();
            current = charActual();
            if (current == '\'') {
                string.append(current);
                avanzar();
                return new Token(TokenType.CARACTER, string.toString());
            } else {
                retroceder(marcadorIndice);
                string = new StringBuilder(caracterInicio);
            }
        }

        while (current != '\n' && current != '\0') {
            string.append(current);
            avanzar();
            current = charActual();
        }

        return new Token(TokenType.COMENTARIO, string.toString());
    }

    private Token identificarCadena() {
        StringBuilder string = new StringBuilder();
        char current = charActual();
        string.append(current);
        avanzar();

        current = charActual();

        while (current != '"' && current != '\0') {
            string.append(current);
            avanzar();
            current = charActual();
        }

        if (current == '"') {
            string.append(current);
            avanzar();
        } else {
            string.append('"');
        }
        return new Token(TokenType.CADENA, string.toString());
    }

    private boolean esPalabraReservada(String palabra) {
        try {
            PalabraReservada.valueOf(palabra);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean esPalabraReservadaEspecial(String palabra) {
        PalabraReservadaEspecial[] valores = PalabraReservadaEspecial.values();
        for (PalabraReservadaEspecial valor : valores) {
            if (palabra.equals(valor.toString())) {
                return true;
            }
        }
        return false;
    }

    private boolean esOperadorAritmeticoOAsignacionCompuesta(char c) {
        return c == '+' || c == '-' || c == '^' || c == '/' || c == '*';
    }

    private boolean esOperadorRelacionalOAsignacionSimple(char c) {
        return c == '=' || c == '<' || c == '>';
    }

    private boolean esSimbolo(char c) {
        return c == '(' || c == ')' || c == '{' || c == '}' || c == '[' || c == ']' || c == ',' || c == '.';
    }

}
