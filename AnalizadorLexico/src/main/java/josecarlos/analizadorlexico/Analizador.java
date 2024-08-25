package josecarlos.analizadorlexico;

import josecarlos.analizadorlexico.enums.OperadorAritmetico;
import josecarlos.analizadorlexico.enums.OperadorLogico;
import josecarlos.analizadorlexico.enums.PalabraReservada;
import josecarlos.analizadorlexico.enums.PalabraReservadaEspecial;

/**
 *
 * @author emahch
 */
public class Analizador {

    public void analizarTexto(String texto) {
        if (texto == null) {
            return;
        }
        String[] lineas = texto.split("\n");
        for (String linea : lineas) {
            analizarLinea(linea);
        }
    }

    private void analizarLinea(String linea) {
        if (linea.isBlank() || linea.isEmpty()) {
            return;
        }
        String[] palabras = linea.split(" ");
        for (String palabra : palabras) {
            analizarPalabra(palabra);
        }
    }

    private void analizarPalabra(String palabra) {
        try {
            PalabraReservada palabraReservada = PalabraReservada.valueOf(palabra);
            System.out.println("PR " + palabraReservada);
        } catch (IllegalArgumentException e) {
            analizarPalabraReservadaEspecial(palabra);
        }
    }

    private void analizarPalabraReservadaEspecial(String palabra) {
        PalabraReservadaEspecial[] valores = PalabraReservadaEspecial.values();
        for (PalabraReservadaEspecial valor : valores) {
            if (palabra.equals(valor.toString())) {
                System.out.println("PRE " + palabra);
                return;
            }
        }
        analizarOperadorLogico(palabra);
    }

    private void analizarOperadorLogico(String palabra) {
        OperadorLogico[] valores = OperadorLogico.values();
        for (OperadorLogico valor : valores) {
            if (palabra.equals(valor.toString())) {
                System.out.println("LO " + palabra);
                return;
            }
        }
        analizarOperadorAritmetico(palabra);
    }
    
    private void analizarOperadorAritmetico(String palabra) {
        OperadorAritmetico[] valores = OperadorAritmetico.values();
        for (OperadorAritmetico valor : valores) {
            if (palabra.equals(valor.toString())) {
                System.out.println("AR " + palabra);
                return;
            }
        }
    }
}
