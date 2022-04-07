package model;

import java.util.ArrayList;
import java.util.Date;

public abstract class Tracker {
    protected int id;
    protected String nameSeries;
    protected String status;
    protected Date startDate;
    protected int rating;
    protected String genre;

    public Tracker(String nameSeries, String status, int rating, String genre) {
        this.nameSeries = nameSeries;
        this.status = status;
        this.startDate = new Date();
        this.rating = rating;
        this.genre = genre;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getNameSeries() {
        return nameSeries;
    }

    public void setNameSeries(String nameSeries) {
        this.nameSeries = nameSeries;
    }

    public String getStatus() {
        return status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public abstract void printProgress();

}
