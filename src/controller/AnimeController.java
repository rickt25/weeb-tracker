package controller;

import main.Main;
import main.Menu;
import model.Anime;
import model.Manga;
import model.Tracker;

import java.util.List;
import java.util.stream.Collectors;

import static main.Menu.printLineTable;

public class AnimeController implements Controller {
    private static int increment = 0; // auto increment id

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
                System.out.print("=");
            }
            System.out.println("No Data");
        }

        printLineTable();
        System.out.println();
    }

    @Override
    public Tracker find(int id) {
        return Menu.animeList.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void insert(Tracker tracker) {
        tracker.setId(++increment);
        Menu.animeList.add((Anime) tracker);
    }

    @Override
    public void delete(int id) {
        Anime anime = (Anime) find(id);
        if(anime != null) {
            Menu.animeList.remove((Anime) find(id));
            System.out.println("Sukses menghapus anime dari tracker");
        }else{
            System.out.println("Id anime tidak ditemukan");
        }
    }
}
