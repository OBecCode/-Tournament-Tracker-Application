package com.techelevator.controller;

import com.techelevator.model.Holiday;
import com.techelevator.services.ServerHolidayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin
public class HolidayController {

    @Value("${api.key}")
    private String apiKey;

    private final ServerHolidayService serverHolidayService;

    public HolidayController(ServerHolidayService serverHolidayService){

        this.serverHolidayService = serverHolidayService;
    }

    @RequestMapping(path = "test", method = RequestMethod.GET)
    public Holiday[] getAllHolidays(Date date){
       return serverHolidayService.getAllHolidaysOnDate(date);
    }



}
