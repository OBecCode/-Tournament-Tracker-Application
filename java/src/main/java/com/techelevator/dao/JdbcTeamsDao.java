package com.techelevator.dao;

import com.techelevator.model.Teams;
import com.techelevator.model.Players;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.security.Principal;


@Component
public class JdbcTeamsDao implements TeamsDao {

    private JdbcTemplate jdbcTemplate;


    public JdbcTeamsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Teams> listAllTeams() {
        List<Teams> teams = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM teams; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            teams.add(mapRowToTeams(results));

        }
        return teams;
    }


    @Override
    public Teams getTeamById(int teamId) {
        Teams team = new Teams();
        String sql = "SELECT team_id, team_name, team_size " +
                "FROM teams " +
                "WHERE team_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
        if (results.next()) {
            team = mapRowToTeams(results);

        }
        return team;
    }


    @Override
    public Teams getTeamSize(int teamId) {
        Teams team = new Teams();
        String sql = "SELECT team_size " +
                "FROM teams " +
                "WHERE team_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
        if (results.next()) {
            team = mapRowToTeams(results);

        }
        return team;
    }


    @Override
    public Teams createTeam(Teams team) {
        String sql = "INSERT INTO teams (team_name, team_size) VALUES (?, ?) RETURNING team_id;";
        int newTeamId = jdbcTemplate.queryForObject(sql, Integer.class, team.getTeamName(), team.getTeamSize());
        Teams newTeam = getTeamById(newTeamId);
        return newTeam;
    }


    @Override
    public Teams updateTeam(int teamId) {
        String sql = "UPDATE teams " +
                " SET team_size = " +
                " WHERE team_id = ?;";
        Teams teams = new Teams();
        jdbcTemplate.update(sql, teams.getTeamSize());
        return getTeamById(teamId);
    }


    @Override
    public boolean deleteTeam(int teamId) {
        String sql = "DELETE FROM teams WHERE team_id = ?;";
        jdbcTemplate.update(sql, teamId);
        if (getTeamById(teamId) == null) {
            return true;
        } else {
            return false;
        }
    }
    public List<Teams> getTeamsByTournamentId(int id){
        List<Teams> tournamentTeams = new ArrayList<>();
        String sql = "SELECT team_id, team_name, team_size " +
                "FROM teams " +
                "JOIN tournament_teams USING(team_id) " +
                "JOIN tournaments USING (tournament_id) " +
                "WHERE tournament_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while(results.next()){
            tournamentTeams.add(mapRowToTeams(results));
        }
        return tournamentTeams;
    }

        public Teams getTeamByPlayerId(int playerId){
            Teams team = new Teams();
            String sql = "SELECT team_id, team_name, team_size " +
                    "FROM teams " +
                    "JOIN player_team USING(team_id) " +
                    "WHERE player_id = ?;";

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playerId);
            if (results.next()) {
                team = mapRowToTeams(results);
            }
            return team;
        }

    @Override
    public List<Teams> getWinningTeamsByRoundNumber(int tournamentId, int roundNumber) {
        List<Teams> winningTeams = new ArrayList<>();
        String sql = "SELECT team_id, team_name, team_size " +
                "FROM teams " +
                "JOIN matches on matches.winning_team_id = teams.team_id " +
                " WHERE matches.tournament_id = ? AND matches.round_number = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournamentId, roundNumber);
        while(results.next()){
            winningTeams.add(mapRowToTeams(results));
            System.out.println(mapRowToTeams(results));
        }
        return winningTeams;
    }

    @Override
    public void addPlayersToTeam(Players player, int teamId) {

        String sql = "INSERT INTO player_team (player_id, team_id) " +
                     "VALUES                  ((SELECT player_id FROM players WHERE player_id = ?), " +
                                             "(SELECT team_id FROM teams WHERE team_id = ?));";

        jdbcTemplate.update(sql, player.getPlayerId(), teamId);

    }


    private Teams mapRowToTeams (SqlRowSet results) {
            Teams team = new Teams();
            team.setTeamId(results.getInt("team_id"));
            team.setTeamName(results.getString("team_name"));
            team.setTeamSize(results.getInt("team_size"));
            return team;
        }


}
