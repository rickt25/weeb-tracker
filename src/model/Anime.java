package model;

import main.Menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static main.Menu.printLineTable;

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
        System.out.println("=========");
        System.out.println("| Anime |");
        System.out.println("=========");
        System.out.println("Name: " + getNameSeries());
        System.out.println("Seasons: " + getSeason());
        System.out.println("Episodes: " + (getTotalEpisode() == -1 ? "N/A" : getTotalEpisode()));
        System.out.println("Status: " + getStatus());
        System.out.println("Genre: " + getGenre());
        System.out.println("Progress: " + getCurrEpisode() + "/" + (getTotalEpisode() > 0 ?  String.valueOf(getTotalEpisode()) : '?'));
        System.out.println("Started watching on: " + getStartDate());
        System.out.println();
    }

}
