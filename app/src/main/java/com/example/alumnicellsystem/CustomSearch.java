package com.example.alumnicellsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alumnicellsystem.Constants.UserFields;
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

    private EditText nameField, yearField, companyField, enrollmentNoField;
    private Spinner branchSpinner;
    private String name, branch, company, year, enrollmentNo;
    private Button search;
    private HashMap<String, String> searchOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_search_activity);

        getSupportActionBar().setTitle("Custom Search");

        nameField = findViewById(R.id.nameET);
        yearField = findViewById(R.id.yearET);
        companyField = findViewById(R.id.companyET);
        enrollmentNoField = findViewById(R.id.roll_noET);
        branchSpinner = findViewById(R.id.branch);
        search = findViewById(R.id.search);
        searchOptions = new HashMap<>();


        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new AddCookiesInterceptor(getApplicationContext())); // VERY VERY IMPORTANT
        builder.addInterceptor(new ReceivedCookiesInterceptor(getApplicationContext())); // VERY VERY IMPORTANT
        client = builder.build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bvpapp.herokuapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UrlService service = retrofit.create(UrlService.class);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameField.getText().toString();
                branch = branchSpinner.getSelectedItem().toString();
                company = companyField.getText().toString();
                enrollmentNo = enrollmentNoField.getText().toString();
                year = yearField.getText().toString();

                if(!TextUtils.isEmpty(name)){
                    searchOptions.put(UserFields.NAME, name.toUpperCase());
                }

                if(!TextUtils.isEmpty(year)){
                    searchOptions.put(UserFields.YEAR, year);
                }

                if(!branch.equalsIgnoreCase("Select")){
                    searchOptions.put(UserFields.BRANCH, branch.toUpperCase());
                }

                if(!TextUtils.isEmpty(company)){
                    searchOptions.put(UserFields.COMPANY_1, company.toUpperCase());
                }

                if(!TextUtils.isEmpty(enrollmentNo)){
                    searchOptions.put(UserFields.ENROLLMENT_NO, enrollmentNo);
                }

                if(TextUtils.isEmpty(name) && TextUtils.isEmpty(company) && TextUtils.isEmpty(branch)
                        && TextUtils.isEmpty(year) && TextUtils.isEmpty(enrollmentNo)){
                    Toast.makeText(getApplicationContext(), "Enter at least 1 field", Toast.LENGTH_SHORT).show();
                }
                else {
                    Call<SearchResponse> call = service.customSearchRequest(searchOptions);
                    //Call<SearchResponse> call = service.searchRequest(name,branch,enrollmentNo,company);
                    call.enqueue(new Callback<SearchResponse>() {
                        @Override
                        public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                            SearchResponse searchResponse = response.body();

                            Log.v("call", call.toString());

                            if(searchResponse != null && Utility.isStatusOk(searchResponse.getStatus())){
                                Log.v("Search response ",searchResponse.toString());

                                Intent intent = new Intent(getApplicationContext(), AlumniList.class);
                                intent.putExtra("search", searchResponse);
                                startActivity(intent);
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
