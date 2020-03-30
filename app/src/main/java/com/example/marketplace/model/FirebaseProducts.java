package com.example.marketplace.model;

public class FirebaseProducts {

    private String name;
    private String price;
    private String image;

    public FirebaseProducts( String title, String price, String image) {
        this.name = title;
        this.price = price;
        this.image = image;
    }

    public FirebaseProducts() {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
