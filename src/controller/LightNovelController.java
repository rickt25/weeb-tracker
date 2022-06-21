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

public class LightNovelController implements Controller {
    TrackerFacade trackerFacade = TrackerFacade.getInstance();

    @Override
    public boolean checkTracker(int userId) {
        return trackerFacade.lightNovel.getLightNovelList(userId).size() > 0;
    }

    @Override
    public Tracker find(int id, String status, int userId) {
        return trackerFacade.lightNovel.getLightNovelList(userId).stream()
                .filter(x -> x.getId() == id && (x.getStatus().equals(status) || status.isEmpty()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void insert(Tracker tracker) {
        trackerFacade.lightNovel.insertLightNovel((LightNovel) tracker);
    }


    @Override
    public void delete(Tracker tracker) {
        LightNovel lightNovel = (LightNovel) tracker;
        if(lightNovel != null) {
            trackerFacade.lightNovel.deleteLightNovel(lightNovel.getId());
            System.out.println("Delete Succeeded from Light Novel Tracker");
        }else{
            System.out.println("Id not found");
        }
        System.out.println();
    }

    @Override
    public void printAll(int userId) {
        ArrayList<LightNovel> lightNovelList = trackerFacade.lightNovel.getLightNovelList(userId);
        printLineTable();
        System.out.println("| Id | Light Novel's Name                  | Volume | Genre                                    | Progress  | Status      |");
        printLineTable();
        if(lightNovelList.size() > 0){
            for (LightNovel lnView : lightNovelList){
                System.out.printf("| %-2d | %-35s | %-6s | %-40s | PG %-6d | %-11s |\n", lnView.getId(), lnView.getNameSeries(), lnView.getCurrentVolume(), lnView.getGenre(), lnView.getCurrentPage(), lnView.getStatus());
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
        List<LightNovel> lightNovels = trackerFacade.lightNovel.getLightNovelList(userId).stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList());

        printLineTable();
        System.out.println("| Id | Light Novel's Name                  | Volume | Genre                                    | Progress  | Status      |");
        printLineTable();
        if(lightNovels.size() > 0){
            for(LightNovel ln : lightNovels){
                System.out.printf("| %-2d | %-35s | %-6s | %-40s | CH %-6d | %-11s |\n", ln.getId(), ln.getNameSeries(), ln.getCurrentVolume(), ln.getGenre(), ln.getCurrentPage(), ln.getStatus());
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
        trackerFacade.lightNovel.updateLightNovel((LightNovel) tracker);
    }
}
