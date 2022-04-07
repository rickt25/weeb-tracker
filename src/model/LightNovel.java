package model;

import java.util.ArrayList;
import java.util.Date;

public class LightNovel extends Tracker {
    private int currentVolume, currentPage;

    public LightNovel(String nameSeries, String status, int rating, String genre, int currentVolume, int currentPage) {
        super(nameSeries, status, rating, genre);
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

    }
}
