package com.techelevator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Sports {

    @Min(value = 1, message = "Sport ID Should Be a Positive Number.")
    private int sportId;

    @NotBlank( message = "Sport Name is Required.")
    private String sportName;


    public Sports() {
    }

    public Sports(int sportId, String sportName) {
        this.sportId = sportId;
        this.sportName = sportName;
    }


    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }


}
