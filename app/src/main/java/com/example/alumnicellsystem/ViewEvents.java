package com.example.alumnicellsystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alumnicellsystem.Responses.ViewEventsResponse;
import com.example.alumnicellsystem.Utils.Utility;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewEvents extends AppCompatActivity {

    private ProgressBar progressBar;
    private ViewEventsAdapter viewEventsAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_events_layout);

        setTitle("Events");

        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.eventsResult);
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
        Call<ViewEventsResponse> call = service.getEvents();
        call.enqueue(new Callback<ViewEventsResponse>() {
            @Override
            public void onResponse(Call<ViewEventsResponse> call, Response<ViewEventsResponse> response) {
                ViewEventsResponse eventsResponse = response.body();

                Log.v("call", call.toString());

                if (eventsResponse != null && Utility.isStatusOk(eventsResponse.getStatus())) {
                    Log.v("Events response ", eventsResponse.toString());

                    viewEventsAdapter = new ViewEventsAdapter(getApplicationContext(), eventsResponse.getData());
                    recyclerView.setAdapter(viewEventsAdapter);
                } else {
                    if (eventsResponse != null) {
                        Log.v("Events response ", eventsResponse.toString());
                    }

                    Toast.makeText(getApplicationContext(), "Cannot find Events", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<ViewEventsResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server error", Toast.LENGTH_SHORT).show();
                Log.v("request failed", t.toString());
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
