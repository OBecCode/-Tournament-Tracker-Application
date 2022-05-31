package com.techelevator.model;

public class TournamentMatch {
     private int matchID;
     private int tournamentId;
     private String startDate;
     private String startTime;
     private int homeTeamId;
     private int awayTeamId;

    public TournamentMatch(int matchID, int tournamentId, String startDate, String startTime, int homeTeamId, int awayTeamId) {
        this.matchID = matchID;
        this.tournamentId = tournamentId;
        this.startDate = startDate;
        this.startTime = startTime;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
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
}
