package com.techelevator.controller;

import com.techelevator.dao.SportsDao;
import com.techelevator.model.Sports;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.server.ResponseStatusException;
import com.techelevator.exception.SportNotFoundException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
public class SportsController {

    private final SportsDao sportsDao;

    public SportsController(SportsDao sportsDao) { this.sportsDao = sportsDao; }



    @GetMapping("/sports/{id}")
    public Sports getSport(@PathVariable("id") int sportId) throws SportNotFoundException {

        Sports result = sportsDao.getSport(sportId);
        if (result == null) {
            throw new SportNotFoundException("No Sport found with that id. ");
        } else {
            return result;
        }
    }


    @GetMapping("/sports")
    public List<Sports> getAllSports() {
        return sportsDao.getAllSports();
    }


    @RequestMapping(path = "/sports", method = RequestMethod.POST)
    public Sports postSport(@RequestBody Sports newSport) {
        return sportsDao.createSport(newSport);
    }



    @RequestMapping(path = "/sports/{id}", method = RequestMethod.PUT)
    public Sports putSport(@PathVariable ("id") int sportId, @RequestBody Sports updatedSport) throws SportNotFoundException {

        if (sportsDao.updateSport(sportId) != null) {
            return updatedSport;
        } else {
            throw new SportNotFoundException("No Sport found with that id. ");
        }
    }


    @DeleteMapping("/sports/{id}")
    public void deleteSport(@PathVariable("id") int sportId) throws SportNotFoundException {
        if (!sportsDao.deleteSport(sportId)) {
            throw new SportNotFoundException("Sport not found to delete. ");
        }

    }

}
