package com.techelevator.model;

import javax.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class Matches {


    private int matchId;

    @Min(value = 1, message = "Tournament ID Should Be a Positive Number.")
    private int tournamentId;


    private LocalDate startDate;
    private LocalTime startTime;

    @Min(value = 1, message = "Home Team ID Should Be a Positive Number.")
    private int homeTeamId;

    @Min(value = 1, message = "Away Team ID Should Be a Positive Number.")
    private int awayTeamId;
    private int locationId;
    private int winningTeamId;
    private int roundNumber;


    public Matches() {}
    public Matches(int homeTeamId, int awayTeamId){
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    public Matches(int matchId, int tournamentId, LocalDate date, LocalTime startTime, int homeTeamId, int awayTeamId,
                   int locationId, int winningTeamId, int roundNumber) {
        this.matchId = matchId;
        this.tournamentId = tournamentId;
        this.startDate = date;
        this.startTime = startTime;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.locationId = locationId;
        this.winningTeamId = winningTeamId;
        this.roundNumber = roundNumber;
    }



    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getWinningTeamId() {
        return winningTeamId;
    }

    public void setWinningTeamId(int winningTeamId) {
        this.winningTeamId = winningTeamId;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
}



