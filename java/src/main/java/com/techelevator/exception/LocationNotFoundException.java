package com.techelevator.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND, reason = "location couldn't be found")

public class LocationNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;
    public LocationNotFoundException(String s) {super("Location not found.");}


}
