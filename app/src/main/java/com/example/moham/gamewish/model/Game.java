package com.example.moham.gamewish.model;

/**
 * Created by Mozeeb on 17/03/2018.
 */

public class Game {

    public String name;
    public String dev;
    public String genre;

    //Constructor for Game
    public Game(String name, String dev, String genre){
        this.name = name;
        this.dev = dev;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getDev() {
        return dev;
    }

    public String getGenre() {
        return genre;
    }
}
