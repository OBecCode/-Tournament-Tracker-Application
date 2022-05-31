package com.techelevator.model;

import javax.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Teams {

    @Min(value = 1, message = "Team ID Should Be a Positive Number.")
    private int teamId;

    @Min(value = 1, message = "Team Size Should Be a Positive Number.")
    private int teamSize;
    private String teamName;

    public Teams() {}

    public Teams(int teamId, int teamSize, String teamName) {
        this.teamId = teamId;
        this.teamSize = teamSize;
        this.teamName = teamName;
    }


    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }


}
