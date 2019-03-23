package com.example.alumnicellsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alumnicellsystem.Constants.UserFields;
import com.example.alumnicellsystem.Responses.LoginResponse;
import com.example.alumnicellsystem.Responses.SearchResponse;
import com.example.alumnicellsystem.Utils.Utility;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomSearch extends AppCompatActivity {

    private EditText nameField, branchField, yearField, companyField;
    private String name, branch, company, year;
    private Button search;
    private HashMap<String, String> searchOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_search_activity);

        nameField = findViewById(R.id.name);
        branchField = findViewById(R.id.branch);
        yearField = findViewById(R.id.year);
        companyField = findViewById(R.id.company);
        search = findViewById(R.id.search);
        searchOptions = new HashMap<>();


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

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameField.getText().toString();
                branch = branchField.getText().toString();
                company = companyField.getText().toString();
                year = yearField.getText().toString();

                if(!TextUtils.isEmpty(name)){
                    searchOptions.put(UserFields.NAME, name);
                }

                if(!TextUtils.isEmpty(year)){
                    searchOptions.put(UserFields.YEAR, year);
                }

                if(!TextUtils.isEmpty(branch)){
                    searchOptions.put(UserFields.BRANCH, branch);
                }

                if(!TextUtils.isEmpty(company)){
                    searchOptions.put(UserFields.COMPANY, company);
                }
                if(TextUtils.isEmpty(name) && TextUtils.isEmpty(company) && TextUtils.isEmpty(branch) && TextUtils.isEmpty(year)){
                    Toast.makeText(getApplicationContext(), "Enter at least 1 field", Toast.LENGTH_SHORT).show();
                }
                else {
                    Call<SearchResponse> call = service.searchRequest(branch);
                    call.enqueue(new Callback<SearchResponse>() {
                        @Override
                        public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                            SearchResponse searchResponse = response.body();


                            if(searchResponse != null && Utility.isStatusOk(searchResponse.getStatus())){
                                Log.v("Search response ",searchResponse.toString());

                            }
                            else {
                                if(searchResponse != null){
                                    Log.v("Search response ",searchResponse.toString());
                                }

                                Toast.makeText(getApplicationContext(), "Cannot find alumni", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<SearchResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Search Unsuccessful, server error", Toast.LENGTH_SHORT).show();
                            Log.v("request failed", t.toString());
                        }
                    });
                }
            }
        });

    }
}
