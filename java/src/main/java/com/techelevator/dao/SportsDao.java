package com.techelevator.dao;

import com.techelevator.model.Sports;

import java.util.List;

public interface SportsDao {


    Sports getSport(int sportId);
    List<Sports> getAllSports();
    Sports createSport(Sports sport);
    Sports updateSport(int sportId);
    boolean deleteSport(int sportId);


}
