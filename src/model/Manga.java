package model;

import java.util.ArrayList;
import java.util.Date;

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
    public void printProgress() {

    }
}
