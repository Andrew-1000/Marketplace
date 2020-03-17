package com.example.marketplace.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarketplaceClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.myjson.com";

    public static Retrofit getMarketplaceInstance() {
        if (retrofit == null) {
            Retrofit.Builder builder = new Retrofit
                    .Builder();
            builder.baseUrl( BASE_URL );
            builder.addConverterFactory( GsonConverterFactory.create() );
            retrofit = builder//HigherOrder Function // .create() is a callback function
                    .build();
        }
        return retrofit;
    }
}
