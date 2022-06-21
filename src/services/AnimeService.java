package services;

import database.Connect;
import model.Anime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimeService {
    Connect con = Connect.getConnection();

    public ArrayList<Anime> getAnimeList(){
        ArrayList<Anime> temp = new ArrayList<Anime>();

        String query = "SELECT * FROM anime";


        try {
            ResultSet rs = con.executeQuery(query);
            while(rs.next()) {
                temp.add(new Anime(rs.getInt("id"),rs.getInt("userId"), rs.getString("name"), rs.getString("status"), rs.getDate("startDate"),
                        rs.getInt("rating"), rs.getString("genre"), rs.getInt("season"), rs.getInt("totalEpisode"), rs.getInt("currentEpisode")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return temp;
    }

    public void insertAnime(Anime anime){
        String query = String.format("INSERT INTO anime VALUES"
                + "(null, '%s', '%s', null, %d, '%s', %d, %d, %d, %d)", anime.getNameSeries(), anime.getStatus(), anime.getRating(), anime.getGenre(), anime.getSeason(), anime.getTotalEpisode(), anime.getCurrEpisode(), anime.getUserId());
        con.executeUpdate(query);
    }

    public void updateAnime(Anime anime, int id){
        String query = String.format("UPDATE anime SET "
                        + "name = '%s', "
                        + "status = '%s', "
                        + "startDate = '%s',"
                        + "rating = %d, "
                        + "genre = '%s', "
                        + "season = %d, "
                        + "totalEpisode = %d, "
                        + "currentEpisode = %d "
                        + "WHERE id = %d", anime.getNameSeries(), anime.getStatus(), anime.getStartDate(), anime.getRating(),
                anime.getGenre(), anime.getSeason(), anime.getTotalEpisode(), anime.getCurrEpisode(), id);
        con.executeUpdate(query);
    }

    public void deleteAnime(int id){
        String query = String.format("DELETE FROM anime WHERE id = %d", id);
        con.executeUpdate(query);
    }
}