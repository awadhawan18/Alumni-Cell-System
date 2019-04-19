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

import com.example.alumnicellsystem.Responses.AlumniConfirmResponse;
import com.example.alumnicellsystem.Responses.AlumniConfirmResponse;
import com.example.alumnicellsystem.Utils.Utility;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlumniOTP extends AppCompatActivity {

    private Button verfiy;
    private EditText otpET;
    private String otp, enrollmentNo, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_otp);

        otpET = findViewById(R.id.otp);
        verfiy = findViewById(R.id.verify);
        enrollmentNo = getIntent().getStringExtra("rollNo");
        password = getIntent().getStringExtra("password");

        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UrlService service = retrofit.create(UrlService.class);


        verfiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = otpET.getText().toString();

                if(TextUtils.isEmpty(otp)){
                    Toast.makeText(getApplicationContext(), "Enter otp please", Toast.LENGTH_SHORT).show();
                }
                else {

                    Call<AlumniConfirmResponse> call = service.alumniConfirmRequest(enrollmentNo, otp, password);
                    call.enqueue(new Callback<AlumniConfirmResponse>() {
                        @Override
                        public void onResponse(Call<AlumniConfirmResponse> call, Response<AlumniConfirmResponse> response) {
                            AlumniConfirmResponse confirmResponse = response.body();


                            if(confirmResponse != null && Utility.isOtpStatusOk(confirmResponse.getStatus())){
                                Log.v("Alumni Otp response ",confirmResponse.toString());
                                Toast.makeText(getApplicationContext(), confirmResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                if(confirmResponse != null){
                                    Toast.makeText(getApplicationContext(), confirmResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.v("Alumni Otp response ",confirmResponse.toString());
                                }

                            }

                        }

                        @Override
                        public void onFailure(Call<AlumniConfirmResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "SignUp Unsuccessful", Toast.LENGTH_SHORT).show();
                            Log.v("SignUp Failed : ", t.toString());
                        }
                    });
                }

            }
        });
    }
}
