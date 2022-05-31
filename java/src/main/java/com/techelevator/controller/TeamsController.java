package com.techelevator.controller;

import com.techelevator.dao.PlayersDao;
import com.techelevator.dao.TeamsDao;
import com.techelevator.exception.PlayerNotFoundException;
import com.techelevator.exception.TeamNotFoundException;
import com.techelevator.model.Players;
import com.techelevator.model.Teams;
import com.techelevator.exception.AuthorizationException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import com.techelevator.exception.TeamNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
public class TeamsController {

    private final TeamsDao teamsDao;
    private final PlayersDao playersDao;


    public TeamsController(TeamsDao teamsDao, PlayersDao playersDao) { this.teamsDao = teamsDao; this.playersDao = playersDao; }

    //tested and ok
    @GetMapping("/teams")
    public List<Teams> listAllTeams() throws TeamNotFoundException {
        return teamsDao.listAllTeams();
    }

    // tested and ok
    @GetMapping("/teams/{id}")
    public Teams getTeamById(@PathVariable ("id") int teamId) throws TeamNotFoundException {

        Teams result = teamsDao.getTeamById(teamId);
        if (result == null) {
            throw new TeamNotFoundException("No Team found with that id. ");
        } else {
            return result;
        }
    }



    @GetMapping("/teams/{id}/teamSize")
    public Teams getTeamSize(@PathVariable ("id")  int teamSize) throws TeamNotFoundException {

        Teams result = teamsDao.getTeamSize(teamSize);
        if (result == null) {
            throw new TeamNotFoundException("No Team Found with that size");
        } else {
            return result;
        }
    }

    @GetMapping("/teams/{id}/players")
    public List<Players> getPlayersByTeam(@PathVariable ("id") int teamId){
        return playersDao.getPlayerByTeam(teamId);
    }




    @RequestMapping(path = "/teams", method = RequestMethod.POST)
    public Teams postTeam(@RequestBody Teams newTeam) {
        return teamsDao.createTeam(newTeam);
    }


    @RequestMapping(path = "/teams/{id}", method = RequestMethod.PUT)
    public Teams putTeam(@PathVariable ("id") int teamId, @RequestBody Teams updatedTeam) throws TeamNotFoundException {

       if (teamsDao.updateTeam(teamId) != null) {
            return updatedTeam;
        } else {
           throw new TeamNotFoundException("No Team found with that id. ");
        }
    }

    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable int teamId) throws TeamNotFoundException {
      if (!teamsDao.deleteTeam(teamId)) {
           throw new TeamNotFoundException("Team not found to delete.");
         }

     }

     @RequestMapping(path = "/teams/{id}/player", method = RequestMethod.POST)
     public void playersToTeam(@PathVariable ("id") int teamId, @RequestBody Players players) throws TeamNotFoundException, PlayerNotFoundException {
            teamsDao.addPlayersToTeam(players, teamId);

     }

}

