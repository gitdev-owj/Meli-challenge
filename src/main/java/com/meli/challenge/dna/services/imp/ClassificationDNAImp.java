package com.meli.challenge.dna.services.imp;

import com.meli.challenge.dna.dao.DnaSequenceDao;
import com.meli.challenge.dna.exceptions.SequenceInvalidException;
import com.meli.challenge.dna.models.DnaSequence;
import com.meli.challenge.dna.services.ClassificationDNA;
import com.meli.challenge.dna.services.GenerateSequenceDNA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ClassificationDNAImp implements ClassificationDNA {

    private static Logger log = LoggerFactory.getLogger(ClassificationDNA.class);
    private char[][] DNA;
    @Autowired
    private GenerateSequenceDNA generateSequenceDNA;
    @Autowired
    private DnaSequenceDao dnaSequenceDao;
    private int foundSequence ;
    private Set<String> insertedSequencesDna ;

    public ClassificationDNAImp(){
        this.insertedSequencesDna = new HashSet<String>();
    }

    @Override
    public boolean isMutant(String[] dna) throws SequenceInvalidException {
        foundSequence = 0;
        DNA = generateSequenceDNA.getSequence(dna);

        validateHorizontalSequences();

        if (foundSequence < 2)
            validateVerticalSequences();
        if (foundSequence < 2)
            validateDiagonalSequence();
        /*if (foundSequence < 2)
            validateDiagonalInverse();*/

        String sequence = String.join("|", dna);
        char isMutant =  foundSequence < 2 ? 'N' : 'S';

        saveDna(sequence,isMutant);

        return foundSequence > 1; //validateDiagonalSequence();
    }

    private void validateDiagonalSequence() {
        int z = 0;
        for (int i = 0; i < DNA.length; i++ ) {
            char currentSeed;
            for (int j = 0; j < DNA.length; j ++) {
                currentSeed = DNA[i][j];
                if ((j + 4 <= DNA.length) && (i + 4 <= DNA.length)) {
                    if ((Character.compare(currentSeed, DNA[i + 1][j +1]) == 0) &&
                            (Character.compare(currentSeed, DNA[i + 2][j + 2]) == 0) &&
                            (Character.compare(currentSeed, DNA[i + 3][j + 3]) == 0)) {
                        System.out.println("Found D: " + currentSeed);
                        foundSequence += 1;
                    }
                }

                if ((j >= 3) && (i + 4) <= DNA.length) {
                    if ((Character.compare(currentSeed, DNA[i + 2][j - 2]) == 0) &&
                            (Character.compare(currentSeed, DNA[i + 1][j - 1]) == 0) &&
                            (Character.compare(currentSeed, DNA[i + 3][j - 3]) == 0)) {
                        log.info(String.format("Found DIV : %s ", currentSeed));
                        foundSequence += 1;
                    }
                }
            }
            if (foundSequence > 1)
                break;
        }
    }

    private void validateHorizontalSequences() {

        for (int i = 0; i < DNA.length; i++) {
            char currentSeed = Character.MIN_VALUE;
            for (int j = 0; j < DNA.length; j += 2) {
                if (currentSeed != Character.MIN_VALUE && Character.compare(currentSeed, DNA[i][j]) == 0) {
                    if ((Character.compare(currentSeed, DNA[i][j - 1]) == 0) &&
                            (Character.compare(currentSeed, DNA[i][j + 1]) == 0)) {
                        foundSequence += 1;
                        log.info(String.format("Found H :[%s]", currentSeed));
                    }
                }
                currentSeed = DNA[i][j];
            }
            if (foundSequence > 1)
                break;
        }
    }

    private void validateVerticalSequences() {

        for (int i = 2; i < DNA.length; i += 2) {
            char currentSeed ;
            for (int j = 0; j < DNA.length; j++) {
                currentSeed = DNA[i][j];
                if (Character.compare(currentSeed, DNA[i-2][j]) == 0) {
                    if ((Character.compare(currentSeed, DNA[i - 1][j]) == 0) &&
                            (Character.compare(currentSeed, DNA[i + 1][j]) == 0)) {
                        foundSequence += 1;
                        log.info(String.format("Found V :[  %s  ]", currentSeed));
                    }
                }

            }
            if (foundSequence > 1)
                break;
        }
    }

    private void saveDna(String dna,char classification){

        log.info(String.format("Save DNA : {%s , %s }", dna, classification));

        if (!insertedSequencesDna.contains(dna)) {
            insertedSequencesDna.add(dna);
            DnaSequence dnaSequence = new DnaSequence();
            dnaSequence.setDna(dna);
            dnaSequence.setIsMutante(classification);

            dnaSequenceDao.save(dnaSequence);

        }
    }

}
