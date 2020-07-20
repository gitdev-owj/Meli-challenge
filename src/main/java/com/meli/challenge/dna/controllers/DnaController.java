package com.meli.challenge.dna.controllers;

import com.meli.challenge.dna.exceptions.SequenceInvalidException;
import com.meli.challenge.dna.pojo.Dna;
import com.meli.challenge.dna.pojo.Ratio;
import com.meli.challenge.dna.services.ClassificationDNA;
import com.meli.challenge.dna.services.StatsDna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DnaController {

    @Autowired
    private ClassificationDNA classificationDNA;
    @Autowired
    private StatsDna statsDna;

    @PostMapping("/mutant")
    public ResponseEntity isMutant(@RequestBody Dna dna) throws SequenceInvalidException {

        try {
            if (classificationDNA.isMutant(dna.getDna())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (SequenceInvalidException segEx) {
            return ResponseEntity.badRequest().body(segEx.getMessage());
        }
    }

    @GetMapping("/stats")
    public Ratio getStatsDna(){
        return statsDna.getStats();
    }
}
