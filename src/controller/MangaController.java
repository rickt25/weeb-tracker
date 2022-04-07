package controller;

import main.Main;
import main.Menu;
import model.LightNovel;
import model.Manga;
import model.Tracker;

import java.util.List;
import java.util.stream.Collectors;

public class MangaController implements Controller {
    private static int increment = 0;

    @Override
    public void printByStatus(String status) {
        List<Manga> mangas = Menu.mangaList.stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList());

        for(Manga manga : mangas){
            System.out.println("diatas udah filter by status, tinggal print manganya ngabs");
        }
    }

    @Override
    public Tracker find(int id) {
        return Menu.mangaList.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void insert(Tracker tracker) {
        tracker.setId(++increment);
        Menu.mangaList.add((Manga) tracker);
    }

    @Override
    public void update(int id, Tracker tracker){
        Manga manga = (Manga) find(id);
        int index = Menu.mangaList.indexOf(manga);

        Manga updateLN = (Manga) tracker;
        updateLN.setId(manga.getId());
        updateLN.setStartDate(manga.getStartDate());
        Menu.mangaList.set(index, manga);

        System.out.println("Sukses update data");
    }

    @Override
    public void delete(int id) {
        Manga manga = (Manga) find(id);
        if(manga != null) {
            Menu.mangaList.remove((Manga) find(id));
            System.out.println("Sukses menghapus manga dari tracker");
        }else{
            System.out.println("Id manga tidak ditemukan");
        }
    }
}
