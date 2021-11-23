package com.example.praktika.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model();

    private final Map<Integer, User> model;

    public static Model getInstance(){
        return instance;
    }

    private Model(){
        model = new HashMap<>();

        model.put(1, new User("Mikhail", "Yakushev", 123456));
        model.put(2, new User("Oleg", "Petrov", 44444));
        model.put(3, new User("Liza", "Korovina", 77777));
    }
    public void add(User user, int id){
        model.put(id, user);
    }

    public void delete(int id){
        model.remove(id);
    }

    public void edit(User user, int id){
        model.replace(id, user);
    }

    public Map<Integer, User> getFromList(){
        return model;
    }
}
