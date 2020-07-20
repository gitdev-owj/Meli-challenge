package com.meli.challenge.dna.exceptions;

public class SequenceInvalidException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public SequenceInvalidException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
