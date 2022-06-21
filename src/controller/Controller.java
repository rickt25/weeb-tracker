package controller;

import model.Tracker;

public interface Controller {
    boolean checkTracker(int userId);
    Tracker find(int id, String status, int userId); // this function will return an object
    void insert(Tracker tracker);
    void delete(Tracker tracker);
    void printAll(int userId);
    void printByStatus(String status, int userId);

    void update(Tracker tracker);
}
