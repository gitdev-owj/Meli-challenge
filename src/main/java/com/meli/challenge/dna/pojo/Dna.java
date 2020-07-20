package com.meli.challenge.dna.pojo;

import java.io.Serializable;

public class Dna implements Serializable  {

    private static final long serialVersionUID = -3275890157305140356L;

    public  String [] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
