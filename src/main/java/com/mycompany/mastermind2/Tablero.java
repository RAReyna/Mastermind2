/*
 * Esta clase representa al tablero del juego de manera graficas
 * mediante canvas
 */
package com.mycompany.mastermind2;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 */
public class Tablero {

    private Canvas canvas;
    private Rectangulo r; //rectangulo de debajo de las combinaciones
    private Rectangulo r2; //tablero debajo de la retroalimentación
    private ArrayList<BouncingBall> circulosPrincipal;
    private ArrayList<BouncingBall> circulosRetroalimentacion;

    public Tablero(Canvas canvas) {
        r = new Rectangulo(1, 1, 250, 480, Color.GRAY, canvas);
        r2 = new Rectangulo(255, 1, 100, 480, Color.GRAY, canvas);
        circulosPrincipal = new ArrayList<BouncingBall>();
        circulosRetroalimentacion = new ArrayList<BouncingBall>();
        r.draw();
        r2.draw();
        crearCirculosPrincipales(canvas);
        crearCirculosRetroalimentacion(canvas);
    }

    //muestra el codigo que se intenta decodifcar
    public void mostrarCodigo(CodigoColores codigoColores) {
        String c = codigoColores.getCombinacion();
        String[] c2 = c.split(" ");
        int j = 0;
        for (int i = 90; i < 96; i++) {
            circulosPrincipal.get(i).changeColor(generarColor(c2[j]));
            j++;
        }
    }

    //crea los circulos de las combinaciones con valores y colores predeterminados
    public void crearCirculosPrincipales(Canvas canvas) {
        int x = 10;
        int y = 5;
        for (int i = 0; i < 16; i++) {
            x = 10;
            for (int j = 0; j < 6; j++) {
                if (i == 15) {
                    circulosPrincipal.add(new BouncingBall(x, y, 20, Color.BLACK, canvas));
                } else {
                    circulosPrincipal.add(new BouncingBall(x, y, 20, Color.DARK_GRAY, canvas));
                }
                x = x + 40;
            }
            y = y + 30;
        }
        dibujarCirculosPrincipales(canvas);
    }

    //Crear los circulos de la retroalimentacion con valores y color predeterminados
    public void crearCirculosRetroalimentacion(Canvas canvas) {
        int x = 257;
        int y = 5;
        for (int i = 0; i < 15; i++) {
            x = 257;
            for (int j = 0; j < 6; j++) {
                circulosRetroalimentacion.add(new BouncingBall(x, y, 10, Color.DARK_GRAY, canvas));
                x = x + 16;
            }
            y = y + 30;
        }
        dibujarCirculosRetroalimentacion(canvas);
    }

    //dibujas los circulos de las combinaciones en canvas
    public void dibujarCirculosPrincipales(Canvas canvas) {
        for (int i = 0; i < circulosPrincipal.size(); i++) {
            circulosPrincipal.get(i).draw();
        }
    }

    //dibujas los circulos de la retroalimentacion en canvas
    public void dibujarCirculosRetroalimentacion(Canvas canvas) {
        for (int i = 0; i < circulosRetroalimentacion.size(); i++) {
            circulosRetroalimentacion.get(i).draw();
        }
    }

    //genera el color de tipo Color en base a las abreviaciones usadas para codificar y decodificar
    public Color generarColor(String c) {
        //RO = rojo, AZ = azul, NA = naranja, AM = amarillo, CY = cyan, VE = verde, RA = rosa, MA = magenta.
        //NE = negro, BL = blanco, GR = gris;
        Color color = Color.BLACK;
        switch (c) {
            case "RO":
                color = Color.RED;
                break;

            case "AZ":
                color = Color.BLUE;
                break;

            case "NA":
                color = Color.ORANGE;
                break;

            case "AM":
                color = Color.YELLOW;
                break;

            case "CY":
                color = Color.CYAN;
                break;

            case "VE":
                color = Color.GREEN;
                break;

            case "RA":
                color = Color.PINK;
                break;

            case "MA":
                color = Color.MAGENTA;
                break;

            case "NE":
                color = Color.BLACK;
                break;

            case "BL":
                color = Color.WHITE;
                break;

            case "GR":
                color = Color.DARK_GRAY;
                break;
        }
        return color;
    }

    //modifica los colores de los circulos de las combinaciones dependiendo de 
    //la decodicacion dada por el jugador y el intento
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
            circulosPrincipal.get(j).changeColor(generarColor(c2[k]));
            k++;
        }
    }

    //modifica los colores de los circulos de las retroalimentaciones dependiendo 
    //de la decodicacion dada por el jugador y el intento
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
            circulosRetroalimentacion.get(j).changeColor(generarColor(retroalimentacion.getRetroalimentaciones().get(j)));
        }
    }

    //getters y setters
    public Rectangulo getR() {
        return r;
    }

    public void setR(Rectangulo r) {
        this.r = r;
    }

    public Rectangulo getR2() {
        return r2;
    }

    public void setR2(Rectangulo r2) {
        this.r2 = r2;
    }

    public ArrayList<BouncingBall> getCirculosPrincipal() {
        return circulosPrincipal;
    }

    public void setCirculosPrincipal(ArrayList<BouncingBall> circulosPrincipal) {
        this.circulosPrincipal = circulosPrincipal;
    }

    public ArrayList<BouncingBall> getCirculosRetroalimentacion() {
        return circulosRetroalimentacion;
    }

    public void setCirculosRetroalimentacion(ArrayList<BouncingBall> circulosRetroalimentacion) {
        this.circulosRetroalimentacion = circulosRetroalimentacion;
    }

}
