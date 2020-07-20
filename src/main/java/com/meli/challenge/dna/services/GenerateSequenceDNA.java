package com.meli.challenge.dna.services;

import com.meli.challenge.dna.exceptions.SequenceInvalidException;

public interface GenerateSequenceDNA {

    char[][] getSequence(String sequence[]) throws SequenceInvalidException;
}
