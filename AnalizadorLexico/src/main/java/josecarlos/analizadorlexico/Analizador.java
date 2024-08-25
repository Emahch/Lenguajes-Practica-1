package josecarlos.analizadorlexico;

import josecarlos.analizadorlexico.enums.Dato;
import josecarlos.analizadorlexico.enums.Identificador;
import josecarlos.analizadorlexico.enums.OperadorAritmetico;
import josecarlos.analizadorlexico.enums.OperadorAsignacion;
import josecarlos.analizadorlexico.enums.OperadorLogico;
import josecarlos.analizadorlexico.enums.OperadorRelacional;
import josecarlos.analizadorlexico.enums.PalabraReservada;
import josecarlos.analizadorlexico.enums.PalabraReservadaEspecial;
import josecarlos.analizadorlexico.enums.Simbolo;

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
        analizarOperadorRelacional(palabra);
    }

    private void analizarOperadorRelacional(String palabra) {
        OperadorRelacional[] valores = OperadorRelacional.values();
        for (OperadorRelacional valor : valores) {
            if (palabra.equals(valor.toString())) {
                System.out.println("RE " + palabra);
                return;
            }
        }
        analizarOperadorAsignacion(palabra);
    }

    private void analizarOperadorAsignacion(String palabra) {
        OperadorAsignacion[] valores = OperadorAsignacion.values();
        for (OperadorAsignacion valor : valores) {
            if (palabra.equals(valor.toString())) {
                System.out.println("AS " + palabra);
                return;
            }
        }
        analizarSimbolo(palabra);
    }

    private void analizarSimbolo(String palabra) {
        Simbolo[] valores = Simbolo.values();
        for (Simbolo valor : valores) {
            if (palabra.equals(valor.toString())) {
                System.out.println("SI " + palabra);
                return;
            }
        }
        analizarSiEsNumeroEntero(palabra);
    }

    private void analizarSiEsNumeroEntero(String palabra) {
        try {
            Integer.valueOf(palabra);
            System.out.println("NUM " + palabra);
        } catch (NumberFormatException e) {
            analizarSiEsNumeroDecimal(palabra);
        }
    }

    private void analizarSiEsNumeroDecimal(String palabra) {
        try {
            Double.valueOf(palabra);
            System.out.println("DEC " + palabra);
        } catch (NumberFormatException e) {
            analizarSiEsTipoDato(palabra);
        }
    }

    private void analizarSiEsTipoDato(String palabra) {
        String caracter = String.valueOf(palabra.charAt(0));
        Dato[] valores = Dato.values();
        for (Dato valor : valores) {
            if (caracter.equals(valor.toString())) {
                analizarSiEsCaracter(palabra);
                return;
            }
        }
        analizarSiEsIdentificador(palabra);
    }

    private void analizarSiEsCaracter(String palabra) {
        char[] caracteres = palabra.toCharArray();
        if (caracteres.length == 3) {
            if (String.valueOf(caracteres[0]).equals(Dato.CARACTER.toString())
                    && String.valueOf(caracteres[2]).equals(Dato.CARACTER.toString())) {
                System.out.println("CARACTER " + palabra);
            }
        }
        analizarSiEsCadena(palabra);
    }

    private void analizarSiEsCadena(String palabra) {
        char[] caracteres = palabra.toCharArray();
        if (caracteres.length >= 3) {
            if (String.valueOf(caracteres[0]).equals(Dato.CADENA.toString())
                    && String.valueOf(caracteres[caracteres.length - 1]).equals(Dato.CADENA.toString())) {
                System.out.println("CADENA " + palabra);
            }
        }
    }

    private void analizarSiEsIdentificador(String palabra) {
        char[] caracteres = palabra.toCharArray();

        try {
            if (!String.valueOf(caracteres[0]).equals(Identificador.GUION_BAJO)) {
                Identificador.valueOf(String.valueOf(caracteres[0]).toLowerCase());
            }
        } catch (IllegalArgumentException e) {
            return;
        }

        for (int i = 1; i < caracteres.length; i++) {
            try {
                if (!String.valueOf(caracteres[i]).equals(Identificador.GUION_BAJO)) {
                    Identificador.valueOf(String.valueOf(caracteres[i]).toLowerCase());
                }
            } catch (IllegalArgumentException e) {
                try {
                    Integer.parseInt(String.valueOf(caracteres[i]));
                } catch (NumberFormatException ex) {
                    return;
                }
            }
        }
        System.out.println("ID " + palabra);
    }
    
}
