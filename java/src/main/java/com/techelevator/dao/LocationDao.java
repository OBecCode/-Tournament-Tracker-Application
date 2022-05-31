package com.techelevator.dao;

import com.techelevator.model.Location;
import com.techelevator.model.Sports;

import java.nio.file.LinkOption;
import java.util.List;

public interface LocationDao {
    Location getLocation(int locationId);
    List<Location> getAllLocation();
    List<Location> getLocationByState(String stateName);
    List<Location> getLocationByCity(String cityName);
    Location createLocation(Location location);
    Location updateLocation(int locationId);
    boolean deleteLocation(int locationId);
}
