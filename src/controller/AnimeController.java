package controller;

import main.Main;
import main.Menu;
import model.Anime;
import model.Tracker;

import java.util.List;
import java.util.stream.Collectors;

public class AnimeController implements Controller {
    private static int increment = 0; // auto increment id

    @Override
    public void printByStatus(String status) {
        List<Anime> animes = Menu.animeList.stream()
                                        .filter(x -> x.getStatus().equals(status))
                                        .collect(Collectors.toList());

        for(Anime anime : animes){
            System.out.println("Id : " + anime.getId());
            System.out.println("Nama anime : " + anime.getNameSeries());
            System.out.println("Total episode : " + anime.getTotalEpisode());
            System.out.println("================================");
        }
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
    public void update(int id, Tracker tracker){
        Anime anime = (Anime) find(id);
        int index = Menu.animeList.indexOf(anime);

        Anime updateAnime = (Anime) tracker;
        updateAnime.setId(anime.getId());
        updateAnime.setStartDate(anime.getStartDate());
        Menu.animeList.set(index, updateAnime);

        System.out.println("Sukses update data");
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
