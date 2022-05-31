package com.techelevator.dao;

import com.techelevator.model.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component

public class JdbcLocationDao implements LocationDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcLocationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Location getLocation(int locationId) {
        Location location = new Location();
        String sql = "SELECT location_id, state_name, city_name " +
                " FROM locations " +
                " WHERE location_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, locationId);
        if (results.next()) {
            location = mapRowToLocation(results);
        }
        return location;
    }

    @Override
    public List<Location> getAllLocation() {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT location_id, state_name, city_name " +
                " FROM locations ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            locations.add(mapRowToLocation(results));
        }
        return locations;
    }

    @Override
    public List<Location> getLocationByState(String stateName) {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT location_id, state_name, city_name " +
                " FROM locations " +
                " WHERE state_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            locations.add(mapRowToLocation(results));
        }
        return locations;
    }

    @Override
    public List<Location> getLocationByCity(String cityName) {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT location_id, state_name, city_name " +
                " FROM locations " +
                " WHERE city_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            locations.add(mapRowToLocation(results));
        }
        return locations;
    }

    @Override
    public Location createLocation(Location location) {
        String sql = "INSERT INTO locations (state_name, city_name) " +
                "VALUES ( ?, ?) RETURNING location_id;";
        int newLocationId = jdbcTemplate.queryForObject(sql, Integer.class, location.getStateName(), location.getCityName());
        Location newLocation = getLocation(newLocationId);
        return newLocation;
    }

    @Override
    public Location updateLocation(int locationId) {
        String sql = "UPDATE locations " +
                " SET location_id = ? " +
                " state_name = ? " +
                " city_name = ? " +
                " WHERE location_id = ?;";

        Location location = new Location();
        jdbcTemplate.update(sql, location.getLocationId(), location.getStateName(), location.getCityName());
        return getLocation(locationId);
    }

    @Override
    public boolean deleteLocation(int locationId) {
        String sql = "DELETE FROM locations WHERE location_id = ?;";
        jdbcTemplate.update(sql, locationId);

        if (getLocation(locationId) == null) {
            return true;
        } else {
            return false;
        }
    }

    private Location mapRowToLocation(SqlRowSet results) {
        Location location = new Location();
        location.setLocationId(results.getInt("location_id"));
        location.setStateName(results.getString("state_name"));
        location.setCityName(results.getString("city_name"));
        return location;
    }
}