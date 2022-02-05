package com.careerdevs;

import java.util.ArrayList;

public class MovieRenter {
    private String name;
    private int wallet;
    private ArrayList<Movie> moviesRented;
    // private Movie favoriteMovie;  example of having one movie not an arraylist<movie>

    public MovieRenter(String name, int wallet) {
        this.name = name;
        this.wallet = wallet;
        this.moviesRented = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public int getWallet() {
        return wallet;
    }

    public ArrayList<Movie> getMoviesRented() {
        return moviesRented;
    }

    public int payWithWallet(int amount) {
        if (wallet - amount < 0 && amount > 0) {
            return 0;
        } else {
            wallet -= amount;
            return amount;
        }
    }

    public void addMovie(Movie movieToAdd) {
        moviesRented.add(movieToAdd); // add to the array
    }

    public void returnMovie(Movie movieToReturn) {
        moviesRented.remove(movieToReturn);
    }
}