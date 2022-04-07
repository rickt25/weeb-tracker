package main;

import controller.AnimeController;
import controller.LightNovelController;
import controller.MangaController;
import model.Anime;
import model.LightNovel;
import model.Manga;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.operations();
//        Anime anime = new Anime("Haikyuu", "Watching", 5, "Sports", 1, 24, 5);
//        Anime anime2 = new Anime("Dragonball", "Watching", 7, "Action", 1, 120, 10);
//
//        animeController.insert(anime);
//        animeController.insert(anime2);
//
//        animeController.update(1, new Anime("Kuroko No Basket", "Watching", 2, "Sports", 1, 25, 10));
//        animeController.delete(2);
//
//        animeController.printByStatus("Watching");
    }


}
