package com.example.fetch;
import androidx.annotation.NonNull;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Client {

    private final FetchService fetchService;

    public Client() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fetchService = retrofit.create(FetchService.class);
    }

    public void getFilteredItems(final DataCallback<List<Item>> callback) {
        fetchService.getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(@NonNull Call<List<Item>> call, @NonNull Response<List<Item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Filter and sort the list
                    List<Item> filteredList = response.body().stream()
                            .filter(item -> item.getName() != null && !item.getName().isEmpty())
                            .sorted(Comparator.comparingInt(Item::getListId)
                                    .thenComparing(item -> extractNumberFromName(item.getName())))
                            .collect(Collectors.toList());
                    callback.onSuccess(filteredList);
                } else {
                    callback.onFailure(new Exception("Failed to fetch items"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Item>> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    private int extractNumberFromName(String name) {
        if (name == null || !name.matches(".*\\d+.*")) {
            return 0; // Return 0 if no number is found or name is null
        }
        // Extract numeric part of the name (assuming it's like "item 50")
        return Integer.parseInt(name.replaceAll("\\D+", "")); // Remove non-numeric characters
    }

    public interface DataCallback<T> {
        void onSuccess(T data);
        void onFailure(Throwable t);
    }
}