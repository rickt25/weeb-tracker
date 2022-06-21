package services;

import database.Connect;
import model.Anime;
import model.LightNovel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LightNovelService {
    Connect con = Connect.getConnection();

    public ArrayList<LightNovel> getLightNovelList(){
        ArrayList<LightNovel> temp = new ArrayList<LightNovel>();

        String query = String.format("SELECT * FROM light_novel");
        ResultSet rs = con.executeQuery(query);

        try {
            while(rs.next()) {
                temp.add(new LightNovel(rs.getInt("id"), rs.getInt("userId"), rs.getString("name"), rs.getString("status"), rs.getDate("startDate"),
                        rs.getInt("rating"), rs.getString("genre"), rs.getInt("currentVolume"), rs.getInt("currentPage")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return temp;
    }

    public void insertLightNovel(LightNovel lightNovel){
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String query = String.format("INSERT INTO light_novel VALUES"
                + "(null, '%s', '%s', '%s', %d, '%s', %d, %d)", lightNovel.getNameSeries(), lightNovel.getStatus(), sqlDate, lightNovel.getRating(), lightNovel.getGenre(), lightNovel.getCurrentVolume(), lightNovel.getCurrentPage());
        con.executeUpdate(query);
    }

    public void updateLightNovel(LightNovel lightnovel, int id){
        String query = String.format("UPDATE LightNovel SET "
                        + "name = '%s', "
                        + "status = '%s', "
                        + "rating = %d, "
                        + "genre = '%s', "
                        + "currentVolume = %d, "
                        + "currentPage = %d "
                        + "WHERE id = %d", lightnovel.getNameSeries(), lightnovel.getStatus(), lightnovel.getRating(),
                lightnovel.getGenre(), lightnovel.getCurrentVolume(), lightnovel.getCurrentPage(), id);
        con.executeUpdate(query);
    }

    public void deleteLightNovel(int id){
        String query = String.format("DELETE FROM LightNovel WHERE id = %d", id);
        con.executeUpdate(query);
    }
}
