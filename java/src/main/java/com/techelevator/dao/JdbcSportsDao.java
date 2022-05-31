package com.techelevator.dao;

import com.techelevator.model.Sports;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSportsDao implements SportsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSportsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sports getSport(int sportId) {
        Sports sport = new Sports();
        String sql = "SELECT sport_id, sport_name " +
                " FROM sports " +
                " WHERE sport_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sportId);
        if (results.next()) {
            sport = mapRowToSports(results);
        }
        return sport;
    }



    @Override
    public List<Sports> getAllSports() {
        List<Sports> sports = new ArrayList<>();
        String sql = "SELECT sport_id, sport_name " +
                " FROM sports; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            sports.add(mapRowToSports(results));
        }
        return sports;
    }

    @Override
    public Sports createSport(Sports sport) {
        String sql = "INSERT INTO sports (sport_name) " +
                " VALUES (?) RETURNING sport_id;";
        int newSportId = jdbcTemplate.queryForObject(sql, Integer.class,
                sport.getSportName());
        Sports newSport = getSport(newSportId);
        return newSport;
    }

    @Override
    public Sports updateSport(int sportId) {
        String sql = "UPDATE sports " +
                " SET sport_name = ? " +
                " WHERE sport_id = ?;";

        Sports sports = new Sports();
        jdbcTemplate.update(sql, sports.getSportName());
        return getSport(sportId);
    }




    @Override
    public boolean deleteSport(int sportId) {
        String sql = "DELETE FROM sports WHERE sport_id = ?;";
        jdbcTemplate.update(sql, sportId);

        if (getSport(sportId) == null) {
            return true;
        } else {
            return false;
        }


    }



    private Sports mapRowToSports(SqlRowSet results) {
        Sports sport = new Sports();
//     not sure if this line needed here*   int sportId = results.getInt("sport_id");
        sport.setSportId(results.getInt("sport_id"));
        sport.setSportName(results.getString("sport_name"));
        return sport;
    }
}
