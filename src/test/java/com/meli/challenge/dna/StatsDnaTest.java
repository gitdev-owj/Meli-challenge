package com.meli.challenge.dna;

import com.meli.challenge.dna.dao.DnaSequenceDao;
import com.meli.challenge.dna.models.DnaSequence;
import com.meli.challenge.dna.services.imp.StatsDnaImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StatsDnaTest extends  BaseTestCase {

    @Mock
    DnaSequenceDao dnaSequenceDao;

    @InjectMocks
    StatsDnaImp statsDnaImp;

    @BeforeEach
    public void setup() {

    }


    @Test
    public void validatingRatio(){

        DnaSequence dnaSequenceMutant = new DnaSequence();
        dnaSequenceMutant.setIsMutante('S');
        dnaSequenceMutant.setDna("ABC");
        dnaSequenceMutant.setId(123L);

        DnaSequence dnaSequenceHuman = new DnaSequence();
        dnaSequenceHuman.setIsMutante('N');
        dnaSequenceHuman.setDna("ABC");
        dnaSequenceHuman.setId(123L);

        List<DnaSequence> listDna = new ArrayList<>();
        listDna.add(dnaSequenceMutant);
        listDna.add(dnaSequenceMutant);

        listDna.add(dnaSequenceHuman);

        when(dnaSequenceDao.findAll()).thenReturn(listDna);
        assertEquals(0.6666666666666666 , statsDnaImp.getStats().getRatio() );

    }

    @Test
    public void validatingQuantityMutantsSequence(){

        DnaSequence dnaSequenceMutant = new DnaSequence();
        dnaSequenceMutant.setIsMutante('S');
        dnaSequenceMutant.setDna("ABC");
        dnaSequenceMutant.setId(123L);

        DnaSequence dnaSequenceHuman = new DnaSequence();
        dnaSequenceHuman.setIsMutante('N');
        dnaSequenceHuman.setDna("ABC");
        dnaSequenceHuman.setId(123L);

        List<DnaSequence> listDna = new ArrayList<>();
        listDna.add(dnaSequenceMutant);
        listDna.add(dnaSequenceMutant);

        listDna.add(dnaSequenceHuman);

        when(dnaSequenceDao.findAll()).thenReturn(listDna);
        assertEquals(2 , statsDnaImp.getStats().getCount_mutant_dna() );

    }

    @Test
    public void validatingQuantityHumansSequence(){

        DnaSequence dnaSequenceMutant = new DnaSequence();
        dnaSequenceMutant.setIsMutante('S');
        dnaSequenceMutant.setDna("ABC");
        dnaSequenceMutant.setId(123L);

        DnaSequence dnaSequenceHuman = new DnaSequence();
        dnaSequenceHuman.setIsMutante('N');
        dnaSequenceHuman.setDna("ABC");
        dnaSequenceHuman.setId(123L);

        List<DnaSequence> listDna = new ArrayList<>();
        listDna.add(dnaSequenceMutant);
        listDna.add(dnaSequenceMutant);

        listDna.add(dnaSequenceHuman);

        when(dnaSequenceDao.findAll()).thenReturn(listDna);
        assertEquals(1 , statsDnaImp.getStats().getCount_human_dna() );

    }

}
