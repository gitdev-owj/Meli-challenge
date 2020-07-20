package com.meli.challenge.dna.services;


import com.meli.challenge.dna.exceptions.SequenceInvalidException;

public interface ClassificationDNA {

    boolean isMutant(String[] dna) throws SequenceInvalidException;
}
