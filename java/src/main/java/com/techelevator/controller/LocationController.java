package com.techelevator.controller;

import com.techelevator.dao.LocationDao;
import com.techelevator.exception.LocationNotFoundException;
import com.techelevator.model.Location;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin
public class LocationController {
    private final LocationDao locationDao;

    public LocationController(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @GetMapping("/locations/{id}")
    public Location getLocation(@PathVariable("id") int locationId) throws LocationNotFoundException {
        Location result = locationDao.getLocation(locationId);
        if (result == null) {
            throw new LocationNotFoundException("No Location found!");
        } else {
            return result;
        }
    }

    @GetMapping("/locations")
    public List<Location> getAllLocation() {
        return locationDao.getAllLocation();
    }

    @RequestMapping(path = "/locations", method = RequestMethod.POST)
    public Location postLocation(@RequestBody Location newLocation) {
        return locationDao.createLocation(newLocation);
    }

    @RequestMapping(path = "/location/{id}", method = RequestMethod.PUT)
    public Location putLocation(@PathVariable("id") int locationId, @RequestBody Location updatedLocation) throws LocationNotFoundException {
        if (locationDao.updateLocation(locationId) != null) {
            return updatedLocation;
        } else {
            throw new LocationNotFoundException("No Location Found!");
        }
    }

    @DeleteMapping("/locations/{id}")
    public void deleteLocation(@PathVariable("id") int locationId) throws LocationNotFoundException {
        if (!locationDao.deleteLocation(locationId)) {
            throw new LocationNotFoundException("Location not found to delete. ");
        }
    }

    @GetMapping("/locations/state/{stateName}")
    public List<Location> getLocationsByState(@PathVariable String stateName) {
        return locationDao.getLocationByState(stateName);
    }

    @GetMapping("/locations/city/{cityName}")
    public List<Location> getLocationsByCity(@PathVariable String cityName) {
        return locationDao.getLocationByCity(cityName);
    }

}
