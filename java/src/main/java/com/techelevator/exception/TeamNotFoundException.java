package com.techelevator.exception;

public class TeamNotFoundException extends Exception{
    public TeamNotFoundException(String message){super(message);}

    public TeamNotFoundException(){this("Team not found.");}
}
