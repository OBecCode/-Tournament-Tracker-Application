package com.techelevator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Location {
    public Location() {
    }

    ;
    @Min(value = 1, message = "Location ID Should Be a Positive Number.")
    private int locationId;
    @NotBlank(message = "State Name is Required.")
    private String stateName;
    @NotBlank(message = "City Name is Required.")
    private String cityName;

    public Location(int locationId, String stateName, String cityName) {
        this.locationId = locationId;
        this.stateName = stateName;
        this.cityName = cityName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
