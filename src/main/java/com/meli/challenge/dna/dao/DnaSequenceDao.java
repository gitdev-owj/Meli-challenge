package com.meli.challenge.dna.dao;


import com.meli.challenge.dna.models.DnaSequence;
import org.springframework.data.repository.CrudRepository;

public interface DnaSequenceDao extends CrudRepository<DnaSequence, Long> {

}
