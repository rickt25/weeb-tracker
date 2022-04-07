package controller;

import model.Tracker;

public interface Controller {
    void printByStatus(String status);
    Tracker find(int id); // this function will return an object
    void insert(Tracker tracker);
    void delete(int id);
}
