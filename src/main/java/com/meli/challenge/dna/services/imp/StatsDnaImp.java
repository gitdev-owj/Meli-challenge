package com.meli.challenge.dna.services.imp;

import com.meli.challenge.dna.dao.DnaSequenceDao;
import com.meli.challenge.dna.models.DnaSequence;
import com.meli.challenge.dna.pojo.Ratio;
import com.meli.challenge.dna.services.StatsDna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsDnaImp implements StatsDna {

    @Autowired
    private DnaSequenceDao dnaSequenceDao;

    @Override
    public Ratio getStats() {

        List<DnaSequence> listDna = (List<DnaSequence>)dnaSequenceDao.findAll();

        long cantMutants = listDna.stream()
                .filter(d -> d.getIsMutante() == 'S')
                .count();

        Ratio ratio = new Ratio();
        ratio.setCount_human_dna(listDna.size() - (int) cantMutants );
        ratio.setCount_mutant_dna((int)cantMutants);
        ratio.setRatio( ( 1.0 * cantMutants / listDna.size()));


        return ratio;
    }
}
