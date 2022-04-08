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
        System.out.println("=========");
        System.out.println("| Manga |");
        System.out.println("=========");
        System.out.println("Name: " + getNameSeries());
        System.out.println("Volume: " + getCurrentVolume());
        System.out.println("Status: " + getStatus());
        System.out.println("Genre: " + getGenre());
        System.out.println("Progress: " + getCurrentChapter());
        System.out.println("Started reading on: " + getStartDate());
        System.out.println();
    }

}
