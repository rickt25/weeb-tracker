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

    // 2 Design Pattern yg digunakan adalah Singleton dan Facade
    // Singleton ada pada :
    // database > Connect.java
    // facades > TrackerFacade.java

    // Alasan Singleton adalah karena kita hanya perlu menginstansiasi classnya sekali saja
    // dan menggunakan fungsi-fungsinya saja

    // Facade ada pada TrackerFacade yaitu sebuah facade yg berisi service-service untuk
    // interaksi database untuk ketiga data yaitu Anime, Manga, & LightNovel

    // Kemudian contoh penggunaan Facade ada pada package Controller

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.operations();
    }

}
