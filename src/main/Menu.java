package main;

import controller.AnimeController;
import controller.LightNovelController;
import controller.MangaController;
import model.Anime;
import model.LightNovel;
import model.Manga;
import model.Tracker;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static ArrayList<Anime> animeList = new ArrayList<>();
    public static ArrayList<Manga> mangaList = new ArrayList<>();
    public static ArrayList<LightNovel> lightNovelList = new ArrayList<>();
    public static AnimeController animeController = new AnimeController();
    public static MangaController mangaController = new MangaController();
    public static LightNovelController lightNovelController = new LightNovelController();
    private String[] menuStatus = {"Planning", "On Progress", "Finished", ""};
    private Scanner sc = new Scanner(System.in);

    public void operations(){
        int choice = 0;
        boolean loopMenu = true;
        do{
            homeMenu();
            try{
                choice = sc.nextInt();
            }catch (Exception e){
                System.out.println("Input must be a number!");
                continue;
            }finally {
                sc.nextLine();
            }
            switch(choice){
                case 1:
                    addTracker();
                    break;
                case 2:
                    viewTracker();
                    break;
                case 3:
                    loopMenu = false;
                    continue;
                default:
                    break;
            }
        }while(loopMenu);
    }

    private void addTracker(){
        int choice = 0;

        do{
            trackerType();
            try{
                choice = sc.nextInt();
            }catch (Exception e){
                System.out.println("Input must be a number!");
                continue;
            }finally {
                sc.nextLine();
            }
            switch(choice){
                case 1:
                    insertAnimeTracker();
                    break;
                case 2:
                    insertMangaTracker();
                    break;
                case 3:
                    insertLNTracker();
                    break;
                case 4:
                    break;
                default:
                    continue;
            }
            break;
        }while(true);
    }

    private void insertAnimeTracker(){
        Anime anime;
        String name, status, genre;
        int rating, seasons, totalEpisode, currEpisode;
        name = inputString("Input Anime's Name [Max: 35 Character]", 35);
        seasons = inputNumber(-1, "Input Anime's Seasons (Default 1)", 0);
        genre = inputString("Input Anime's genre (Separate each genre with a comma) [Max: 40 Character]", 40);
        totalEpisode = inputNumber(-1, "Input Anime's Total Episode (If you know input the total, otherwise input -1)", 0);
        currEpisode = inputNumber(1, "Input Current Episode", (totalEpisode == -1) ? 0 : totalEpisode);
        rating = inputNumber(1, "Input Anime's Rating [1-5]", 5);
        status = menuStatus[statusSelect() - 1];
        anime = new Anime(name, status, rating, genre, seasons, totalEpisode, currEpisode);
        animeController.insert(anime);
    }

    private void insertMangaTracker(){
        Manga manga;
        String name, status, genre;
        int rating, currentVolume, currentChap;
        name = inputString("Input Manga's name [Max: 35 Character]", 35);
        currentVolume = inputNumber(1, "Input Manga's current volume (Default 1)", 0);
        genre = inputString("Input Manga's genre (Separate each genre with a comma) [Max: 40 Character]", 40);
        currentChap = inputNumber(1, "Input Manga's current chapter", 0);
        rating = inputNumber(1, "Input Manga's rating [1-5]", 5);
        status = menuStatus[statusSelect() - 1];
        manga = new Manga(name, status, rating, genre, currentVolume, currentChap);
        mangaController.insert(manga);
    }

    private void insertLNTracker(){
        LightNovel Ln;
        String name, status, genre;
        int rating, currentVolume, currentPage;
        name = inputString("Input Light Novel's name [Max: 35 Character]", 35);
        currentVolume = inputNumber(1, "Input Light Novel's total volume (Default 1)", 0);
        genre = inputString("Input Light Novel's genre (Separate each genre with a comma) [Max: 40 Character]", 40);
        currentPage = inputNumber(1, "Input Light Novel's current page", 0);
        rating = inputNumber(1, "Input Light Novel's rating [1-5]", 5);
        status = menuStatus[statusSelect() - 1];
        Ln = new LightNovel(name, status, rating, genre, currentVolume, currentPage);
        lightNovelController.insert(Ln);
    }

    private void viewTracker(){
        int choice = 0;
        boolean loopMenu = true;
        do{
            trackerType();
            try{
                choice = sc.nextInt();
            }catch (Exception e){
                System.out.println("Input must be a number!");
                continue;
            }finally {
                sc.nextLine();
            }
            switch(choice){
                case 1:
                    viewAnimeTracker();
                    break;
                case 2:
                    viewMangaTracker();
                    break;
                case 3:
                    insertLNTracker();
                    break;
                case 4:
                    loopMenu = false;
                    break;
                default:
                    break;
            }
        }while(loopMenu);
    }

    private void viewAnimeTracker(){
        int choice;
        boolean loop = true;
        String filter = "";
        Anime anime = null;
        if(animeList.size() > 0){
            do{
                if(filter.isEmpty()){
                    tableAnimeView();
                }else{
                    animeController.printByStatus(filter);
                }
                menuView("Anime");
                try{
                    choice = sc.nextInt();
                }catch (Exception e){
                    System.out.println("Input must be a number!");
                    continue;
                }finally {
                    sc.nextLine();
                }
                System.out.println();
                if(choice == 1){
                    int selectedId = 0;
                    do{
                        tableAnimeView();
                        selectedId = inputNumber(1, "Input Id to see the detail", 0);
                        anime = (Anime)animeController.find(selectedId);
                    }while(anime == null);
                    System.out.println("=========");
                    System.out.println("| Anime |");
                    System.out.println("=========");
                    System.out.println();
                    System.out.println("Name: " + anime.getNameSeries());
                    System.out.println("Seasons: " + anime.getSeason());
                    System.out.println("Episodes: " + (anime.getTotalEpisode() == -1 ? "N/A" : anime.getTotalEpisode()));
                    System.out.println("Status: " + anime.getStatus());
                    System.out.println("Genre: " + anime.getGenre());
                    System.out.println("Progress: " + anime.getCurrEpisode() + "/" + (anime.getTotalEpisode() > 0 ?  String.valueOf(anime.getTotalEpisode()) : '?'));
                    System.out.println("Started watching on: " + anime.getStartDate());
                    System.out.println();
                    int choiceUpdate;
                    do{
                        System.out.println("1. Update Status");
                        System.out.println("2. Update Progress");
                        System.out.println("3. Edit Details");
                        System.out.println("4. Back");
                        System.out.print(">> ");
                        try{
                            choiceUpdate = sc.nextInt();
                        }catch (Exception e){
                            System.out.println("Input must be a number!");
                            continue;
                        }finally {
                            sc.nextLine();
                        }

                        if(choiceUpdate == 1){
                            anime.setStatus(menuStatus[statusSelect() - 1]);
                        }else if(choiceUpdate == 2){
                            int updateProgress = 0;
                            do{
                                try{
                                    updateProgress = sc.nextInt();
                                }catch (Exception e){
                                    System.out.println("Input must be a number!");
                                    continue;
                                }finally {
                                    sc.nextLine();
                                }
                            }while((updateProgress >= 1 && updateProgress <= (anime.getTotalEpisode() == -1 ? Integer.MAX_VALUE : anime.getTotalEpisode())));
                            anime.setCurrEpisode(updateProgress);
                        }else if(choiceUpdate == 3){
                            String status, genre;
                            int rating, seasons, totalEpisode, currEpisode;
                            genre = inputString("Update Anime's genre (Separate each genre with a comma) [Max: 40 Character]", 40);
                            totalEpisode = inputNumber(-1, "Update Anime's total episode (If you know input the total, otherwise input -1)", 0);
                            currEpisode = inputNumber(1, "Update Current episode", (totalEpisode == -1) ? 0 : totalEpisode);
                            rating = inputNumber(1, "Update Anime's rating [1-5]", 5);
                            status = menuStatus[statusSelect() - 1];
                            anime.setGenre(genre);
                            anime.setTotalEpisode(totalEpisode);
                            anime.setRating(rating);
                            anime.setCurrEpisode(currEpisode);
                            anime.setStatus(status);
                            System.out.println("Update Success...");
                        }else if(choiceUpdate == 4){
                            break;
                        }else{
                            continue;
                        }
                        break;
                    }while(true);
                }else if(choice == 2){
                    int selectStatus;
                    do{
                        if(filter.isEmpty()){
                            tableAnimeView();
                        }else{
                            animeController.printByStatus(filter);
                        }
                        System.out.println("=============");
                        System.out.println("| Filter by |");
                        System.out.println("=============");
                        for(int i = 0; i < 3; i++){
                            System.out.println((i + 1) + ". " + menuStatus[i]);
                        }
                        System.out.println("4. View All");
                        System.out.print(">> ");
                        selectStatus = inputNumber(1, "", 0);
                    }while(!(selectStatus >= 1 && selectStatus <= 4));
                    filter = menuStatus[selectStatus - 1];
                }else if(choice == 3){
                    break;
                }
            }while(true);
        }else{
            System.out.println("You haven't been watching anime lately...");
        }
    }
    private void viewMangaTracker(){
        int choice;
        boolean loop = true;
        String filter = "";
        Manga manga = null;
        if(mangaList.size() > 0){
            do{
                if(filter.isEmpty()){
                    tableMangaView();
                }else{
                    mangaController.printByStatus(filter);
                }
                menuView("Manga");
                try{
                    choice = sc.nextInt();
                }catch (Exception e){
                    System.out.println("Input must be a number!");
                    continue;
                }finally {
                    sc.nextLine();
                }
                System.out.println();
                if(choice == 1){
                    int selectedId = 0;
                    do{
                        tableMangaView();
                        selectedId = inputNumber(1, "Input Id to see the detail", 0);
                        manga = (Manga) mangaController.find(selectedId);
                    }while(manga == null);
                    System.out.println("=========");
                    System.out.println("| Manga |");
                    System.out.println("=========");
                    System.out.println();
                    System.out.println("Name: " + manga.getNameSeries());
                    System.out.println("Volume: " + manga.getCurrentVolume());
                    System.out.println("Status: " + manga.getStatus());
                    System.out.println("Genre: " + manga.getGenre());
                    System.out.println("Progress: " + manga.getCurrentChapter());
                    System.out.println("Started reading on: " + manga.getStartDate());
                    System.out.println();
                    int choiceUpdate;
                    do{
                        System.out.println("1. Update Status");
                        System.out.println("2. Update Progress");
                        System.out.println("3. Edit Details");
                        System.out.println("4. Back");
                        System.out.print(">> ");
                        try{
                            choiceUpdate = sc.nextInt();
                        }catch (Exception e){
                            System.out.println("Input must be a number!");
                            continue;
                        }finally {
                            sc.nextLine();
                        }

                        if(choiceUpdate == 1){
                            manga.setStatus(menuStatus[statusSelect() - 1]);
                        }else if(choiceUpdate == 2){
                            int updateProgress = 0;
                            do{
                                try{
                                    updateProgress = sc.nextInt();
                                }catch (Exception e){
                                    System.out.println("Input must be a number!");
                                    continue;
                                }finally {
                                    sc.nextLine();
                                }
                            }while(!(updateProgress >= 1));
                            manga.setCurrentChapter(updateProgress);
                        }else if(choiceUpdate == 3){
                            String status, genre;
                            int rating, volume, currChapter;
                            genre = inputString("Update Manga's genre (Separate each genre with a comma) [Max: 40 Character]", 40);
                            volume = inputNumber(-1, "Update Manga's volume", 0);
                            currChapter = inputNumber(1, "Update Current chapter", 0);
                            rating = inputNumber(1, "Update Manga's rating [1-5]", 5);
                            status = menuStatus[statusSelect() - 1];
                            manga.setGenre(genre);
                            manga.setCurrentVolume(volume);
                            manga.setRating(rating);
                            manga.setCurrentChapter(currChapter);
                            manga.setStatus(status);
                            System.out.println("Update Success...");
                        }else if(choiceUpdate == 4){
                            break;
                        }else{
                            continue;
                        }
                        break;
                    }while(true);
                }else if(choice == 2){
                    int selectStatus;
                    do{
                        if(filter.isEmpty()){
                            tableMangaView();
                        }else{
                            mangaController.printByStatus(filter);
                        }
                        System.out.println("=============");
                        System.out.println("| Filter by |");
                        System.out.println("=============");
                        for(int i = 0; i < 3; i++){
                            System.out.println((i + 1) + ". " + menuStatus[i]);
                        }
                        System.out.println("4. View All");
                        System.out.print(">> ");
                        selectStatus = inputNumber(1, "", 0);
                    }while(!(selectStatus >= 1 && selectStatus <= 4));
                    filter = menuStatus[selectStatus - 1];
                }else if(choice == 3){
                    break;
                }
            }while(true);
        }else{
            System.out.println("You haven't been reading manga lately...");
        }
    }
    private void viewLNTracker(){
        int choice;
        boolean loop = true;
        String filter = "";
        LightNovel ln = null;
        if(lightNovelList.size() > 0){
            do{
                if(filter.isEmpty()){
                    tableLNView();
                }else{
                    lightNovelController.printByStatus(filter);
                }
                menuView("Light Novel");
                try{
                    choice = sc.nextInt();
                }catch (Exception e){
                    System.out.println("Input must be a number!");
                    continue;
                }finally {
                    sc.nextLine();
                }
                System.out.println();
                if(choice == 1){
                    int selectedId = 0;
                    do{
                        tableLNView();
                        selectedId = inputNumber(1, "Input Id to see the detail", 0);
                        ln = (LightNovel) lightNovelController.find(selectedId);
                    }while(ln == null);
                    System.out.println("=========");
                    System.out.println("| Manga |");
                    System.out.println("=========");
                    System.out.println();
                    System.out.println("Name: " + ln.getNameSeries());
                    System.out.println("Volume: " + ln.getCurrentVolume());
                    System.out.println("Status: " + ln.getStatus());
                    System.out.println("Genre: " + ln.getGenre());
                    System.out.println("Progress: " + ln.getCurrentPage());
                    System.out.println("Started reading on: " + ln.getStartDate());
                    System.out.println();
                    int choiceUpdate;
                    do{
                        System.out.println("1. Update Status");
                        System.out.println("2. Update Progress");
                        System.out.println("3. Edit Details");
                        System.out.println("4. Back");
                        System.out.print(">> ");
                        try{
                            choiceUpdate = sc.nextInt();
                        }catch (Exception e){
                            System.out.println("Input must be a number!");
                            continue;
                        }finally {
                            sc.nextLine();
                        }

                        if(choiceUpdate == 1){
                            ln.setStatus(menuStatus[statusSelect() - 1]);
                        }else if(choiceUpdate == 2){
                            int updateProgress = 0;
                            do{
                                try{
                                    updateProgress = sc.nextInt();
                                }catch (Exception e){
                                    System.out.println("Input must be a number!");
                                    continue;
                                }finally {
                                    sc.nextLine();
                                }
                            }while(!(updateProgress >= 1));
                            ln.setCurrentPage(updateProgress);
                        }else if(choiceUpdate == 3){
                            String status, genre;
                            int rating, volume, currPage;
                            genre = inputString("Update Light Novel's genre (Separate each genre with a comma) [Max: 40 Character]", 40);
                            volume = inputNumber(-1, "Update Light Novel's volume", 0);
                            currPage = inputNumber(1, "Update Current page", 0);
                            rating = inputNumber(1, "Update Light Novel's rating [1-5]", 5);
                            status = menuStatus[statusSelect() - 1];
                            ln.setGenre(genre);
                            ln.setCurrentVolume(volume);
                            ln.setRating(rating);
                            ln.setCurrentPage(currPage);
                            ln.setStatus(status);
                            System.out.println("Update Success...");
                        }else if(choiceUpdate == 4){
                            break;
                        }else{
                            continue;
                        }
                        break;
                    }while(true);
                }else if(choice == 2){
                    int selectStatus;
                    do{
                        if(filter.isEmpty()){
                            tableLNView();
                        }else{
                            lightNovelController.printByStatus(filter);
                        }
                        System.out.println("=============");
                        System.out.println("| Filter by |");
                        System.out.println("=============");
                        for(int i = 0; i < 3; i++){
                            System.out.println((i + 1) + ". " + menuStatus[i]);
                        }
                        System.out.println("4. View All");
                        System.out.print(">> ");
                        selectStatus = inputNumber(1, "", 0);
                    }while(!(selectStatus >= 1 && selectStatus <= 4));
                    filter = menuStatus[selectStatus - 1];
                }else if(choice == 3){
                    break;
                }
            }while(true);
        }else{
            System.out.println("You haven't been reading light novel lately...");
        }
    }
    private void tableAnimeView(){
        printLineTable();
        System.out.println("| Id | Anime's Name                        | Season | Genre                                    | Progress  | Status      |");
        printLineTable();
        for (Anime animeView : animeList){
            System.out.printf("| %-2d | %-35s | %-6s | %-40s | %4d/%-4s | %-11s |\n", animeView.getId(), animeView.getNameSeries(), animeView.getSeason(), animeView.getGenre(), animeView.getCurrEpisode(), animeView.getTotalEpisode() > 0 ?  String.valueOf(animeView.getTotalEpisode()) : '?', animeView.getStatus());
        }
        printLineTable();
        System.out.println();
    }

    private void tableMangaView(){
        printLineTable();
        System.out.println("| Id | Manga's Name                        | Volume | Genre                                    | Progress  | Status      |");
        printLineTable();
        for (Manga mangaView : mangaList){
            System.out.printf("| %-2d | %-35s | %-6s | %-40s | CH %-6d | %-11s |\n", mangaView.getId(), mangaView.getNameSeries(), mangaView.getCurrentVolume(), mangaView.getGenre(), mangaView.getCurrentChapter(), mangaView.getStatus());
        }
        printLineTable();
        System.out.println();
    }
    private void tableLNView(){
        printLineTable();
        System.out.println("| Id | Light Novel's Name                        | Volume | Genre                                    | Progress  | Status      |");
        printLineTable();
        for (LightNovel lnView : lightNovelList){
            System.out.printf("| %-2d | %-35s | %-6s | %-40s | PG %-6d | %-11s |\n", lnView.getId(), lnView.getNameSeries(), lnView.getCurrentVolume(), lnView.getGenre(), lnView.getCurrentPage(), lnView.getStatus());
        }
        printLineTable();
        System.out.println();
    }

    private void menuView(String type){
        System.out.println("1. View " + type + " detail by id");
        System.out.println("2. Filter View");
        System.out.println("3. Back");
        System.out.print(">> ");
    }

    public static void printLineTable(){
        for(int i = 0; i < 122; i++){
            System.out.print("=");
        }
        System.out.println();
    }

    private void homeMenu(){
        System.out.println(" /$$      /$$                     /$$             /$$$$$$$$                           /$$");
        System.out.println("| $$  /$ | $$                    | $$            |__  $$__/                          | $$");
        System.out.println("| $$ /$$$| $$  /$$$$$$   /$$$$$$ | $$$$$$$          | $$  /$$$$$$  /$$$$$$   /$$$$$$$| $$   /$$  /$$$$$$   /$$$$$$");
        System.out.println("| $$/$$ $$ $$ /$$__  $$ /$$__  $$| $$__  $$         | $$ /$$__  $$|____  $$ /$$_____/| $$  /$$/ /$$__  $$ /$$__  $$");
        System.out.println("| $$$$_  $$$$| $$$$$$$$| $$$$$$$$| $$  \\ $$         | $$| $$  \\__/ /$$$$$$$| $$      | $$$$$$/ | $$$$$$$$| $$  \\__/");
        System.out.println("| $$$/ \\  $$$| $$_____/| $$_____/| $$  | $$         | $$| $$      /$$__  $$| $$      | $$_  $$ | $$_____/| $$");
        System.out.println("| $$/   \\  $$|  $$$$$$$|  $$$$$$$| $$$$$$$/         | $$| $$     |  $$$$$$$|  $$$$$$$| $$ \\  $$|  $$$$$$$| $$");
        System.out.println("|__/     \\__/ \\_______/ \\_______/|_______/          |__/|__/      \\_______/ \\_______/|__/  \\__/ \\_______/|__/");
        System.out.println("=======================================================================================================================");
        System.out.println();
        System.out.println("1. Add Tracker");
        System.out.println("2. View Tracker");
        System.out.print(">> ");
    }

    private void trackerType(){
        System.out.println("1. Anime");
        System.out.println("2. Manga");
        System.out.println("3. Light Novel");
        System.out.println("4. Back");
        System.out.print(">> ");
    }

    private int statusSelect(){
        int selectStatus;
        do{
            System.out.println();
            System.out.println("====================");
            System.out.println("| Tracker's status |");
            System.out.println("====================");
            for(int i = 0; i < 3; i++){
                System.out.println((i + 1) + ". " + menuStatus[i]);
            }
            System.out.print(">> ");
            selectStatus = inputNumber(1, "", 0);

        }while(!(selectStatus >= 1 && selectStatus <= 3));
        return selectStatus;
    }

    private String inputString(String info, int max){
        String input = "";
        do{
            System.out.print(info + ": ");
            input = sc.nextLine();
            if(max > 0){
                if(input.length() > max){
                    System.out.println("Input exceeds max");
                    input = "";
                }
            }
        }while(input.isEmpty());
        return input;
    }

    private int inputNumber(int defautValue, String info, int max){
        int input;
        do{
            System.out.print((info).isEmpty() ? "" : info + ": ");
            try{
                input = sc.nextInt();
            }catch (Exception e){
                System.out.println("Input must be a number!");
                continue;
            }finally {
                sc.nextLine();
            }
            if(input == 0){
                input = defautValue;
            }
            if(max > 0){
                if(input > max){
                    System.out.println("Input exceeds max");
                    continue;
                }
            }
            break;
        }while(true);
        return input;
    }
}
