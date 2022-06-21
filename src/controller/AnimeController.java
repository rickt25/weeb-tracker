package controller;

import facades.TrackerFacade;
import main.Menu;
import model.Anime;
import model.Tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static main.Menu.printLineTable;

public class AnimeController implements Controller {
    TrackerFacade trackerFacade = TrackerFacade.getInstance();

    @Override
    public boolean checkTracker() {
        return trackerFacade.anime.getAnimeList().size() > 0;
    }

    @Override
    public Tracker find(int id, String status) {
        ArrayList<Anime> animeList = trackerFacade.anime.getAnimeList();
        return animeList.stream()
                .filter(x -> x.getId() == id && (x.getStatus().equals(status) || status.isEmpty()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void insert(Tracker tracker) {
        trackerFacade.anime.insertAnime((Anime) tracker);
    }


    @Override
    public void delete(Tracker tracker) {
        Anime anime = (Anime) tracker;
        if(anime != null) {
            trackerFacade.anime.deleteAnime(anime.getId());
            System.out.println("Delete Succeeded from Anime Tracker");
        }else{
            System.out.println("Id not found");
        }
        System.out.println();
    }

    @Override
    public void printAll() {
        ArrayList<Anime> animeList = trackerFacade.anime.getAnimeList();
        printLineTable();
        System.out.println("| Id | Anime's Name                        | Season | Genre                                    | Progress  | Status      |");
        printLineTable();
        if(animeList.size() > 0){
            for (Anime animeView : animeList){
                System.out.printf("| %-2d | %-35s | %-6s | %-40s | %4d/%-4s | %-11s |\n", animeView.getId(), animeView.getNameSeries(), animeView.getSeason(), animeView.getGenre(), animeView.getCurrEpisode(), animeView.getTotalEpisode() > 0 ?  String.valueOf(animeView.getTotalEpisode()) : '?', animeView.getStatus());
            }
        }else{
            System.out.println("No Data");
        }
        printLineTable();
        System.out.println();
    }

    @Override
    public void printByStatus(String status) {
        List<Anime> animeList = trackerFacade.anime.getAnimeList().stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList());
        printLineTable();
        System.out.println("| Id | Anime's Name                        | Season | Genre                                    | Progress  | Status      |");
        printLineTable();
        if(animeList.size() > 0){
            for(Anime anime : animeList){
                System.out.printf("| %-2d | %-35s | %-6s | %-40s | %4d/%-4s | %-11s |\n", anime.getId(), anime.getNameSeries(), anime.getSeason(), anime.getGenre(), anime.getCurrEpisode(), anime.getTotalEpisode() > 0 ?  String.valueOf(anime.getTotalEpisode()) : '?', anime.getStatus());
            }
        }else{
            for(int i = 0; i < 54; i++){
                System.out.print(" ");
            }
            System.out.println("No Data");
        }

        printLineTable();
        System.out.println();
    }
}
