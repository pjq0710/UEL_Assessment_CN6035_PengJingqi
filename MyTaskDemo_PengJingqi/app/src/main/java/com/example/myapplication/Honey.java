package com.example.myapplication;


public class Honey {
    private String name;
    private String price;
    private int imageResId;

    public Honey(String name,String price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }


    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
