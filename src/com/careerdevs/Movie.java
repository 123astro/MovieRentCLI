package com.careerdevs;

public class Movie {

    private String title;
    private String genre;
    private int pricePerDay; //this is in cents

    public Movie(String title, String genre, int pricePerDay) {
        this.title = title;
        this.genre = genre;
        setPricePerDay(pricePerDay);
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        if (pricePerDay < 0) System.out.println("Error: Price should not be less than 0");
        else
            this.pricePerDay = pricePerDay;
    }


}
