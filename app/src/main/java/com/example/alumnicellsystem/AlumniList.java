package com.example.alumnicellsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.alumnicellsystem.Responses.SearchData;
import com.example.alumnicellsystem.Responses.SearchResponse;

import java.util.ArrayList;
import java.util.List;

public class AlumniList extends AppCompatActivity {

    private SearchAdapter searchAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private SearchResponse searchResponse;
    private List<SearchData> searchDataList;
    private TextView noResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_list);

        getSupportActionBar().setTitle("Search Results");

        searchResponse = getIntent().getParcelableExtra("search");
        searchDataList = searchResponse.getData();

        noResults = findViewById(R.id.no_results);
        recyclerView = findViewById(R.id.search_results);
        llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        if(searchDataList.size()==0){
            noResults.setVisibility(View.VISIBLE);
        }else {
            searchAdapter = new SearchAdapter(getApplicationContext(), searchDataList);
            recyclerView.setAdapter(searchAdapter);
        }

    }
}
