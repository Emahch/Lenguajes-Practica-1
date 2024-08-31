package josecarlos.analizadorlexico.tokens;

import java.awt.Color;

/**
 *
 * @author emahch
 */
public enum PalabraReservada {
    Module,
    End,
    Sub,
    Main,
    Dim,
    As,
    Integer,
    String,
    Boolean,
    Double,
    Char,
    If,
    ElseIf,
    Else,
    Then,
    While,
    Do,
    Loop,
    For,
    To,
    Next,
    Function,
    Return,
    Const;
    
    public static final Color COLOR = Color.decode("#60A917");
}
