package controller;

import model.Tracker;

public interface Controller {
    Tracker find(int id, String status); // this function will return an object
    void insert(Tracker tracker);
    void delete(Tracker tracker);
    void printAll();
    void printByStatus(String status);
}
