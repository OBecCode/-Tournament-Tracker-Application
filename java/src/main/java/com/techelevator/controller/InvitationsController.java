package com.techelevator.controller;


import com.techelevator.dao.InvitationDao;
import com.techelevator.exception.InvitationFailedException;
import com.techelevator.exception.InvitationNotFoundException;
import com.techelevator.exception.TeamNotFoundException;
import com.techelevator.model.Invitation;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/invitations")
public class InvitationsController {


    private final InvitationDao invitationDao;

    public InvitationsController(InvitationDao invitationDao) {
        this.invitationDao = invitationDao;
    }

    //below method is good to go, it's been tested

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void createInvite(@RequestBody Invitation invitation) throws InvitationFailedException {
        invitationDao.createInvitation(invitation);

    }

    //below method is good to go, it's been tested

    @RequestMapping(path = "/tournament/{id}", method = RequestMethod.GET)
    public List<Invitation> getInvByTournamentId(@PathVariable("id")int tournamentId) throws InvitationNotFoundException {
      return invitationDao.getInvitationsByTournamentId(tournamentId);

    }

    //below method is good to go, it's been tested

    @RequestMapping(path =  "/status", method = RequestMethod.GET)
    public List<Invitation> getInvByStatus(String status) throws InvitationNotFoundException {
        return invitationDao.getInvitationByStatus(status);
    }

    //below method is good to, it's been tested

    @RequestMapping(path = "/team/{id}", method = RequestMethod.GET)
    public List<Invitation> getInvByTeamId(@PathVariable("id")int teamId) throws InvitationFailedException, TeamNotFoundException {

        return invitationDao.getInvitationByTeamId(teamId);
    }


    //below method is good to go, it's been tested

    @RequestMapping(path = "/organizer/{id}", method = RequestMethod.GET)
    public List<Invitation> getInvByOrganizerId(@PathVariable("id")int organizerId)throws InvitationFailedException{

        return invitationDao.getInvitationsByOrganizer(organizerId);
    }

    //below method is good to go it's been tested

    @RequestMapping(path = "/status", method = RequestMethod.PUT)
    public Invitation updateInvitationStatus(@RequestBody Invitation invitation)throws InvitationNotFoundException{
        return invitationDao.updateInvitationStatus(invitation);
    }




}





