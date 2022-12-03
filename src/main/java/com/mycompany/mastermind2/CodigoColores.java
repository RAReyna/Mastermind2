/*
 * Esta clase representa a la combinaciones de colores tanto para el
 * codificador como para el decodificador(jugador)
 */
package com.mycompany.mastermind2;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 */
public class CodigoColores {

    private HashMap<Integer, String> codigo;
    //RO = rojo, AZ = azul, NA = naranja, AM = amarillo, CY = cyan, VE = verde, RA = rosa, MA = magenta.
    private String[] coloresValidos = {"RO", "AZ", "NA", "AM", "CY", "VE", "RA", "MA"};
    private String combinacion;

    public CodigoColores() {
        codigo = new HashMap<Integer, String>();
        combinacion = "";
    }

    //crear el codigo o combinacion en el caso del jugador
    public boolean crearCodigo(String codigo) {
        boolean b = true;
        combinacion = codigo;
        String[] colores = codigo.split(" ");
        if (colores.length < 6 || colores.length > 6) {
            b = false;
        } else {
            for (int i = 0; i < colores.length; i++) {
                if (validarColor(colores[i]) == false || agregarColor(i, colores[i]) == false || colores[i].length() > 2 || colores[i].length() < 2) {
                    b = false;
                    borrarCodigo();
                    break;
                }
            }
        }
        return b;
    }

    //verifica si el color se encuntra dentro de los colores validos
    public boolean validarColor(String color) {
        boolean b = false;
        for (int i = 0; i < coloresValidos.length; i++) {
            if (color.equals(coloresValidos[i])) {
                b = true;
                break;
            }
        }
        return b;
    }

    //agrega el color al HashMap codigo
    public boolean agregarColor(int n, String color) {
        boolean b = true;
        for (String i : codigo.values()) {
            if (i.equals(color)) {
                b = false;
                break;
            }
        }
        if (b == true) {
            codigo.put(n, color);
        }
        return b;
    }

    public void mostrarCodigo() {
        System.out.println(codigo);
    }

    public void borrarCodigo() {
        codigo.clear();
    }

    //getters y setters
    public HashMap<Integer, String> getCodigo() {
        return codigo;
    }

    public void setCodigo(HashMap<Integer, String> codigo) {
        this.codigo = codigo;
    }

    public String[] getColoresValidos() {
        return coloresValidos;
    }

    public void setColoresValidos(String[] coloresValidos) {
        this.coloresValidos = coloresValidos;
    }

    public String getCombinacion() {
        return combinacion;
    }

    public void setCombinacion(String combinacion) {
        this.combinacion = combinacion;
    }

}
