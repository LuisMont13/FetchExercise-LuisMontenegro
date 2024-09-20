package com.example.fetch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class FetchViewModel extends ViewModel {
    private Client r;
    private MutableLiveData<List<Item>> items;

    public FetchViewModel() {
        r = new Client();
        items = new MutableLiveData<>();
    }

    public LiveData<List<Item>> getItems() {
        return items;
    }

    public void fetchItems() {
        r.getFilteredItems(new Client.DataCallback<List<Item>>() {
            @Override
            public void onSuccess(List<Item> data) {
                items.setValue(data);
            }

            @Override
            public void onFailure(Throwable t) {
                // Handle error
            }
        });
    }
}