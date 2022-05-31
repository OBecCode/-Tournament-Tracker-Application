package com.techelevator.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)



public class SportNotFoundException extends Exception {

    public SportNotFoundException(String message) {super(message);}

    public SportNotFoundException(){ this("Sport not found.");}







}
