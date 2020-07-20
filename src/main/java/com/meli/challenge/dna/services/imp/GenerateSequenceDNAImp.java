package com.meli.challenge.dna.services.imp;

import com.meli.challenge.dna.services.GenerateSequenceDNA;
import com.meli.challenge.dna.exceptions.SequenceInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class GenerateSequenceDNAImp implements GenerateSequenceDNA {

    private Logger log = LoggerFactory.getLogger(GenerateSequenceDNA.class);

    private Pattern sequencePatternValid =  Pattern.compile("[actg]+", Pattern.CASE_INSENSITIVE);

    @Override
    public char[][] getSequence(String[] sequence) throws SequenceInvalidException {

        if (sequence == null){
            log.error("Invalid entry NULL data sequence DNA ");
            throw new SequenceInvalidException("Invalid entry NULL data sequence DNA");
        }

        char [][] sequenceDNA = new char[sequence.length][sequence.length];

        for (int index = 0 ; index < sequence.length ; index++){

             if (!isValidNumberSequences(sequence[index], sequence.length)){
                 log.error(String.format("size of the sequence %s is not valid, it must be %d",sequence[index],sequence.length));
                 throw new SequenceInvalidException(String.format("size of the sequence %s is not valid, it must be %d",sequence[index],sequence.length ));
             }
             else if (isValidSequence(sequence[index])){
                 log.error("The sequence has invalid characters. The characters accepted are A , C , T , G");
                 throw new SequenceInvalidException("The sequence has invalid characters. The characters accepted are A , C , T , G ");
             }else {
                 log.info("Valid Sequence");
                 sequenceDNA[index] = sequence[index].toLowerCase().toCharArray();
             }
        }

        return sequenceDNA;
    }

    private boolean isValidSequence (String sequence){
        return !sequencePatternValid.matcher(sequence).matches();
    }

    private boolean  isValidNumberSequences (String sequence , int size ){
        return sequence.length() == size ;
    }
}
