package com.elena.app;

/**
 * Created by elena on 08/04/14.
 */

import java.io.Serializable;


public class Tienda implements Serializable {


    private String supermercado;
    private double pan;
    private double manzana;


    public String getSupermercado() {
        return supermercado;
    }
    public void setSupermercado(String supermercado) {
        this.supermercado = supermercado;
    }
    public double getPan() {
        return pan;
    }
    public void setPan(double pan) {
        this.pan = pan;
    }
    public double getManzana() {
        return manzana;
    }
    public void setManzana(double manzana) {
        this.manzana = manzana;
    }
    public String toStringman() {
        return String.format("%s: euro %f", supermercado, manzana);
    }
    public String toStringpan() {
        return String.format("%s: euro %f", supermercado, pan);
    }
}