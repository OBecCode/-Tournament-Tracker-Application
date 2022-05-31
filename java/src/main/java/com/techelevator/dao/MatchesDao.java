package com.techelevator.dao;

import com.techelevator.model.Matches;

import java.time.LocalDate;
import java.util.List;

public interface MatchesDao {


    Matches getMatch(int matchId);
    List<Matches> getAllMatches();
    List<Matches> getMatchesByDate(LocalDate date);
    List<Matches> getMatchesByTournament(int tournamentId);
    Matches createMatch(Matches match, int tournamentId);
    Matches updateMatch(Matches match);
    Matches setMatchWinner(Matches match, int matchId);
    Matches updateBracketMatches(Matches matches);
    boolean deleteMatch(int matchId);
    List<Matches> getMatchesByTournamentAndRound(int tournamentId, int roundNumber);
    int getCurrentRoundNumber(int tournament_id);
}
