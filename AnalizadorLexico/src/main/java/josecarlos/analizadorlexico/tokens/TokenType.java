package josecarlos.analizadorlexico.tokens;

import java.awt.Color;

/**
 *
 * @author emahch
 */
public enum TokenType {
    IDENTIFICADOR("#FFD300"), 
    
    OPERADOR_ARITMETICO_SUMA("#FF33FF"), 
    OPERADOR_ARITMETICO_RESTA("#C19A6B"), 
    OPERADOR_ARITMETICO_EXPONENTE("#FCD0B4"), 
    OPERADOR_ARITMETICO_DIVISION("#B4D941"), 
    OPERADOR_ARITMETICO_MODULO("#D9AB41"), 
    OPERADOR_ARITMETICO_MULTIPLICACION("#D80073"), 
    
    OPERADOR_RELACIONAL_IGUAL("#6A00FF"),
    OPERADOR_RELACIONAL_DIFERENTE("#3F2212"),
    OPERADOR_RELACIONAL_MAYOR_QUE("#D9D441"),
    OPERADOR_RELACIONAL_MENOR_QUE("#D94A41"),
    OPERADOR_RELACIONAL_MAYOR_IGUAL_QUE("#E3C800"),
    OPERADOR_RELACIONAL_MENOR_IGUAL_QUE("#F0A30A"),
    
    OPERADOR_LOGICO_AND("#414ED9"),
    OPERADOR_LOGICO_OR("#41D95D"),
    OPERADOR_LOGICO_NOT("#A741D9"),
    
    OPERADOR_ASIGNACION_SIMPLE("#41D9D4"), 
    OPERADOR_ASIGNACION_COMPUESTA("#FFFFFF"),
    
    PALABRA_RESERVADA("#60A917"),
    
    NUMERO_ENTERO("#1BA1E2"),
    NUMERO_DECIMAL("#FFFF88"),
    CADENA("#E51400"),
    BOOLEANO("#FA6800"),
    CARACTER("#0050EF"),
    
    SQUARE(null),
    
    COMENTARIO("#B3B3B3"),
    DESCONOCIDO("#0f0"),
    
    SIMBOLO_PARENTESIS("#9AD8DB"),
    SIMBOLO_LLAVE("#DBD29A"),
    SIMBOLO_CORCHETE("#DBA49A"),
    SIGNO_COMA("#B79ADB"),
    SIGNO_PUNTO("#9ADBA6");
        
    private final Color color;
    private final String codigoColor;
    
    private TokenType(String codigoColor){
        this.codigoColor = codigoColor;
        if (codigoColor != null) {
            this.color = Color.decode(codigoColor);
        } else {
            color = null;
        }
    }

    public Color getColor() {
        return color;
    }

    public String getCodigoColor() {
        return codigoColor;
    }
    
}
