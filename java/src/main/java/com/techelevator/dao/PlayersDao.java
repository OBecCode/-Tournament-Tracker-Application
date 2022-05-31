package com.techelevator.dao;

import com.techelevator.model.Players;
import java.util.List;

public interface PlayersDao {


    List<Players> listAllPlayers();
    Players getPlayerById(int playerId);
    List<Players> getPlayerByTeamId(int playerId);


    Players getPlayerByUserId(int userId);
    Players createPlayer(Players player);

     Players updatePlayer(Players updatedPlayer);
    boolean deletePlayer(int playerId);
    List<Players> getPlayerByTeam(int teamId);

}
