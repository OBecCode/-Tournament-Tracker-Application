package com.techelevator.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND, reason = "Invitation couldn't be found")
public class InvitationNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public InvitationNotFoundException() {
        super("Invitation not found");
    }

}
