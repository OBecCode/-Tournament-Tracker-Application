package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED )
public class InvitationFailedException extends Exception{

    private static final long serialVersionUID = 1L;

   public InvitationFailedException(){
       super("Could not be created, I don't know why so please don't ask.");
    }
}
