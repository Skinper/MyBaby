package br.ufop.wagner.mybabywagner;

import java.io.Serializable;

public class Peso implements Serializable {

    private float peso = 0f;
    private String data = "0/0/0";

    public Peso(float aDouble, String s) {
        this.peso = aDouble;
        this.data = s;

    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getData() {
        return data;
    }

    public void setData(String dia) {
        this.data = dia;
    }
}
