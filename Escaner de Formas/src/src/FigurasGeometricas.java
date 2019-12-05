/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author YOZABETH GARCIA
 */
public class FigurasGeometricas {

    int anchoPre = 200;
    int altoPre = 200;

    Rectangle2D rectangulo;
    Ellipse2D elipse;
    Polygon poligono;

    public Rectangle2D Cuadrado() {
        rectangulo = new Rectangle2D.Double(10, 10, anchoPre - 20, altoPre - 20);
        return rectangulo;
    }

    public Rectangle2D Rectangulo() {
        rectangulo = new Rectangle2D.Double(10, 50, anchoPre - 20, altoPre - 100);
        return rectangulo;
    }

    public Ellipse2D Elipse() {
        elipse = new Ellipse2D.Double(10, 50, anchoPre - 20, altoPre - 100);
        return elipse;
    }

    public Ellipse2D Circulo() {
        elipse = new Ellipse2D.Double(10, 10, anchoPre - 20, altoPre - 20);
        return elipse;
    }

    public Polygon Tringulo() {
        int x[] = {10, 100, 190};
        int y[] = {190, 10, 190};
        poligono = new Polygon(x, y, 3);
        return poligono;
    }
    
    public Polygon Trapecio() {
        int x[] = {10, 50, 150, 190};
        int y[] = {190, 10, 10, 190};
        poligono = new Polygon(x, y, 4);
        return poligono;
    }
    
    public Polygon Hexagono() {
        int x[] = {10, 50, 150, 190, 150, 50};
        int y[] = {100, 10, 10, 100, 190, 190};
        poligono = new Polygon(x, y, 6);
        return poligono;
    }
    
    /*public Polygon Pentagono() {
        int x[] = {10, 100, 190};
        int y[] = {190, 10, 190};
        poligono = new Polygon(x, y, 5);
        return poligono;
    }
    
    public Polygon Romboide() {
        int x[] = {10, 100, 190};
        int y[] = {190, 10, 190};
        poligono = new Polygon(x, y, 3);
        return poligono;
    }*/
    
}
