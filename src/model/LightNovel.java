package model;

import main.Menu;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static main.Menu.printLineTable;

public class LightNovel extends Tracker {
    private int currentVolume, currentPage;

    public LightNovel(int id, int userId, String nameSeries, String status, Date startDate, int rating, String genre, int currentVolume, int currentPage) {
        super(id, userId, nameSeries, status, startDate, rating, genre);
        this.currentVolume = currentVolume;
        this.currentPage = currentPage;
    }

    public LightNovel(int userId, String nameSeries, String status, int rating, String genre, int currentVolume, int currentPage) {
        super(userId, nameSeries, status, rating, genre);
        this.currentVolume = currentVolume;
        this.currentPage = currentPage;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(int currentVolume) {
        this.currentVolume = currentVolume;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public void printDetail() {
    	System.out.println("================================================================");
    	System.out.printf("| Name    : %-50s |\n", getNameSeries());
        System.out.println("================================================================");
        System.out.printf("| Volume  : %-50s |\n", getCurrentVolume());
        System.out.printf("| Status  : %-50s |\n", getStatus());
        System.out.printf("| Genre   : %-50s |\n", getGenre());
        System.out.printf("| Progress: %-50s |\n", getCurrentPage());
        System.out.printf("| Started : %-50s |\n", getStartDate());
        System.out.println("================================================================");
    }

}
