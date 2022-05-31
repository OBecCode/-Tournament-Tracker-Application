package com.techelevator.dao;

import com.techelevator.exception.TeamNotFoundException;
import com.techelevator.exception.TournamentNotFoundException;
import com.techelevator.model.Teams;
import com.techelevator.model.Tournament;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTournamentDao implements TournamentDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTournamentDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tournament create(Tournament tournament) {
        String sql = "INSERT INTO tournaments " +
                "(tournament_name, num_of_teams, start_date, end_date, sport_id, description, num_of_rounds, tournament_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING tournament_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                tournament.getTournamentName(), tournament.getNumOfTeams(),
                tournament.getStartDate(), tournament.getEndDate(),
                tournament.getSportId(), tournament.getDescription(),
                tournament.getNumOfRounds(), tournament.getTournamentType());
        if (newId != null) {
            return getTournamentById(newId);
        }
        return null;
    }

    @Override
    public Tournament getTournamentById(int tournamentId) {
        String sql = "SELECT * " +
                "FROM tournaments " +
                "WHERE tournament_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, tournamentId);
        if(rowSet.next()){
            return mapRowToTourney(rowSet);
        }
        return null;
    }

    @Override
    public List<Tournament> getAllTournaments() {
        List<Tournament> tournaments = new ArrayList<>();
        String sql = "SELECT tournament_id, tournament_name, num_of_teams, start_date, end_date, sport_id, description, num_of_rounds, tournament_type, organizer_id " +
                "FROM tournaments;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Tournament tournament = mapRowToTourney(results);
            tournaments.add(tournament);
        }
        return tournaments;
    }

    @Override
    public Tournament updateTournament(Tournament tournament, int tournamentId) {
        String sql = "UPDATE tournaments " +
                "SET tournament_id = ?, " +
                "tournament_name = ?, " +
                "num_of_teams = ?, " +
                "start_date = ?, " +
                "end_date = ?, " +
                "sport_id = ?, " +
                "description = ?, " +
                "num_of_rounds = ?, " +
                "tournament_type = ? " +
                "WHERE tournament_id = ?;";

        jdbcTemplate.update(sql, tournament.getTournamentId(), tournament.getTournamentName(), tournament.getNumOfTeams(),
                tournament.getStartDate(), tournament.getEndDate(), tournament.getSportId(), tournament.getDescription(), tournament.getNumOfRounds(),
                tournament.getTournamentType(), tournamentId);
        return getTournamentById(tournamentId);
    }


    @Override
    public boolean deleteTournament(int tournamentId) {
        String sql = "DELETE FROM tournaments WHERE tournament_id = ?;";
        jdbcTemplate.update(sql, tournamentId);
       if (getTournamentById(tournamentId) == null) {
           return true;
       } else {
           return false;
       }
    }

    @Override
    public void addTeamsToTournaments(Teams teams, int tournamentId)
                                      throws TeamNotFoundException, TournamentNotFoundException {

        String sql = "INSERT INTO tournament_teams(team_id, tournament_id) " +
                     "VALUES                      ((SELECT team_id FROM teams WHERE team_id = ? )," +
                                                  "(SELECT tournament_id FROM tournaments WHERE tournament_id = ?));";

        jdbcTemplate.update(sql, teams.getTeamId(), tournamentId);

    }

    @Override
    public List<Tournament> getTournamentsBySportId(int sportId) throws TournamentNotFoundException {
        List<Tournament> tournaments = new ArrayList<>();
       String sql = "SELECT tournament_id, tournament_name, num_of_teams, start_date, end_date, sport_id, description, num_of_rounds, tournament_type, organizer_id " +
                    "FROM tournaments " +
                    "WHERE sport_id = ?;";

      SqlRowSet results =  jdbcTemplate.queryForRowSet(sql, sportId);

        while (results.next()){
            Tournament tournament = mapRowToTourney(results);
            tournaments.add(tournament);
        }
        return tournaments;
    }




    private Tournament mapRowToTourney(SqlRowSet row){

        Tournament tournament = new Tournament();
        tournament.setTournamentId(row.getInt("tournament_id"));
        tournament.setNumOfTeams(row.getInt("num_of_teams"));
        tournament.setTournamentName(row.getString("tournament_name"));
        tournament.setStartDate(row.getDate("start_date").toLocalDate());
        tournament.setEndDate(row.getDate("end_date").toLocalDate());
        tournament.setSportId(row.getInt("sport_id"));
        tournament.setDescription(row.getString("description"));
        tournament.setNumOfRounds(row.getInt("num_of_rounds"));
        tournament.setTournamentType(row.getString("tournament_type"));
        return tournament;
    }
}
