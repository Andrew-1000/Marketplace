package com.example.marketplace.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    private String product_id;
    @SerializedName("title")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private String price;
    private String location;
    private String category;
    private String imageName;
    @SerializedName("imageurl")
    private String image;



    public Product( String title, String image, String price) {
        this.name = title;
        this.image = image;
        this.price = price;

    }
    public Product( String title,  String description, String price, String location, String category, String image) {
        this.name = title;
        this.description = description;
        this.price = price;
        this.location = location;
        this.category = category;
        this.image = image;

    }


    public Product() {

    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
