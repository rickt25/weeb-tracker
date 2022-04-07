package controller;

import main.Main;
import model.LightNovel;
import model.Manga;
import model.Tracker;

import java.util.List;
import java.util.stream.Collectors;

public class MangaController implements Controller {
    @Override
    public void printByStatus(String status) {
        List<Manga> mangas = Main.mangaList.stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList());

        for(Manga manga : mangas){
            System.out.println("diatas udah filter by status, tinggal print manganya ngabs");
        }
    }

    @Override
    public Tracker find(int id) {
        return Main.mangaList.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void insert(Tracker tracker) {
        Main.mangaList.add((Manga) tracker);
    }

    @Override
    public void update(int id, Tracker tracker){
        Manga manga = (Manga) find(id);
        int index = Main.mangaList.indexOf(manga);

        Manga updateLN = (Manga) tracker;
        updateLN.setId(manga.getId());
        updateLN.setStartDate(manga.getStartDate());
        Main.mangaList.set(index, manga);

        System.out.println("Sukses update data");
    }

    @Override
    public void delete(int id) {
        Manga manga = (Manga) find(id);
        if(manga != null) {
            Main.mangaList.remove((Manga) find(id));
            System.out.println("Sukses menghapus manga dari tracker");
        }else{
            System.out.println("Id manga tidak ditemukan");
        }
    }
}
