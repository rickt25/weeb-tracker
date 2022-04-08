package model;

import main.Menu;

import java.util.List;
import java.util.stream.Collectors;

import static main.Menu.printLineTable;

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
        System.out.println("===============");
        System.out.println("| Light Novel |");
        System.out.println("===============");
        System.out.println("Name: " + getNameSeries());
        System.out.println("Volume: " + getCurrentVolume());
        System.out.println("Status: " + getStatus());
        System.out.println("Genre: " + getGenre());
        System.out.println("Progress: " + getCurrentPage());
        System.out.println("Started reading on: " + getStartDate());
        System.out.println();
    }

}
