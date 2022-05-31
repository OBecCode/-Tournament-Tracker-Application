package com.techelevator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Players {

    @Min(value = 1, message = "Player ID Should Be a Positive Number.")
    private int playerId;

    @Min(value = 1, message = "User ID Should Be a Positive Number.")
    private int userId;

    @NotBlank(message = "Player Name is Required.")
    private String playerName;


    public Players () {}

    public Players(int playerId, int userId, String playerName) {
        this.playerId = playerId;
        this.userId = userId;
        this.playerName = playerName;
    }



    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


}
