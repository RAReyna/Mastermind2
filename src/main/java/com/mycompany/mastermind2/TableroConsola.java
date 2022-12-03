/*
 * Esta clase representa al tablero en consola
 */
package com.mycompany.mastermind2;

/**
 *
 */
public class TableroConsola {

    private String[] tableroCombinaciones;
    private String[] tableroRetroalimentacion;

    public TableroConsola() {
        tableroCombinaciones = new String[96];
        tableroRetroalimentacion = new String[96];
        generarTablero();
    }

    //genera el tablero con valores predeterminados
    public void generarTablero() {
        for (int i = 0; i < 96; i++) {
            tableroCombinaciones[i] = "GR";
            tableroRetroalimentacion[i] = "GR";
        }
    }
    
    public void mostrarCodigo(CodigoColores codigoColores){
        String c = codigoColores.getCombinacion();
        String[] c2 = c.split(" ");
        int j = 0;
        for (int i = 90; i < 96; i++) {
            tableroCombinaciones[i] = c2[j];
            j++;
        }
    }

    //modifica los string dependiendo de la combinacion dada por el jugador
    public void agregarCombinacion(int i, Jugador j1) {
        int numCombinacionInicio;
        int numCombinacionFinal;
        if (i == 0) {
            numCombinacionInicio = i;
            numCombinacionFinal = 6;
        } else {
            numCombinacionInicio = 6 * i;
            numCombinacionFinal = numCombinacionInicio + 6;
        }
        String c = j1.getCombinaciones().get(i).getCombinacion();
        String[] c2 = c.split(" ");
        int k = 0;
        for (int j = numCombinacionInicio; j < numCombinacionFinal; j++) {
            tableroCombinaciones[j] = c2[k];
            k++;
        }
    }

    //modifica los string dependiendo de la combinacion agregada en retroalimentacion
    public void agregarRetroalimentacion(int i, Retroalimentacion retroalimentacion) {
        int numRetroInicio;
        int numRetroFinal;
        if (i == 0) {
            numRetroInicio = i;
            numRetroFinal = 6;
        } else {
            numRetroInicio = 6 * i;
            numRetroFinal = numRetroInicio + 6;
        }
        for (int j = numRetroInicio; j < numRetroFinal; j++) {
            tableroRetroalimentacion[j] = retroalimentacion.getRetroalimentaciones().get(j);
        }
    }

    //regresa un String de la representacion del tablero en texto
    public String mostrarTablero() {
        String t = "";
        int k = 0;
        int l = 0;
        t += "Combinaciones\t\tRetroalimentacion\n";
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 6; j++) {
                t += tableroCombinaciones[k] + " ";
                k++;
            }
            t += "\t";
            for (int j = 0; j < 6; j++) {
                t += tableroRetroalimentacion[l] + " ";
                l++;
            }
            t += "\n";
        }
        return t;
    }

    //getters y setters
    public String[] getTableroCombinaciones() {
        return tableroCombinaciones;
    }

    public void setTableroCombinaciones(String[] tableroCombinaciones) {
        this.tableroCombinaciones = tableroCombinaciones;
    }

    public String[] getTableroRetroalimentacion() {
        return tableroRetroalimentacion;
    }

    public void setTableroRetroalimentacion(String[] tableroRetroalimentacion) {
        this.tableroRetroalimentacion = tableroRetroalimentacion;
    }
    
}
