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
    }


}
