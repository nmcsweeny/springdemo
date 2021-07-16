package com.example.demo.models;

public class FuelStats {
    private int year;
    private String country;
    private int total;

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public int getTotal(){
        return total;
    }

    public void setTotal(int total){
        this.total = total;
    
    }

    @Override
    public String toString(){
        return "year = " + year + "\'" + ", country = " + country + "\'" + ", total = " + total;
    }
}
