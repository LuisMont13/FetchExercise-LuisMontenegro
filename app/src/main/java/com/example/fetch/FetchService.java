package com.example.fetch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FetchService {
    @GET("hiring.json")
    Call<List<Item>> getItems();
}
