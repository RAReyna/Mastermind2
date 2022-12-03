/*
 * Esta clase representa la retroalimentacion que se da en el juego
 * Mastermind
 */
package com.mycompany.mastermind2;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Green
 */
public class Retroalimentacion {

    private ArrayList<String> retroalimentaciones;

    //NE = negro, BL = blanco, GR = gris;
    //NE = esta el color y esta en la misma posicion que en codigo
    //BL = esta el color dentro del codigo
    //GR = no esta el color dentro del codigo
    public Retroalimentacion() {
        retroalimentaciones = new ArrayList<String>();
        for (int i = 0; i < 96; i++) {
            retroalimentaciones.add("GR");
        }
    }

    public void crearRetroalimentacion(int i, Jugador j1, CodigoColores codigoColores) {
        int numRetroInicio;
        if (i == 0) {
            numRetroInicio = i;
        } else {
            numRetroInicio = 6 * i;
        }
        String s1 = j1.getCombinaciones().get(i).getCombinacion();
        String[] ars = s1.split(" ");
        String s2 = codigoColores.getCombinacion();
        String[] ars2 = s2.split(" ");
        for (int j = 0; j < ars.length; j++) {
            if (ars[j].equals(ars2[j])) {
                retroalimentaciones.set(numRetroInicio, "NE");
                numRetroInicio++;
            } else if (codigoColores.getCodigo().containsValue(ars[j])) {
                retroalimentaciones.set(numRetroInicio, "BL");
                numRetroInicio++;
            }
        }
    }

    //getters y setters
    public ArrayList<String> getRetroalimentaciones() {
        return retroalimentaciones;
    }

    public void setRetroalimentaciones(ArrayList<String> retroalimentaciones) {
        this.retroalimentaciones = retroalimentaciones;
    }

}
