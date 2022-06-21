package main;

import controller.AnimeController;
import controller.LightNovelController;
import controller.MangaController;
import model.Anime;
import model.LightNovel;
import model.Manga;
import model.User;
import services.PasswordService;
import services.UserService;

import java.io.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    public static AnimeController animeController = new AnimeController();
    public static MangaController mangaController = new MangaController();
    public static LightNovelController lightNovelController = new LightNovelController();
    private String[] menuStatus = {"Planning", "On Progress", "Finished", ""};
    private Scanner sc = new Scanner(System.in);
    private User userLogin = null;
    private String test;

    public void home(){
        int choice = 0;
        boolean loopMenu = true;
        do{
            isLogin();
            try{
                choice = sc.nextInt();
            }catch (Exception e){
                System.out.println("Input must be a number!");
                continue;
            }finally {
                sc.nextLine();
            }
            System.out.println();
            switch(choice){
                case 1: login(); break;
                case 2: register(); break;
                case 3: loopMenu = false; continue;
                default: break;
            }
        }while(loopMenu);
    }
    private void homeTracker(){
        int choice = 0;
        boolean loopMenu = true;
        do{
            isTrack();
            try{
                choice = sc.nextInt();
            }catch (Exception e){
                System.out.println("Input must be a number!");
                continue;
            }finally {
                sc.nextLine();
            }
            System.out.println();
            switch(choice){
                case 1: addTracker(); break;
                case 2: viewTracker(); break;
                case 3: {
                    userLogin = null;
                    loopMenu = false; continue;
                }
                default: break;
            }

        }while(loopMenu);
    }
    private void register(){
        String name, email, password, rePassword;
        LocalDate dob;
        int day, month, year;
        System.out.println("====================");
        System.out.println("|     Register     |");
        System.out.println("====================");
        System.out.println();
        do {
            System.out.print("Input Name [name length must be at 4 to 20]: ");
            name = sc.nextLine();
            if(name.length() >= 4 && name.length() <= 20){
                break;
            }else{
                System.out.println("Name length must be at 4 to 20");
                System.out.println("Press Enter to Continue...");
                sc.nextLine();
            }
        }while(true);
        do {
            Pattern patternEmail = Pattern.compile("[A-Za-z0-9._%+-]+@gmail.com");
            System.out.print("Input email [must ends with '@gmail.com']: ");
            email = sc.nextLine().toLowerCase();
            Matcher match = patternEmail.matcher(email);
            if(match.matches()){
                break;
            }else{
                System.out.println("Must ends with '@gmail.com'");
                System.out.println("Press Enter to Continue...");
                sc.nextLine();
            }
        }while(true);
        do{
            System.out.print("Input Date of Birth [YEAR (XXXX) must be between 1990 to " + Calendar.getInstance().get(Calendar.YEAR) +"]: ");
            try{
                year = sc.nextInt();
            }catch (Exception e){
                System.out.println("Input must be a number!");
                continue;
            }finally {
                sc.nextLine();
            }
            if(year < 1990 || year > Calendar.getInstance().get(Calendar.YEAR)){
                System.out.println("Year must be between 1990 to " + Calendar.getInstance().get(Calendar.YEAR));
                System.out.println("Press Enter to Continue...");
                sc.nextLine();
            }else{
                break;
            }
        }while(true);
        do{
            System.out.print("Input Date of Birth [MONTH must be between 1 to 12]: ");

            try{
                month = sc.nextInt();
            }catch (Exception e){
                System.out.println("Input must be a number!");
                continue;
            }finally {
                sc.nextLine();
            }
            if(month < 1 || month > 12){
                System.out.println("month must be between 1 to 12");
                System.out.println("Press Enter to Continue...");
                sc.nextLine();
            }else{
                break;
            }
        }while(true);
        do{
            System.out.print("Input Date of Birth [DAY must be between 1 to " + YearMonth.of(year, month).lengthOfMonth() +"]: ");
            try{
                day = sc.nextInt();
            }catch (Exception e){
                System.out.println("Input must be a number!");
                continue;
            }finally {
                sc.nextLine();
            }
            if(day < 1 || day > YearMonth.of(year, month).lengthOfMonth()){
                System.out.println("Day must be between 1 to " + YearMonth.of(year, month).lengthOfMonth());
                System.out.println("Press Enter to Continue...");
                sc.nextLine();
            }else{
                break;
            }
        }while(true);
        dob = LocalDate.of(year, month, day);
        do {
            System.out.print("Input password: ");
            password = sc.nextLine();
            if(password.length() < 8){
                System.out.println("Password length must be more than 7");
                System.out.println("Press Enter to Continue...");
                sc.nextLine();
                continue;
            }
            System.out.print("Re-input password: ");
            rePassword = sc.nextLine();
            if(password.equals(rePassword)){
                break;
            }else{
                System.out.println("Password not match");
                System.out.println("Press Enter to Continue...");
                sc.nextLine();
            }
        }while(true);
        password = PasswordService.encrypt(password);
        test = password;
        userLogin = new User(name, email, password, dob);
        UserService.insertUser(userLogin);
        userLogin = null;
        System.out.println("Register successfull");
        System.out.println("Press Enter to Continue...");
        sc.nextLine();
    }
    private void login(){
        String email, password;
        System.out.println("====================");
        System.out.println("|      Login       |");
        System.out.println("====================");
        System.out.println();
        do {
            Pattern patternEmail = Pattern.compile("[A-Za-z0-9._%+-]+@gmail.com");
            System.out.print("Input email [must ends with '@gmail.com']: ");
            email = sc.nextLine().toLowerCase();
            Matcher match = patternEmail.matcher(email);
            if(match.matches()){
                break;
            }else{
                System.out.println("Must ends with '@gmail.com'");
                System.out.println("Press Enter to Continue...");
                sc.nextLine();
            }
        }while(true);
        do {
            System.out.print("Input password: ");
            password = sc.nextLine();
            if(password.length() >= 8){
                break;
            }else{
                System.out.println("Password length must be more than 7");
                System.out.println("Press Enter to Continue...");
                sc.nextLine();
            }
        }while(true);
        userLogin = UserService.getUser(email, password);
        if(userLogin != null){
            homeTracker();
        }else{
            System.out.println("Email or Password Invalid");
            System.out.println("Press Enter to Continue...");
            sc.nextLine();
        }
    }
    private void addTracker(){
        int choice = 0;
        do{
        	System.out.println("====================");
            System.out.println("|   Add  Tracker   |");
            System.out.println("====================");
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
                case 1: insertAnimeTracker(); break;
                case 2: insertMangaTracker(); break;
                case 3: insertLNTracker(); break;
                case 4: break;
                default: continue;
            }
            break;
        }while(true);
    }

    private void insertAnimeTracker(){
        Anime anime;
        String name, status, genre;
        int rating, seasons, totalEpisode, currEpisode;
        name = inputString("Input Anime's Name [Max: 35 Character]", 35);
        seasons = inputNumber(1, "Input Anime's Seasons (Default 1)", 0);
        genre = inputString("Input Anime's genre (Separate each genre with a comma) [Max: 40 Character]", 40);
        totalEpisode = inputNumber(-1, "Input Anime's Total Episode (If you know input the total, otherwise input -1)", 0);
        currEpisode = inputNumber(1, "Input Current Episode", (totalEpisode == -1) ? 0 : totalEpisode);
        rating = inputNumber(1, "Input Anime's Rating [1-5]", 5);
        status = menuStatus[statusSelect() - 1];
        anime = new Anime(userLogin.getId(), name, status, rating, genre, seasons, totalEpisode, currEpisode);
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
        manga = new Manga(userLogin.getId(), name, status, rating, genre, currentVolume, currentChap);
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
        Ln = new LightNovel(userLogin.getId(), name, status, rating, genre, currentVolume, currentPage);
        lightNovelController.insert(Ln);
    }

    private void viewTracker(){
        int choice = 0;
        boolean loopMenu = true;
        do{
            System.out.println("====================");
            System.out.println("|   View Tracker   |");
            System.out.println("====================");
            trackerType();
            try{
                choice = sc.nextInt();
            }catch (Exception e){
                System.out.println("Input must be a number!");
                continue;
            }finally {
                sc.nextLine();
            }
            System.out.println();
            switch(choice){
                case 1: viewAnimeTracker(); break;
                case 2: viewMangaTracker(); break;
                case 3: viewLNTracker(); break;
                case 4: loopMenu = false; break;
                default: break;
            }
        }while(loopMenu);
    }

    private void viewAnimeTracker(){
        int choice;
        boolean loop = true;
        String filter = "";
        Anime anime = null;
        if(animeController.checkTracker(userLogin.getId())){
            do{
                if(filter.isEmpty()){
                    animeController.printAll(userLogin.getId());
                }else{
                    animeController.printByStatus(filter, userLogin.getId());
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
                        if(filter.isEmpty()){
                            animeController.printAll(userLogin.getId());
                        }else{
                            animeController.printByStatus(filter, userLogin.getId());
                        }
                        selectedId = inputNumber(0, "Input Id to see the detail [Input 0 to 'Cancel']", 0);
                        anime = (Anime)animeController.find(selectedId, filter, userLogin.getId());
                        System.out.println();
                    }while(anime == null && selectedId != 0);
                    if(selectedId != 0){
                        anime.printDetail();
                        int choiceUpdate;
                        do{
                            printMenuDetail();
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
                                animeController.update(anime);
                            }else if(choiceUpdate == 2){
                                int updateProgress = 0;
                                do{
                                    try{
                                        System.out.print("Update progress to [current = " +  anime.getCurrEpisode() +"]: ");
                                        updateProgress = sc.nextInt();
                                    }catch (Exception e){
                                        System.out.println("Input must be a number!");
                                        continue;
                                    }finally {
                                        sc.nextLine();
                                    }
                                }while(!(updateProgress >= 1 && updateProgress <= (anime.getTotalEpisode() == -1 ? Integer.MAX_VALUE : anime.getTotalEpisode())));
                                anime.setCurrEpisode(updateProgress);
                                animeController.update(anime);
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
                                animeController.update(anime);
                                System.out.println("Update Success...");
                            }else if(choiceUpdate == 4){
                                String confirmDelete;
                                do{
                                    confirmDelete = inputString("Are you sure you want to delete this Tracker [y/n] ?", 1);
                                    confirmDelete.toLowerCase();
                                    System.out.println();
                                }while(!confirmDelete.equals("y") && !confirmDelete.equals("n"));
                                if(confirmDelete.equals("y"))
                                    animeController.delete(anime);
                            }else if(choiceUpdate == 5){
                                break;
                            }else{
                                continue;
                            }
                            break;
                        }while(true);
                    }
                }else if(choice == 2){
                    int selectStatus;
                    do{
                        if(filter.isEmpty()){
                            animeController.printAll(userLogin.getId());
                        }else{
                            animeController.printByStatus(filter, userLogin.getId());
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
        if(mangaController.checkTracker(userLogin.getId())){
            do{
                if(filter.isEmpty()){
                    mangaController.printAll(userLogin.getId());
                }else{
                    mangaController.printByStatus(filter, userLogin.getId());
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
                        if(filter.isEmpty()){
                            mangaController.printAll(userLogin.getId());
                        }else{
                            mangaController.printByStatus(filter, userLogin.getId());
                        }
                        selectedId = inputNumber(0, "Input Id to see the detail [Input 0 to 'Cancel']", 0);
                        manga = (Manga) mangaController.find(selectedId, filter, userLogin.getId());
                        System.out.println();
                    }while(manga == null && selectedId != 0);
                    if(selectedId != 0){
                        manga.printDetail();
                        int choiceUpdate;
                        do{
                            printMenuDetail();
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
                                mangaController.update(manga);
                            }else if(choiceUpdate == 2){
                                int updateProgress = 0;
                                do{
                                    try{
                                        System.out.print("Update progress to [current = " +  manga.getCurrentChapter() +"]: ");
                                        updateProgress = sc.nextInt();
                                    }catch (Exception e){
                                        System.out.println("Input must be a number!");
                                        continue;
                                    }finally {
                                        sc.nextLine();
                                    }
                                }while(!(updateProgress >= 1));
                                manga.setCurrentChapter(updateProgress);
                                mangaController.update(manga);
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
                                mangaController.update(manga);
                                System.out.println("Update Success...");
                            }else if(choiceUpdate == 4){
                                String confirmDelete;
                                do{
                                    confirmDelete = inputString("Are you sure you want to delete this Tracker [y/n] ?", 1);
                                    confirmDelete.toLowerCase();
                                    System.out.println();
                                }while(!confirmDelete.equals("y") && !confirmDelete.equals("n"));
                                if(confirmDelete.equals("y"))
                                    mangaController.delete(manga);
                            }else if(choiceUpdate == 5){
                                break;
                            }else{
                                continue;
                            }
                            break;
                        }while(true);
                    }
                }else if(choice == 2){
                    int selectStatus;
                    do{
                        if(filter.isEmpty()){
                            mangaController.printAll(userLogin.getId());
                        }else{
                            mangaController.printByStatus(filter, userLogin.getId());
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
        if(lightNovelController.checkTracker(userLogin.getId())){
            do{
                if(filter.isEmpty()){
                    lightNovelController.printAll(userLogin.getId());
                }else{
                    lightNovelController.printByStatus(filter,userLogin.getId());
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
                        if(filter.isEmpty()){
                            lightNovelController.printAll(userLogin.getId());
                        }else{
                            lightNovelController.printByStatus(filter, userLogin.getId());
                        }
                        selectedId = inputNumber(0, "Input Id to see the detail [Input 0 to 'Cancel']", 0);
                        ln = (LightNovel) lightNovelController.find(selectedId, filter,userLogin.getId());
                        System.out.println();
                    }while(ln == null && selectedId != 0);
                    if(selectedId != 0){
                        ln.printDetail();
                        int choiceUpdate;
                        do{
                            printMenuDetail();
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
                                lightNovelController.update(ln);
                            }else if(choiceUpdate == 2){
                                int updateProgress = 0;
                                do{
                                    try{
                                        System.out.print("Update progress to [current = " +  ln.getCurrentPage() +"]: ");
                                        updateProgress = sc.nextInt();
                                    }catch (Exception e){
                                        System.out.println("Input must be a number!");
                                        continue;
                                    }finally {
                                        sc.nextLine();
                                    }
                                }while(!(updateProgress >= 1));
                                ln.setCurrentPage(updateProgress);
                                lightNovelController.update(ln);
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
                                lightNovelController.update(ln);
                                System.out.println("Update Success...");
                            }else if(choiceUpdate == 4){
                                String confirmDelete;
                                do{
                                    confirmDelete = inputString("Are you sure you want to delete this Tracker [y/n] ?", 1);
                                    confirmDelete.toLowerCase();
                                    System.out.println();
                                }while(!confirmDelete.equals("y") && !confirmDelete.equals("n"));
                                if(confirmDelete.equals("y"))
                                    lightNovelController.delete(ln);
                            }else if(choiceUpdate == 5){
                                break;
                            }else{
                                continue;
                            }
                            break;
                        }while(true);
                    }
                }else if(choice == 2){
                    int selectStatus;
                    do{
                        if(filter.isEmpty()){
                            lightNovelController.printAll(userLogin.getId());
                        }else{
                            lightNovelController.printByStatus(filter,userLogin.getId());
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

    private void menuView(String type){
        System.out.println("1. View " + type + " detail by Id");
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
    private void printMenuDetail(){
        System.out.println("1. Update Status");
        System.out.println("2. Update Progress");
        System.out.println("3. Edit Details");
        System.out.println("4. Delete Tracker");
        System.out.println("5. Back");
        System.out.print(">> ");
    }

    private void homeTitle(){
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

    }

    private void isLogin(){
        homeTitle();
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print(">> ");
    }
    private void isTrack(){
        homeTitle();
        System.out.println("1. Add Tracker");
        System.out.println("2. View Tracker");
        System.out.println("3. Log Out");
        System.out.print(">> ");
    }
    private void trackerType(){
        System.out.println("| 1. Anime         |");
        System.out.println("| 2. Manga         |");
        System.out.println("| 3. Light Novel   |");
        System.out.println("| 4. Back          |");
        System.out.println("====================");
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
                System.out.printf("|%d. %-14s |\n" , i+1, menuStatus[i]);
            }
            System.out.println("====================");
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
                    System.out.println("Input exceeds max!");
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
                    System.out.println("Input exceeds max!");
                    continue;
                }
            }
            break;
        }while(true);
        return input;
    }
}
