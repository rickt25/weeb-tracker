package controller;

import main.Menu;
import model.Anime;
import model.Tracker;

import java.util.List;
import java.util.stream.Collectors;

import static main.Menu.printLineTable;

public class AnimeController implements Controller {
    private static int increment = 0; // auto increment id

    @Override
    public Tracker find(int id, String status) {
        return Menu.animeList.stream()
                .filter(x -> x.getId() == id && (x.getStatus().equals(status) || status.isEmpty()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void insert(Tracker tracker) {
        tracker.setId(++increment);
        Menu.animeList.add((Anime) tracker);
    }


    @Override
    public void delete(Tracker tracker) {
        Anime anime = (Anime) tracker;
        if(anime != null) {
            Menu.animeList.remove(anime);
            System.out.println("Delete Succeeded from Anime Tracker");
        }else{
            System.out.println("Id not found");
        }
        System.out.println();
    }

    @Override
    public void printAll() {
        printLineTable();
        System.out.println("| Id | Anime's Name                        | Season | Genre                                    | Progress  | Status      |");
        printLineTable();
        if(Menu.animeList.size() > 0){
            for (Anime animeView : Menu.animeList){
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
        List<Anime> animes = Menu.animeList.stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList());
        printLineTable();
        System.out.println("| Id | Anime's Name                        | Season | Genre                                    | Progress  | Status      |");
        printLineTable();
        if(animes.size() > 0){
            for(Anime anime : animes){
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
