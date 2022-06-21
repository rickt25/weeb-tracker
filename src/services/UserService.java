package services;

import database.Connect;
import model.Anime;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    private static Connect con = Connect.getConnection();

    public static User getUser(String email, String password){
        User user = null;
        String query = "SELECT * FROM users";
        ResultSet rs = con.executeQuery(query);

        try {
            while(rs.next()) {
                String passTemp = rs.getString("password");
                String passTempCheck = PasswordService.decrypt(passTemp);

                if(rs.getString("email").equals(email) && passTempCheck.equals(password)){
                    user = new User(rs.getString("name"), rs.getString("email"), passTemp, rs.getDate("dob").toLocalDate(), rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }

    public static void insertUser(User user){
        String query = String.format("INSERT INTO users VALUES"
                + "(null, '%s', '%s', '%s', '%s')", user.getName(), user.getDob(), user.getEmail(), user.getPassword());
        con.executeUpdate(query);
    }
}
