package com.techelevator.controller;

import com.techelevator.dao.PlayersDao;
import com.techelevator.dao.TeamsDao;
import com.techelevator.exception.TeamNotFoundException;
import com.techelevator.model.Players;
import com.techelevator.model.Teams;
import com.techelevator.model.Tournament;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.server.ResponseStatusException;
import com.techelevator.exception.PlayerNotFoundException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
public class PlayersController {


    private final PlayersDao playersDao;
    private final TeamsDao teamsDao;

    public PlayersController(PlayersDao playersDao, TeamsDao teamsDao) {
        this.playersDao = playersDao;
        this.teamsDao = teamsDao;
    }


    @GetMapping("/players")
    public List<Players> listAllPlayers() {
        return playersDao.listAllPlayers();
    }

    @GetMapping("/players/{id}")
    public Players getPlayerById(@PathVariable ("id")  int playerId) throws PlayerNotFoundException {

        Players result = playersDao.getPlayerById(playerId);
        if (result == null) {
            throw new PlayerNotFoundException("No Player found with that id. ");
        } else {
            return result;
        }

    }
        @GetMapping("/players/user/{userId}")
        public Players getPlayerByUserId(@PathVariable ("userId") int userId) throws PlayerNotFoundException{

            Players result = playersDao.getPlayerByUserId(userId);
            if (result == null) {
                throw new PlayerNotFoundException("No Player found with that user id. ");
            } else {
                return result;
            }

        }

    @GetMapping("/players/{id}/team")
    public Teams getPlayerTeam(@PathVariable ("id")  int playerId) throws TeamNotFoundException {
        Teams result = teamsDao.getTeamByPlayerId(playerId);
        if (result == null) {
            throw new TeamNotFoundException("No Team found with that id. ");
        } else {
            return result;
        }
    }

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public Players postPlayer(@RequestBody Players newPlayer) {
        return playersDao.createPlayer(newPlayer);
    }


    @RequestMapping(path = "/players/{id}", method = RequestMethod.PUT)
    public Players putPlayer(@PathVariable ("id") int playerId, @RequestBody Players updatedPlayer) throws PlayerNotFoundException {

        if (playersDao.updatePlayer(updatedPlayer) != null) {
            return updatedPlayer;
        } else {
            throw new PlayerNotFoundException("Player not found to update. ");
        }

    }


    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable int playerId) throws PlayerNotFoundException {
        if (!playersDao.deletePlayer(playerId)) {
            throw new PlayerNotFoundException("Player not found to delete. ");
        }

    }

}
