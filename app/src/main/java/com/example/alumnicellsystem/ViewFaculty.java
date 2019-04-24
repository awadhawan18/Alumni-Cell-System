package com.example.alumnicellsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alumnicellsystem.Responses.FacultyResponse;
import com.example.alumnicellsystem.Utils.Utility;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewFaculty extends AppCompatActivity {

    private ProgressBar progressBar;
    private ViewFacultyAdapter searchAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faculty);

        setTitle("Faculties");

        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.search_results);
        llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new AddCookiesInterceptor(getApplicationContext())); // VERY VERY IMPORTANT
        builder.addInterceptor(new ReceivedCookiesInterceptor(getApplicationContext())); // VERY VERY IMPORTANT
        client = builder.build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UrlService service = retrofit.create(UrlService.class);

        progressBar.setVisibility(View.VISIBLE);
        Call<FacultyResponse> call = service.viewFaculty();
        call.enqueue(new Callback<FacultyResponse>() {
            @Override
            public void onResponse(Call<FacultyResponse> call, Response<FacultyResponse> response) {
                FacultyResponse facultyResponse = response.body();

                Log.v("call", call.toString());

                if (facultyResponse != null && Utility.isStatusOk(facultyResponse.getStatus())) {
                    Log.v("Faculty response ", facultyResponse.toString());

                    searchAdapter = new ViewFacultyAdapter(getApplicationContext(), facultyResponse.getData());
                    recyclerView.setAdapter(searchAdapter);
                } else {
                    if (facultyResponse != null) {
                        Log.v("Faculty response ", facultyResponse.toString());
                    }

                    Toast.makeText(getApplicationContext(), "Cannot find faculty", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<FacultyResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Search Unsuccessful, server error", Toast.LENGTH_SHORT).show();
                Log.v("request failed", t.toString());
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}

