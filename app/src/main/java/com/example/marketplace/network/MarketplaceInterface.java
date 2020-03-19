package com.example.marketplace.network;

import com.example.marketplace.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MarketplaceInterface {

    @GET("/bins/8xile")
    Call<List<Product>> getAllProducts();

}

