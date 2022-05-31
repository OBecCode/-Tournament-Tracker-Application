package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class MatchNotFoundException extends Exception {


    public MatchNotFoundException(String message) {super(message);}

    public MatchNotFoundException(){ this("Match not found.");}

}