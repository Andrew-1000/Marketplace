package com.example.marketplace.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    private String product_id;
    @SerializedName("title")
    private String title;
    @SerializedName("imageurl")
    private String image;
    @SerializedName("price")
    private String price;
    @SerializedName("description")
    private String description;

    public Product( String title, String image, String price, String description) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.description = description;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
