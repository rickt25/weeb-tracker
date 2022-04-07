package controller;

import main.Main;
import model.Anime;
import model.LightNovel;
import model.Tracker;

import java.util.List;
import java.util.stream.Collectors;

public class LightNovelController implements Controller {
    @Override
    public void printByStatus(String status) {
        List<LightNovel> lightNovels = Main.lightNovelList.stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList());

        for(LightNovel ln : lightNovels){
            System.out.println("diatas udah filter by status, tinggal print LNnya ngabs");
        }
    }

    @Override
    public Tracker find(int id) {
        return Main.lightNovelList.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void insert(Tracker tracker) {
        Main.lightNovelList.add((LightNovel) tracker);
    }

    @Override
    public void update(int id, Tracker tracker){
        LightNovel lightNovel = (LightNovel) find(id);
        int index = Main.lightNovelList.indexOf(lightNovel);

        LightNovel updateLN = (LightNovel) tracker;
        updateLN.setId(lightNovel.getId());
        updateLN.setStartDate(lightNovel.getStartDate());
        Main.lightNovelList.set(index, updateLN);

        System.out.println("Sukses update data");
    }

    @Override
    public void delete(int id) {
        LightNovel lightNovel = (LightNovel) find(id);
        if(lightNovel != null) {
            Main.lightNovelList.remove((LightNovel) find(id));
            System.out.println("Sukses menghapus light novel dari tracker");
        }else{
            System.out.println("Id light novel tidak ditemukan");
        }
    }
}
