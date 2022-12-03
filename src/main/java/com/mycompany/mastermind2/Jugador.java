/*
 * Esta clase representa al jugador y almacena las combinaciones
 * que lleva a cabo
 */
package com.mycompany.mastermind2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class Jugador {

    private HashMap<Integer, CodigoColores> combinaciones;

    public Jugador() {
        combinaciones = new HashMap<Integer, CodigoColores>();
        for (int i = 0; i < 15; i++) {
            combinaciones.put(i, new CodigoColores());
        }
    }

    //agrega una combinacion al ArrayList combinaciones
    public boolean agregarCombinacion(int i, String combinacion) {
        boolean b = false;
        CodigoColores cc = new CodigoColores();
        cc.crearCodigo(combinacion);
        if (verificarRepetido(cc)) {
            System.out.println("Es una combinacion repetida");
            b = false;
        } else {
            if (combinaciones.get(i).crearCodigo(combinacion) == false) {
                System.out.println("Error: Un color fue repetido, fue invalido o no fue de 6 colores el código");
            } else {
                b = true;
            }
        }
        return b;
    }
    
    //compara los codigos en el HashMap para saber si hay una combinacion repetida
    public boolean verificarRepetido(CodigoColores cc) {
        boolean b = false;
        for (CodigoColores i : combinaciones.values()) {
            if (i.getCombinacion().equals(cc.getCombinacion())) {
                b = true;
                break;
            }
        }
        return b;
    }

    //muestra las combinaciones
    public void mostrarCombinaciones() {
        System.out.println("combinaciones");
        for (CodigoColores i : combinaciones.values()) {
            combinaciones.get(i).mostrarCodigo();
        }
    }

    //getters y setters
    public HashMap<Integer, CodigoColores> getCombinaciones() {
        return combinaciones;
    }

    public void setCombinaciones(HashMap<Integer, CodigoColores> combinaciones) {
        this.combinaciones = combinaciones;
    }

}
