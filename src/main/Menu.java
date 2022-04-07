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
    private String[] menuStatus = {"Planning", "On Progress", "Finished", "Back"};
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
                    insertAnimeTracker();
                    break;
                case 2:
                    insertMangaTracker();
                    break;
                case 3:
                    insertLNTracker();
                    break;
                case 4:
                    loopMenu = false;
                    continue;
                default:
                    break;
            }
        }while(loopMenu);
    }

    private void insertAnimeTracker(){
        Anime anime;
        String name, status, genre;
        int rating, seasons, totalEpisode, currEpisode;
        name = inputString("Input Anime's Name [Max: 35 Character]", 35);
        seasons = inputNumber(-1, "Input Anime's Total Seasons (Default 1)", 0);
        genre = inputString("Input Anime's genre (Separate each genre with a comma) [Max: 40 Character]", 40);
        totalEpisode = inputNumber(-1, "Input Anime's Total Episode (If you know input the total, otherwise input -1)", 0);
        rating = inputNumber(1, "Input Anime's Rating [1-5]", 5);
        currEpisode = inputNumber(1, "Input Current Episode", (totalEpisode == -1) ? 0 : totalEpisode);
        status = menuStatus[statusSelect() - 1];
        if(status.equals("Back")){
            return;
        }
        anime = new Anime(name, status, rating, genre, seasons, totalEpisode, currEpisode);
        animeController.insert(anime);
    }

    private void insertMangaTracker(){
        Manga manga;
        String name, status, genre;
        int rating, currentVolume, currentChap;
        name = inputString("Input Manga's name [Max: 35 Character]", 35);
        currentVolume = inputNumber(-1, "Input Manga's current volume (Default 1)", 0);
        genre = inputString("Input Manga's genre (Separate each genre with a comma) [Max: 40 Character]", 40);
        currentChap = inputNumber(-1, "Input Manga's current chapter (If you know input the total, otherwise input -1)", 0);
        rating = inputNumber(1, "Input Manga's rating [1-5]", 5);
        status = menuStatus[statusSelect() - 1];
        if(status.equals("Back")){
            return;
        }
        manga = new Manga(name, status, rating, genre, currentVolume, currentChap);
        mangaController.insert(manga);
    }

    private void insertLNTracker(){
        LightNovel Ln;
        String name, status, genre;
        int rating, currentVolume, currentPage;
        name = inputString("Input Light Novel's name [Max: 35 Character]", 35);
        currentVolume = inputNumber(-1, "Input Light Novel's total volume (Default 1)", 0);
        genre = inputString("Input Light Novel's genre (Separate each genre with a comma) [Max: 40 Character]", 40);
        currentPage = inputNumber(-1, "Input Light Novel's current page (If you know input the total, otherwise input -1)", 0);
        rating = inputNumber(1, "Input Light Novel's rating [1-5]", 5);
        status = menuStatus[statusSelect() - 1];
        if(status.equals("Back")){
            return;
        }
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
                    insertMangaTracker();
                    break;
                case 3:
                    insertLNTracker();
                    break;
                case 4:
                    loopMenu = false;
                    continue;
                default:
                    break;
            }
        }while(loopMenu);
    }

    private void viewAnimeTracker(){
        printLineTable();
        System.out.println("| Id | Anime's Name                        | Season | Genre                                    | Progress  | Status     |");
        printLineTable();
        for (Anime animeView : animeList){
            System.out.printf("| %-2d | %-35s | %-6s | %-40s | %4d/%-4d | %-10s |\n", animeView.getId(), animeView.getNameSeries(), animeView.getSeason(), animeView.getGenre(), animeView.getCurrEpisode(), animeView.getTotalEpisode(), animeView.getStatus());
        }
        printLineTable();
    }

    private void printLineTable(){
        for(int i = 0; i < 121; i++){
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
            System.out.println("===================");
            System.out.println("| Tracker's status |");
            System.out.println("===================");
            for(int i = 0; i < 4; i++){
                System.out.println((i + 1) + ". " + menuStatus[i]);
            }
            System.out.print(">> ");
            selectStatus = inputNumber(1, "", 0);

        }while(!(selectStatus >= 1 && selectStatus <= 4));
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
