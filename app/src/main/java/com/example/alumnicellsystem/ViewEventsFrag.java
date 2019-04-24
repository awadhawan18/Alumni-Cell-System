package com.example.alumnicellsystem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumnicellsystem.Responses.ViewEventsResponse;
import com.example.alumnicellsystem.Utils.Utility;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewEventsFrag extends Fragment {

    private FloatingActionButton fab;
    private ProgressBar progressBar;
    private ViewEventsAdapter viewEventsAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_events_layout, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressBar = getActivity().findViewById(R.id.progress_bar);
        recyclerView = getActivity().findViewById(R.id.eventsResult);
        llm = new LinearLayoutManager(getActivity().getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),
                DividerItemDecoration.VERTICAL));


        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new AddCookiesInterceptor(getActivity().getApplicationContext())); // VERY VERY IMPORTANT
        builder.addInterceptor(new ReceivedCookiesInterceptor(getActivity().getApplicationContext())); // VERY VERY IMPORTANT
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

                    viewEventsAdapter = new ViewEventsAdapter(getActivity().getApplicationContext(), eventsResponse.getData());
                    recyclerView.setAdapter(viewEventsAdapter);
                } else {
                    if (eventsResponse != null) {
                        Log.v("Events response ", eventsResponse.toString());
                    }

                    Toast.makeText(getActivity().getApplicationContext(), "Cannot find Events", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<ViewEventsResponse> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Server error", Toast.LENGTH_SHORT).show();
                Log.v("request failed", t.toString());
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
