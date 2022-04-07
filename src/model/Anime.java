package model;

import java.util.ArrayList;
import java.util.Date;

public class Anime extends Tracker {
    private int season;
    private int totalEpisode;
    private int currEpisode;

    public Anime(String nameSeries, String status, int rating, String genre, int seasons, int totalEpisode, int currEpisode) {
        super(nameSeries, status, rating, genre);
        this.season = seasons;
        this.totalEpisode = totalEpisode;
        this.currEpisode = currEpisode;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int seasons) {
        this.season = seasons;
    }

    public int getTotalEpisode() {
        return totalEpisode;
    }

    public void setTotalEpisode(int totalEpisode) {
        this.totalEpisode = totalEpisode;
    }

    public int getCurrEpisode() {
        return currEpisode;
    }

    public void setCurrEpisode(int currEpisode) {
        this.currEpisode = currEpisode;
    }

    @Override
    public void printDetail() {

    }
}
