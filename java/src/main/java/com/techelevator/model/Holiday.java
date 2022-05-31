package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonPropertyOrder({
        "name",
        "date",
        "observed",
        "public"
})

@JsonIgnoreProperties(value = {"country", "uuid", "weekday"})
public class Holiday {

    private String name;

    private String date;

    private String observed;

    private boolean isPublic;

    public Holiday(){};

    public Holiday(String name, String date, String observed, boolean isPublic) {
        this.name = name;
        this.date = date;
        this.observed = observed;
        this.isPublic = isPublic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getObserved() {
        return observed;
    }

    public void setObserved(String observed) {
        this.observed = observed;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
