package com.techelevator.services;

import com.techelevator.model.Holiday;

import java.util.Date;
import java.util.List;

public interface ServerHolidayService {

    Holiday[] getAllHolidaysOnDate(Date date);


}
