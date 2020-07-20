package com.meli.challenge.dna;

import com.meli.challenge.dna.dao.DnaSequenceDao;
import com.meli.challenge.dna.exceptions.SequenceInvalidException;
import com.meli.challenge.dna.models.DnaSequence;
import com.meli.challenge.dna.services.GenerateSequenceDNA;
import com.meli.challenge.dna.services.imp.ClassificationDNAImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class ClassificationDNATest extends  BaseTestCase{

    @Mock
    GenerateSequenceDNA generateSequenceDNA;

    @Mock
    DnaSequenceDao dnaSequenceDao;

    @InjectMocks
    ClassificationDNAImp classificationDNA;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void validatingPositiveMutantSequence() throws SequenceInvalidException {

        String[] dnaSequence = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        char[][] sequenceMatrix = {{'A', 'T', 'G', 'C', 'G', 'A'},
                                    {'C', 'A', 'G', 'T', 'G', 'C'},
                                    {'T', 'T', 'A', 'T', 'G', 'T'},
                                    {'A', 'G', 'A', 'A', 'G', 'G'},
                                    {'C', 'C', 'C', 'C', 'T', 'A'},
                                    {'T', 'C', 'A', 'C', 'T', 'G'}};

        when(generateSequenceDNA.getSequence(dnaSequence)).thenReturn(sequenceMatrix);
        when(dnaSequenceDao.save(new DnaSequence())).thenReturn(new DnaSequence());
        assertEquals(true, classificationDNA.isMutant(dnaSequence));
    }

    @Test
    public void validatingPositiveVerticalMutantSequence() throws SequenceInvalidException {

        String[] dnaSequence = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        char[][] sequenceMatrix = {{'A', 'T', 'G', 'C', 'G', 'A'},
                                   {'C', 'T', 'G', 'T', 'G', 'C'},
                                   {'T', 'T', 'A', 'T', 'G', 'T'},
                                   {'A', 'T', 'A', 'A', 'G', 'G'},
                                   {'C', 'A', 'C', 'C', 'T', 'A'},
                                   {'T', 'C', 'A', 'C', 'T', 'G'}};

        when(generateSequenceDNA.getSequence(dnaSequence)).thenReturn(sequenceMatrix);
        when(dnaSequenceDao.save(new DnaSequence())).thenReturn(new DnaSequence());
        assertEquals(true, classificationDNA.isMutant(dnaSequence));
    }

    @Test
    public void validatingPositiveHorizontalMutantSequence() throws SequenceInvalidException {

        String[] dnaSequence = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        char[][] sequenceMatrix = {{'A', 'A', 'A', 'A', 'C', 'A'},
                                   {'C', 'T', 'G', 'T', 'G', 'C'},
                                   {'T', 'T', 'A', 'T', 'G', 'T'},
                                   {'A', 'T', 'A', 'A', 'G', 'G'},
                                   {'C', 'C', 'C', 'C', 'T', 'A'},
                                   {'T', 'C', 'A', 'C', 'T', 'G'}};

        when(generateSequenceDNA.getSequence(dnaSequence)).thenReturn(sequenceMatrix);
        when(dnaSequenceDao.save(new DnaSequence())).thenReturn(new DnaSequence());
        assertEquals(true, classificationDNA.isMutant(dnaSequence));
    }



    @Test
    public void validatingPositiveDiagonalMutantSequence() throws SequenceInvalidException {

        String[] dnaSequence = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        char[][] sequenceMatrix = { {'A', 'T', 'G', 'C', 'G', 'A'},
                                    {'C', 'A', 'G', 'T', 'T', 'C'},
                                    {'T', 'T', 'A', 'G', 'G', 'T'},
                                    {'A', 'G', 'A', 'A', 'G', 'G'},
                                    {'C', 'A', 'C', 'C', 'T', 'G'},
                                    {'T', 'C', 'A', 'C', 'T', 'G'}};

        when(generateSequenceDNA.getSequence(dnaSequence)).thenReturn(sequenceMatrix);
        when(dnaSequenceDao.save(new DnaSequence())).thenReturn(new DnaSequence());
        assertEquals(true, classificationDNA.isMutant(dnaSequence));
    }

    @Test
    public void validatingPositiveDiagonalInverseMutantSequence() throws SequenceInvalidException {

        String[] dnaSequence = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        char[][] sequenceMatrix = { {'A', 'T', 'G', 'T', 'G', 'A'},
                                    {'C', 'C', 'T', 'T', 'T', 'C'},
                                    {'T', 'T', 'A', 'A', 'G', 'T'},
                                    {'T', 'G', 'A', 'A', 'G', 'G'},
                                    {'C', 'A', 'C', 'C', 'T', 'G'},
                                    {'A', 'C', 'A', 'C', 'T', 'G'}};

        when(generateSequenceDNA.getSequence(dnaSequence)).thenReturn(sequenceMatrix);
        when(dnaSequenceDao.save(new DnaSequence())).thenReturn(new DnaSequence());
        assertEquals(true, classificationDNA.isMutant(dnaSequence));
    }



    @Test
    public void validatingNegativeMutantSequence() throws SequenceInvalidException {

        String[] dnaSequence = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        char[][] sequenceMatrix = {{'A', 'T', 'G', 'C', 'G', 'A'},
                                   {'C', 'G', 'G', 'T', 'A', 'C'},
                                   {'T', 'T', 'A', 'T', 'G', 'T'},
                                   {'A', 'G', 'A', 'A', 'G', 'G'},
                                   {'C', 'A', 'C', 'C', 'T', 'A'},
                                   {'T', 'C', 'A', 'C', 'T', 'G'}};

        when(generateSequenceDNA.getSequence(dnaSequence)).thenReturn(sequenceMatrix);
        when(dnaSequenceDao.save(new DnaSequence())).thenReturn(new DnaSequence());
        assertEquals(false, classificationDNA.isMutant(dnaSequence));
    }



}
