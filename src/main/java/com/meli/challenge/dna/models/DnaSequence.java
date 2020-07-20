package com.meli.challenge.dna.models;

import javax.persistence.*;

@Entity
@Table(name ="dnssequences")
public class DnaSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dna;
    @Column(
            name = "ismutant"
    )
    private char isMutante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public char getIsMutante() {
        return isMutante;
    }

    public void setIsMutante(char isMutante) {
        this.isMutante = isMutante;
    }
}
