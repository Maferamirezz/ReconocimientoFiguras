/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

import java.awt.image.BufferedImage;

/**
 * 
 * @author Luis Benitez
 */
public class figuraAgregada {
    
    private int x;
    private int y;
    private String nombre;
    private BufferedImage buffer; //buffer de una subimagen de toda la imagen principal
    
    
    public  figuraAgregada(String n,int cx,int cy /* BufferedImage b*/){
        nombre=n;
        x=cx;
        y=cy;
//        buffer=b;
    }

   
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

   

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }
    
}
