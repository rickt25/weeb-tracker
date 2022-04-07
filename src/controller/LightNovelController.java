package controller;

import main.Main;
import main.Menu;
import model.Anime;
import model.LightNovel;
import model.Manga;
import model.Tracker;

import java.util.List;
import java.util.stream.Collectors;

import static main.Menu.printLineTable;

public class LightNovelController implements Controller {
    private static int increment = 0;

    @Override
    public void printByStatus(String status) {
        List<LightNovel> lightNovels = Menu.lightNovelList.stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList());

        printLineTable();
        System.out.println("| Id | Light Novel's Name                        | Volume | Genre                                    | Progress  | Status      |");
        printLineTable();
        if(lightNovels.size() > 0){
            for(LightNovel ln : lightNovels){
                System.out.printf("| %-2d | %-35s | %-6s | %-40s | CH %-6d | %-11s |\n", ln.getId(), ln.getNameSeries(), ln.getCurrentVolume(), ln.getGenre(), ln.getCurrentPage(), ln.getStatus());
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
        return Menu.lightNovelList.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void insert(Tracker tracker) {
        tracker.setId(++increment);
        Menu.lightNovelList.add((LightNovel) tracker);
    }


    @Override
    public void delete(int id) {
        LightNovel lightNovel = (LightNovel) find(id);
        if(lightNovel != null) {
            Menu.lightNovelList.remove((LightNovel) find(id));
            System.out.println("Sukses menghapus light novel dari tracker");
        }else{
            System.out.println("Id light novel tidak ditemukan");
        }
    }
}
