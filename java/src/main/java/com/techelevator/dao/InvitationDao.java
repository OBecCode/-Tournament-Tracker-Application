package com.techelevator.dao;

import com.techelevator.model.Invitation;
import com.techelevator.model.Teams;

import java.util.List;

public interface InvitationDao {

    void createInvitation(Invitation invitation);

    List<Invitation> getInvitationByStatus(String status);

    List<Invitation> getInvitationByTeamId(int id);

    List<Invitation> getInvitationsByOrganizer (int id);

    Invitation updateInvitationStatus(Invitation invitation);

    List<Invitation> getInvitationsByTournamentId(int tournamentId);



}
