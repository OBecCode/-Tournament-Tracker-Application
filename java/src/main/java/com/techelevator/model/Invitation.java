package com.techelevator.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Invitation {

    @Min(value = 1, message = "invitation id must be a positive integer value")
   int invitationId;

    @Min(value = 1, message = "tournament id must be a positive integer value")
   int tournamentId;

    @Min(value = 1, message = "team id must be a positive integer value")
   int teamId;

    @Min(value = 1, message = "organizer id must be a positive integer value")
   int organizerId;

    @NotNull(message = "invite status cannot be empty, it must contain a status pertaining to the users response eg.(NOT_ACCEPTED)")
    String inviteStatus;

    String tournamentName;

    String playerName;

    String teamName;




    public Invitation(){}



    public Invitation(int invitationId, int tournamentId, int teamId,
                      int organizerId, String inviteStatus, String tournamentName,
                      String playerName, String teamName) {


        this.invitationId = invitationId;
        this.tournamentId = tournamentId;
        this.teamId = teamId;
        this.organizerId = organizerId;
        this.inviteStatus = inviteStatus;
        this.tournamentName = tournamentName;
        this.playerName = playerName;
        this.teamName = teamName;
    }

    public int getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(int invitationId) {
        this.invitationId = invitationId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public String getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(String inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
