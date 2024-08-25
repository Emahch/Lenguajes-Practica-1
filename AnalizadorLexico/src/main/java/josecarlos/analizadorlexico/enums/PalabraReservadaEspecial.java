package josecarlos.analizadorlexico.enums;

import java.awt.Color;

/**
 *
 * @author emahch
 */
public enum PalabraReservadaEspecial {
    CONSOLE_WRITELINE("Console.WriteLine"),
    CONSOLE_READLINE("Console.ReadLine");
    
    public static final Color COLOR = Color.decode("#60A917");
    
    private final String palabra;

    private PalabraReservadaEspecial(String palabra) {
        this.palabra = palabra;
    }
    
    @Override
    public String toString(){
        return this.palabra;
    }
}
