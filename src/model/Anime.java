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
    private int currentEpisode;

    public Anime(String nameSeries, String status, int rating, String genre, int seasons, int totalEpisode, int currentEpisode) {
        super(nameSeries, status, rating, genre);
        this.season = seasons;
        this.totalEpisode = totalEpisode;
        this.currentEpisode = currentEpisode;
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
        return currentEpisode;
    }

    public void setCurrEpisode(int currEpisode) {
        this.currentEpisode = currEpisode;
    }



    @Override
    public void printDetail() {
    	System.out.println("================================================================");
    	System.out.printf("| Name    : %-50s |\n", getNameSeries());
        System.out.println("================================================================");
        System.out.printf("| Seasons : %-50s |\n", getSeason());
        System.out.printf("| Episodes: %-50s |\n", (getTotalEpisode() == -1 ? "N/A" : getTotalEpisode()));
        System.out.printf("| Status  : %-50s |\n", getStatus());
        System.out.printf("| Genre   : %-50s |\n", getGenre());
        System.out.printf("| Progress: %-50s |\n", getCurrEpisode() + "/" + (getTotalEpisode() > 0 ?  String.valueOf(getTotalEpisode()) : '?'));
        System.out.printf("| Started : %-50s |\n", getStartDate());
        System.out.println("================================================================");
    }

}
