package com.example.alumnicellsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alumnicellsystem.Responses.AddFacultyResponse;
import com.example.alumnicellsystem.Utils.Utility;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddFaculty extends AppCompatActivity {

    private EditText emailField;
    private String email;
    private Button addFaculty;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        setTitle("Add Faculty");

        emailField = findViewById(R.id.faculty_emailEt);
        addFaculty = findViewById(R.id.add_faculty);
        progressBar = findViewById(R.id.progress_bar);


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

        addFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailField.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(AddFaculty.this, "Add Faculty email", Toast.LENGTH_SHORT).show();
                } else if (!Utility.isValidEmail(email)) {
                    Toast.makeText(AddFaculty.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    Call<AddFacultyResponse> call = service.addFacultyRequest(email, 1, 0);
                    call.enqueue(new Callback<AddFacultyResponse>() {
                        @Override
                        public void onResponse(Call<AddFacultyResponse> call, Response<AddFacultyResponse> response) {
                            AddFacultyResponse addFacultyResponse = response.body();

                            if (addFacultyResponse != null && Utility.isStatusOk(addFacultyResponse.getStatus())) {
                                Log.v("Faculty response ", addFacultyResponse.toString());
                                Toast.makeText(AddFaculty.this, "Email added Successfully", Toast.LENGTH_SHORT).show();

                            } else if (addFacultyResponse != null) {
                                Log.v("Faculty response ", addFacultyResponse.toString());
                                Toast.makeText(AddFaculty.this, addFacultyResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            progressBar.setVisibility(View.INVISIBLE);

                        }

                        @Override
                        public void onFailure(Call<AddFacultyResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Request Unsuccessful, server error", Toast.LENGTH_SHORT).show();
                            Log.v("request failed", t.toString());
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });

    }
}
