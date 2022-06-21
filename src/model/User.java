package model;

import java.time.LocalDate;

public class User {
    private String name, email, password;
    private LocalDate dob;
    private int id;
    public User(String name, String email, String password, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public User(String name, String email, String password, LocalDate dob, int id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
