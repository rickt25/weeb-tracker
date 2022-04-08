package controller;

import main.Main;
import main.Menu;
import model.LightNovel;
import model.Manga;
import model.Tracker;

import java.util.List;
import java.util.stream.Collectors;

import static main.Menu.printLineTable;

public class MangaController implements Controller {
    private static int increment = 0;

    @Override
    public Tracker find(int id, String status) {
        return Menu.mangaList.stream()
                .filter(x -> x.getId() == id && (x.getStatus().equals(status) || status.isEmpty()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void insert(Tracker tracker) {
        tracker.setId(++increment);
        Menu.mangaList.add((Manga) tracker);
    }

    @Override
    public void delete(Tracker tracker) {
        Manga manga = (Manga) tracker;
        if(manga != null) {
            Menu.mangaList.remove(tracker);
            System.out.println("Delete Succeeded from Manga Tracker");
        }else{
            System.out.println("Id not found");
        }
        System.out.println();
    }

    @Override
    public void printAll() {
        printLineTable();
        System.out.println("| Id | Manga's Name                        | Volume | Genre                                    | Progress  | Status      |");
        printLineTable();
        if(Menu.mangaList.size() > 0) {
            for (Manga mangaView : Menu.mangaList) {
                System.out.printf("| %-2d | %-35s | %-6s | %-40s | CH %-6d | %-11s |\n", mangaView.getId(), mangaView.getNameSeries(), mangaView.getCurrentVolume(), mangaView.getGenre(), mangaView.getCurrentChapter(), mangaView.getStatus());
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

    @Override
    public void printByStatus(String status) {
        List<Manga> mangas = Menu.mangaList.stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList());
        printLineTable();
        System.out.println("| Id | Manga's Name                        | Volume | Genre                                    | Progress  | Status      |");
        printLineTable();
        if(mangas.size() > 0){
            for(Manga manga : mangas){
                System.out.printf("| %-2d | %-35s | %-6s | %-40s | CH %-6d | %-11s |\n", manga.getId(), manga.getNameSeries(), manga.getCurrentVolume(), manga.getGenre(), manga.getCurrentChapter(), manga.getStatus());
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
