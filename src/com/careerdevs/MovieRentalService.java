package com.careerdevs;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class MovieRentalService {
    //this a static class bc all the fields and methods are static = pojo are used to create instances of
    // static as a class variable
    // nonstatic field is an instances variable

    // Movie Storage
    private static MovieRenter movieRenter;  // declaring fields
    private static ArrayList<Movie> movieStorage; // all the movies in the store

    //Movie rental menus

    public static void main(String[] args) {
        System.out.println("MOVIE RENTAL SERVICE\n\n");
        movieRenter = createMovieRenter();
        //movieRenter = new MovieRenter("Jon", 10000); // create an instance of MovieRenter
        initializeMovies();

        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("\n" + "-".repeat(15) + "\nMAIN MENU");
        System.out.println("You have " + (changeMoneyToDollars((float) movieRenter.getWallet() / 100)));
        System.out.println("1) Rent Movie");
        System.out.println("2) Return Movie");
        System.out.println("3) Exit");
        int selection = UserInterface.readInt("Select an option", 1, 3);
            switch (selection) {
                case 1 -> rentMovieMenu();
                case 2 -> returnMovieMenu();
                case 3 -> System.out.println("GoodBye");
            }
        }



    private static void initializeMovies() {
        movieStorage = new ArrayList<Movie>();  // instantiate a new ArrayList
        movieStorage.add(new Movie("Matrix 1", "SciFi", 1000));
        movieStorage.add(new Movie("Matrix 2", "SciFi", 100));
        movieStorage.add(new Movie("Matrix 3", "SciFi", 51));
        movieStorage.add(new Movie("Matrix 4", "SciFi", 500));


        //Method 3
//        movieStorage.addAll(List.of(new Movie[]{
//                new Movie("Matrix 1", "SciFi", 1000),
//                new Movie("Matrix 1", "SciFi", 100),
//                new Movie("Matrix 1", "SciFi", 50),
//                new Movie("Matrix 1", "SciFi", 500)
//        }));
    }

    private static MovieRenter createMovieRenter() {

        System.out.println("Before you start renting movies, you need to make an account");
        String name;
        int startMoney;

        while (true) {
            name = UserInterface.readString("Enter your name");
            startMoney = UserInterface.readInt("How much money do you have is USD? ", 5, 500);
            //Confirm choices;
            System.out.println("Your name is " + name + " and you will start with $" + startMoney + ".00");
            boolean confirmation = UserInterface.yerOrNo("Do you confirm your choices? ");
            if (confirmation) break;
        }

        System.out.println("Okay you can rent movies now " + name);
        return new MovieRenter(name, startMoney * 100);

    }

    private static String changeMoneyToDollars(Float num) {
        DecimalFormat decimalFormat = new DecimalFormat("\u00a4#,##0.00");
        return decimalFormat.format(num);
    }

//    private static String changeMoneyToDollars(int num) {
//        DecimalFormat decimalFormat = new DecimalFormat("\u00a4#,##0.00");
//        return decimalFormat.format(num);
//    }


    private static void rentMovieMenu() {
        if (movieStorage.size() == 0) {
            System.out.println("No movies are available. ");
            return;
        }
        System.out.println("Rental Menu");
        for (int i = 0; i < movieStorage.size(); i++) {
            Movie tempMovie = movieStorage.get(i);
            float price = (float) tempMovie.getPricePerDay() / 100;
            System.out.println(i + 1 + ") " + tempMovie.getTitle() + " - " + changeMoneyToDollars(price) + " per/day" +
                    " ");
        }
        int userInput = UserInterface.readInt("Select a Movie", 1, movieStorage.size());
        Movie rentedMovie = movieStorage.get(userInput - 1);

        int daysToRent = UserInterface.readInt("How many days would you like to rent the movie?", 1, 14);

        float totalAmt = (rentedMovie.getPricePerDay() * daysToRent);

        String confirming = "Are you sure you want to rent " + rentedMovie.getTitle() + " for " + daysToRent + " " +
                "day(s)?";

        confirming += "\nTotal Price: " + changeMoneyToDollars(totalAmt / 100);

        if (UserInterface.yerOrNo(confirming)) {
            int paidAmt = movieRenter.payWithWallet((int)totalAmt); //
            if (paidAmt != 0) {
                movieStorage.remove(rentedMovie); // remove from storage and add to renter
                movieRenter.addMovie(rentedMovie);
                System.out.println("Thanks " + movieRenter.getName() + ", you are now renting " + rentedMovie.getTitle());
                System.out.println("You have " + daysToRent + " to return the movie.");
            } else {
                System.out.println("You don't have the funds");
            }
        } else {
            System.out.println("Okay, maybe next time...");
        }
        mainMenu();
    }

    private static void returnMovieMenu() {

        ArrayList<Movie> rentersMovies = movieRenter.getMoviesRented();

        if (rentersMovies.size() == 0) {
            System.out.println("You don't have any movies to return. Back to Main Menu");
            mainMenu();
        }
        System.out.println("\n" + "-".repeat(15) + "\nRETURN MENU");
        for (int i = 0; i < rentersMovies.size(); i++) {
            Movie tempMovie = rentersMovies.get(i);
            System.out.println((i + 1) + ") " + tempMovie.getTitle());
        }
            int userInput = UserInterface.readInt("Select a Movie", 1, movieStorage.size());
            Movie rentedMovie = rentersMovies.get(userInput - 1);

            String confirming = "Are you sure you want to return " + rentedMovie.getTitle();

            if (UserInterface.yerOrNo(confirming)) {
                movieStorage.add(rentedMovie);
                movieRenter.returnMovie(rentedMovie);
                System.out.println("Thanks " + movieRenter.getName() + " for returning " + rentedMovie.getTitle());

            } else {
                System.out.println("Okay, maybe next time...");
            }
            mainMenu();
        }
    }

