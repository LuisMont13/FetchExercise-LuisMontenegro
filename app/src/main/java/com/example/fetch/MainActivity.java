package com.example.fetch;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FetchViewModel viewModel;
    private AdapterList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(FetchViewModel.class);

        viewModel.getItems().observe(this, items -> {
            adapter = new AdapterList(items);
            recyclerView.setAdapter(adapter);
        });

        viewModel.fetchItems();
    }
}