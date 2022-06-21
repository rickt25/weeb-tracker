package controller;

import facades.TrackerFacade;
import main.Main;
import main.Menu;
import model.Anime;
import model.LightNovel;
import model.Manga;
import model.Tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static main.Menu.printLineTable;

public class MangaController implements Controller {
    TrackerFacade trackerFacade = TrackerFacade.getInstance();

    @Override
    public boolean checkTracker(int userId) {
        return trackerFacade.manga.getMangaList(userId).size() > 0;
    }

    @Override
    public Tracker find(int id, String status, int userId) {
        ArrayList<Manga> mangaList = trackerFacade.manga.getMangaList(userId);
        return mangaList.stream()
                .filter(x -> x.getId() == id && (x.getStatus().equals(status) || status.isEmpty()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void insert(Tracker tracker) {
        trackerFacade.manga.insertManga((Manga) tracker);
    }

    @Override
    public void delete(Tracker tracker) {
        Manga manga = (Manga) tracker;
        if(manga != null) {
            trackerFacade.manga.deleteManga(manga.getId());
            System.out.println("Delete Succeeded from Manga Tracker");
        }else{
            System.out.println("Id not found");
        }
        System.out.println();
    }

    @Override
    public void printAll(int userId) {
        ArrayList<Manga> mangaList = trackerFacade.manga.getMangaList(userId);
        printLineTable();
        System.out.println("| Id | Manga's Name                        | Volume | Genre                                    | Progress  | Status      |");
        printLineTable();
        if(mangaList.size() > 0) {
            for (Manga mangaView : mangaList) {
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
    public void printByStatus(String status, int userId) {
        List<Manga> mangas = trackerFacade.manga.getMangaList(userId).stream()
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

    @Override
    public void update(Tracker tracker) {
        trackerFacade.manga.updateManga((Manga) tracker);
    }
}
