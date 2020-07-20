package com.meli.challenge.dna;

import com.meli.challenge.dna.exceptions.SequenceInvalidException;
import com.meli.challenge.dna.services.GenerateSequenceDNA;
import com.meli.challenge.dna.services.imp.GenerateSequenceDNAImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
public class GenerateSequenceDNATest {

    GenerateSequenceDNA generateSequenceDNA;

    @BeforeEach
    public void setup(){
       generateSequenceDNA = new GenerateSequenceDNAImp();
    }

    @Test
    public void validatingInvalidSequenceException() throws SequenceInvalidException {
        Exception exception = assertThrows(SequenceInvalidException.class, () -> {

            String[] badSequence =  {"ATXCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
            generateSequenceDNA.getSequence(badSequence);
        });

        String expectedMessage = "The sequence has invalid characters. The characters accepted are A , C , T , G ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void validatingMatrizNonSquareSquenceException() throws SequenceInvalidException {
        Exception exception = assertThrows(SequenceInvalidException.class, () -> {

            String[] badSequence =  {"ATTCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTGA"};
            generateSequenceDNA.getSequence(badSequence);
        });

        String expectedMessage = "size of the sequence";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public  void validatingSizeMatrixSequence() throws SequenceInvalidException {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        char [][] dnaMatriz = generateSequenceDNA.getSequence(dna);

        assertEquals(36, dnaMatriz.length * dnaMatriz.length);

    }

    @Test
    public  void validatingNullDNASequence() throws SequenceInvalidException {
        Exception exception = assertThrows(SequenceInvalidException.class, () -> {
            String[] dna = null;
            char [][] dnaMatriz = generateSequenceDNA.getSequence(dna);
        });

        String expectedMessage = "Invalid entry";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


}
