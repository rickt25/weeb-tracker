package services;

import database.Connect;
import model.Anime;
import model.Manga;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MangaService {
    Connect con = Connect.getConnection();

    public ArrayList<Manga> getMangaList(){
        ArrayList<Manga> temp = new ArrayList<Manga>();

        String query = String.format("SELECT * FROM manga");
        ResultSet rs = con.executeQuery(query);

        try {
            while(rs.next()) {
                temp.add(new Manga(rs.getInt("id"), rs.getInt("userId"), rs.getString("name"), rs.getString("status"), rs.getDate("startDate"),
                        rs.getInt("rating"), rs.getString("genre"), rs.getInt("currentVolume"), rs.getInt("currentChapter")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return temp;
    }

    public void insertManga(Manga manga){
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String query = String.format("INSERT INTO manga VALUES"
                        + "(null, '%s', '%s', '%s', %d, '%s', %d, %d)", manga.getNameSeries(), manga.getStatus(), sqlDate,
                manga.getRating(), manga.getGenre(), manga.getCurrentVolume(),manga.getCurrentChapter());
        con.executeUpdate(query);
    }

    public void updateManga(Manga manga, int id){
        String query = String.format("UPDATE manga SET "
                        + "name = '%s', "
                        + "status = '%s', "
                        + "rating = %d, "
                        + "genre = '%s', "
                        + "currentVolume = %d, "
                        + "currentChapter = %d "
                        + "WHERE id = %d", manga.getNameSeries(), manga.getStatus(), manga.getRating(),
                manga.getGenre(), manga.getCurrentVolume(),manga.getCurrentChapter(), id);
        con.executeUpdate(query);
    }

    public void deleteManga(int id){
        String query = String.format("DELETE FROM manga WHERE id = %d", id);
        con.executeUpdate(query);
    }
}
