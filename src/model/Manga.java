package model;

import main.Menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static main.Menu.printLineTable;

public class Manga extends Tracker {
    private int currentVolume, currentChapter;

    public Manga(String nameSeries, String status, int rating, String genre, int currentVolume, int currentChapter) {
        super(nameSeries, status, rating, genre);
        this.currentVolume = currentVolume;
        this.currentChapter = currentChapter;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(int currentVolume) {
        this.currentVolume = currentVolume;
    }

    public int getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(int currentChapter) {
        this.currentChapter = currentChapter;
    }

    @Override
    public void printDetail() {
    	System.out.println("================================================================");
    	System.out.printf("| Name    : %-50s |\n", getNameSeries());
        System.out.println("================================================================");
        System.out.printf("| Volume  : %-50s |\n", getCurrentVolume());
        System.out.printf("| Status  : %-50s |\n", getStatus());
        System.out.printf("| Genre   : %-50s |\n", getGenre());
        System.out.printf("| Progress: %-50s |\n", getCurrentChapter());
        System.out.printf("| Started : %-50s |\n", getStartDate());
        System.out.println("================================================================");
    }

}
