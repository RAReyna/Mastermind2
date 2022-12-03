/*
 * Esta clase permite la interacción entre el usuario y el programa para 
 * llevar a acabo el juego.
 */
package com.mycompany.mastermind2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 */
public class Mastermind {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //tablero
        Canvas canvas = new Canvas("Tablero Prueba", 500, 500);
        Tablero tablero = new Tablero(canvas);
        TableroConsola tableroConsola = new TableroConsola();
        //inicializar variables
        boolean victoria = false;
        Scanner s1 = new Scanner(System.in);
        CodigoColores codigoColores = new CodigoColores();
        Jugador j1 = new Jugador();
        Retroalimentacion retroalimentacion = new Retroalimentacion();
        String c = "";
        boolean codigoValido = false;

//para que cree el código un usuario
//        do {
//            c = cadenaCodigoColores(s1);
//            codigoColores.agregarColor(c);
//            if (codigoColores.crearCodigo(c) == false) {
//                System.out.println("Error: Un color fue repetido, fue invalido o no fue de 6 colores el código");
//                System.out.println("Intente de nuevo\n");
//                codigoValido = false;
//            } else {
//                codigoValido = true;
//            }
//        } while (codigoValido == false);
        mensajeInicio();
        codigoColores.crearCodigo(crearCodigo());
        //inicia el juego
        System.out.println("\nEmpieza el juego");
        int i = 0;
        do {
            System.out.println(tableroConsola.mostrarTablero());
            System.out.println("\n----- intento " + (i + 1) + " -----");
            do {
                c = cadenaCodigoColores(s1);
                if (c.equals("CO")) {
                    System.out.println("Código: " + codigoColores.getCombinacion() + "\n");
                    codigoValido = false;
                } else {
                    //codigoColores.agregarColor(i, c);
                    if (j1.agregarCombinacion(i, c) == false) {
                        System.out.println("Intente de nuevo\n");
                        dormir(1);
                        codigoValido = false;
                    } else {
                        codigoValido = true;
                    }
                }
            } while (codigoValido == false);

            tablero.agregarCombinacion(i, j1);
            tableroConsola.agregarCombinacion(i, j1);

            retroalimentacion.crearRetroalimentacion(i, j1, codigoColores);
            tablero.agregarRetroalimentacion(i, retroalimentacion);
            tableroConsola.agregarRetroalimentacion(i, retroalimentacion);

            victoria = verificarVictoria(i, retroalimentacion);

            i++;
        } while (i < 15 && victoria == false);

        tableroConsola.mostrarCodigo(codigoColores);
        System.out.println(tableroConsola.mostrarTablero());
        tablero.mostrarCodigo(codigoColores);
        mensajeVictoria(i - 1, victoria);
    }

    public static String crearCodigo() {
        String codigo = "";
        ArrayList<String> colores = new ArrayList<String>();
        //RO = rojo, AZ = azul, NA = naranja, AM = amarillo, CY = cyan, VE = verde, RA = rosa, MA = magenta.
        colores.add("RO");
        colores.add("AZ");
        colores.add("NA");
        colores.add("AM");
        colores.add("CY");
        colores.add("VE");
        colores.add("RA");
        colores.add("MA");
        Collections.shuffle(colores);
        Random aleatorio = new Random();
        for (int i = 0; i < 6; i++) {
            codigo += colores.remove(aleatorio.nextInt(colores.size())) + " ";
        }
        return codigo;
    }

    public static String cadenaCodigoColores(Scanner s1) {
        String c = "";
        System.out.println("Colores validos:\nRO = rojo, AZ = azul, NA = naranja, AM = amarillo, CY = cyan, VE = verde, RA = rosa, MA = magenta");
        System.out.println("Ingrese el codigo de 6 colores: (ingrese la abreviación que se encuentra al lado del color)");
        c = s1.nextLine();
        return c;
    }

    public static boolean verificarVictoria(int i, Retroalimentacion retroalimentacion) {
        boolean b = false;
        int numRetroInicio;
        int numRetroFinal;
        if (i == 0) {
            numRetroInicio = i;
            numRetroFinal = 6;
        } else {
            numRetroInicio = 6 * i;
            numRetroFinal = numRetroInicio + 6;
        }
        int numNegros = 0;
        for (int j = numRetroInicio; j < numRetroFinal; j++) {
            if (retroalimentacion.getRetroalimentaciones().get(j).equals("NE")) {
                b = true;
            } else {
                b = false;
                break;
            }
        }
        return b;
    }

    public static void mensajeVictoria(int i, boolean victoria) {
        if (victoria == true) {
            System.out.println("\n----- Ganó en la ronda " + (i + 1) + " -----");
        } else {
            System.out.println("\n----- Perdio -----");
        }

        System.out.println("\n----- Fin del juego -----");
    }

    public static void mensajeInicio() {
        System.out.println("----- Mastermind -----");
        System.out.println("\nEn este juego el programa creará un código de colores\n"
                + "y el jugador intentará descifrar este, introduciendo \n"
                + "combinaciones de colores\n");
        dormir(3);
    }

    static void dormir(int n) {
        int s = n * 1000;
        try {
            Thread.sleep(s);
        } catch (InterruptedException ex) {
            Logger.getLogger(Mastermind.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
