package com.techelevator.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({
        "status",
        "error",
        "warning",
        "requests",
        "holidays"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestHoliday {

    public Integer status;

    private Holiday[] holidays;

    private String error;

    private String warning;


    public TestHoliday(){};

    public TestHoliday(Integer status, Holiday[] holidayList, String error, String warning) {
        this.status = status;
        this.holidays = holidayList;
        this.error = error;
        this.warning = warning;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Holiday[] getHolidays() {
        return holidays;
    }

    public void setHolidays(Holiday[] holidayList) {
        this.holidays = holidayList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
