package main;

public class Main {

    // Sebelum menjalankan aplikasi, pastikan meng-import SQL yg ada pada folder sql
    // Nama Database : weeb-tracker
    // note : jika ada username / password dapat di setting di database > Connect.java

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
        menu.home();
    }

}
