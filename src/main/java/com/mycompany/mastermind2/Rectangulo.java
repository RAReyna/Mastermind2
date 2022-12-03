/*
 * Esta clase  representa un rectangulo en canvas
 */
package com.mycompany.mastermind2;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 *
 */
public class Rectangulo {

    private Rectangle.Double rectangle;
    private Color color;
    private int lado1;
    private int lado2;
    private int xPosition;
    private int yPosition;
    private Canvas canvas;

    
    public Rectangulo(int xPos, int yPos, int l1, int l2,Color ballColor, Canvas drawingCanvas) {
        xPosition = xPos;
        yPosition = yPos;
        lado1 = l1;
        lado2 = l2;
        color = ballColor;
        canvas = drawingCanvas;
    }

    public void draw() {
        canvas.setForegroundColor(color);
        canvas.fillRectangle(xPosition, yPosition, lado1, lado2);
    }

    public void erase() {
        canvas.eraseRectangle(xPosition, yPosition, lado1, lado2);
    }

    public void move(int x, int y) {
        erase();
        xPosition = x;
        yPosition = y;
        draw();
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }
}
