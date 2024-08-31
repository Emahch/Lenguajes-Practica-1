package josecarlos.analizadorlexico.tokens;

import java.awt.Color;

/**
 *
 * @author emahch
 */
public enum TokenType {
    IDENTIFICADOR(Color.decode("#FFD300")), 
    
    OPERADOR_ARITMETICO_SUMA(Color.decode("#FF33FF")), 
    OPERADOR_ARITMETICO_RESTA(Color.decode("#C19A6B")), 
    OPERADOR_ARITMETICO_EXPONENTE(Color.decode("#FCD0B4")), 
    OPERADOR_ARITMETICO_DIVISION(Color.decode("#B4D941")), 
    OPERADOR_ARITMETICO_MODULO(Color.decode("#D9AB41")), 
    OPERADOR_ARITMETICO_MULTIPLICACION(Color.decode("#D80073")), 
    
    OPERADOR_RELACIONAL_IGUAL(Color.decode("#6A00FF")),
    OPERADOR_RELACIONAL_DIFERENTE(Color.decode("#3F2212")),
    OPERADOR_RELACIONAL_MAYOR_QUE(Color.decode("#D9D441")),
    OPERADOR_RELACIONAL_MENOR_QUE(Color.decode("#D94A41")),
    OPERADOR_RELACIONAL_MAYOR_IGUAL_QUE(Color.decode("#E3C800")),
    OPERADOR_RELACIONAL_MENOR_IGUAL_QUE(Color.decode("#F0A30A")),
    
    OPERADOR_LOGICO_AND(Color.decode("#414ED9")),
    OPERADOR_LOGICO_OR(Color.decode("#41D95D")),
    OPERADOR_LOGICO_NOT(Color.decode("#A741D9")),
    
    OPERADOR_ASIGNACION_SIMPLE(Color.decode("#41D9D4")), 
    OPERADOR_ASIGNACION_COMPUESTA(Color.decode("#FFFFFF")),
    
    PALABRA_RESERVADA(Color.decode("#60A917")),
    
    NUMERO_ENTERO(Color.decode("#1BA1E2")),
    NUMERO_DECIMAL(Color.decode("#FFFF88")),
    CADENA(Color.decode("#E51400")),
    BOOLEANO(Color.decode("#FA6800")),
    CARACTER(Color.decode("#0050EF")),
    
    SQUARE(null),
    
    COMENTARIO(Color.decode("#B3B3B3")),
    DESCONOCIDO(Color.decode("#0f0")),
    
    SIMBOLO_PARENTESIS(Color.decode("#9AD8DB")),
    SIMBOLO_LLAVE(Color.decode("#DBD29A")),
    SIMBOLO_CORCHETE(Color.decode("#DBA49A")),
    SIGNO_COMA(Color.decode("#B79ADB")),
    SIGNO_PUNTO(Color.decode("#9ADBA6"));
        
    private final Color color;
    
    private TokenType(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
}
