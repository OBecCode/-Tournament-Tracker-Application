package com.techelevator.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TournamentNotFoundException extends Exception{
    public TournamentNotFoundException(String message) {super(message);}

    public TournamentNotFoundException(){ this("Tournament not found.");}
}
