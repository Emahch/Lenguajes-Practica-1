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

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (currentIndex < textoIngresado.length()) {
            char charActual = charActual();

            if (Character.isWhitespace(charActual)) {
                avanzar();  // Ignorar espacios en blanco
            } else if (Character.isLetter(charActual)) {
                tokens.add(identificarPalabra()); // Identifica si es palabra reservada, identificador o algun operador
            } else if (Character.isDigit(charActual)) {
                tokens.add(identificarNumero()); // Identifica si es numero o decimal
            } else if (isOperator(charActual)) {
                tokens.add(lexOperator());
            } else if (charActual == '\n') {
                avanzar();
                currentLine++;
                currentColumn = 0;
            } else if (charActual == '\'') {
                if (lookAhead() == '\'') {
                    tokens.add(lexComment());
                } else {
                    tokens.add(lexCharacter());
                }
            } else if (charActual == '"') {
                tokens.add(lexString());
            } else {
                tokens.add(new Token(TokenType.UNKNOWN, String.valueOf(charActual)));
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
        boolean isDecimal = false;

        while (Character.isDigit(current) || (current == '.' && !isDecimal)) {
            if (current == '.') {
                isDecimal = true;
            }
            string.append(current);
            avanzar();
            current = charActual();
        }

        if (isDecimal) {
            return new Token(TokenType.NUMERO_DECIMAL, string.toString());
        } else {
            return new Token(TokenType.NUMERO_ENTERO, string.toString());
        }
    }

    private Token lexCharacter() {
        avanzar();  // Saltar la primera comilla '
        char current = charActual();
        StringBuilder sb = new StringBuilder();

        if (current == '\\') {  // Manejar caracteres de escape
            sb.append(current);
            avanzar();
            current = charActual();
            sb.append(current);
        } else {
            sb.append(current);
        }

        avanzar();  // Saltar el carácter o secuencia de escape
        avanzar();  // Saltar la segunda comilla '
        return new Token(TokenType.CARACTER, sb.toString());
    }

    private Token lexString() {
        StringBuilder sb = new StringBuilder();
        avanzar();  // Saltar la primera comilla "

        char current = charActual();
        while (current != '"' && current != '\0') {
            if (current == '\\') {  // Manejar secuencias de escape dentro de la cadena
                sb.append(current);
                avanzar();
                current = charActual();
                sb.append(current);
            } else {
                sb.append(current);
            }
            avanzar();
            current = charActual();
        }

        avanzar();  // Saltar la última comilla "
        return new Token(TokenType.CADENA, sb.toString());
    }

    private Token lexOperator() {
        StringBuilder sb = new StringBuilder();
        sb.append(charActual());
        avanzar();

        char next = charActual();
        // Comprobamos operadores de dos caracteres (ej. ==, !=, >=, <=)
        if ((sb.charAt(0) == '=' || sb.charAt(0) == '!' || sb.charAt(0) == '>' || sb.charAt(0) == '<') && next == '=') {
            sb.append(next);
            avanzar();
        }

        return new Token(TokenType.OPERADOR_ASIGNACION_SIMPLE, sb.toString());
    }

    private Token lexComment() {
        StringBuilder sb = new StringBuilder();
        avanzar();  // Saltar la primera comilla '

        char current = charActual();
        while (current != '\n' && current != '\0') {  // Leer hasta el final de la línea o el final del archivo
            sb.append(current);
            avanzar();
            current = charActual();
        }

        return new Token(TokenType.COMENTARIO, sb.toString());
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

    private boolean isOperator(char c) {
        return "+-^*/=<>".indexOf(c) >= 0;
    }

    private boolean isSeparator(char c) {
        return "(){};,.".indexOf(c) >= 0;
    }

    private char lookAhead() {
        return currentIndex + 1 < textoIngresado.length() ? textoIngresado.charAt(currentIndex + 1) : '\0';
    }

    public static void main(String[] args) {
        String code = """
                      int x = 42; float y = 3.14; char c = 'a'; string s = "hello"; 'This is a comment' sd
                      Square.Color(#4905gh   , 1,2) ,  Square.Color(#4905gh   , 12) sladkf True Console.WriteLine Integer""";
        Analizador lexer = new Analizador(code);
        List<Token> tokens = lexer.tokenize();
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

}
