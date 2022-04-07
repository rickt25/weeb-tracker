package main;

import controller.AnimeController;
import controller.LightNovelController;
import controller.MangaController;
import model.Anime;
import model.LightNovel;
import model.Manga;

import java.util.ArrayList;

public class Main {
    public static ArrayList<Anime> animeList = new ArrayList<>();
    public static ArrayList<Manga> mangaList = new ArrayList<>();
    public static ArrayList<LightNovel> lightNovelList = new ArrayList<>();
    public static AnimeController animeController = new AnimeController();
    public static MangaController mangaController = new MangaController();
    public static LightNovelController lightNovelController = new LightNovelController();

    public static void main(String[] args) {
        Anime anime = new Anime("Haikyuu", "Watching", 5, "Sports", 1, 24, 5);
        Anime anime2 = new Anime("Dragonball", "Watching", 7, "Action", 1, 120, 10);

        animeController.insert(anime);
        animeController.insert(anime2);

        animeController.update(1, new Anime("Kuroko No Basket", "Watching", 2, "Sports", 1, 25, 10));
        animeController.delete(2);

        animeController.printByStatus("Watching");
    }
}
