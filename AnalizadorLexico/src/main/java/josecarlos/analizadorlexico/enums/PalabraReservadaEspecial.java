package josecarlos.analizadorlexico.enums;

/**
 *
 * @author emahch
 */
public enum PalabraReservadaEspecial {
    CONSOLE_WRITELINE("Console.WriteLine"),
    CONSOLE_READLINE("Console.ReadLine");
    
    private final String value;

    private PalabraReservadaEspecial(String palabra) {
        this.value = palabra;
    }
    
    @Override
    public String toString(){
        return this.value;
    }
}
