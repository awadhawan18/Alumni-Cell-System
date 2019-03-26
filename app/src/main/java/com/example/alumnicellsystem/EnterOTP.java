package com.example.alumnicellsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alumnicellsystem.Constants.UserFields;
import com.example.alumnicellsystem.Responses.SignupResponse;
import com.example.alumnicellsystem.Utils.Utility;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnterOTP extends AppCompatActivity {

    private Button verfiy;
    private EditText otpET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);

        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UrlService service = retrofit.create(UrlService.class);

        final String email = getIntent().getStringExtra(UserFields.EMAIL);
        otpET = findViewById(R.id.otp);
        verfiy = findViewById(R.id.verify);
        verfiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = otpET.getText().toString();
                if (TextUtils.isEmpty(otp)) {
                    Toast.makeText(getApplicationContext(), "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {

                    Call<SignupResponse> call = service.otpRequest(email, otp);
                    call.enqueue(new Callback<SignupResponse>() {
                        @Override
                        public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                            SignupResponse otpResponse = response.body();


                            if (otpResponse != null && Utility.isOtpStatusOk(otpResponse.getStatus())) {
                                Log.v("OTP response ", otpResponse.toString());

                                SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                                memes.putString("USER_EMAIL", email).apply();
                                memes.commit();

                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(intent);
                                finish();
                            } else {

                                if (otpResponse != null) {
                                    Log.v("OTP response ", otpResponse.toString());
                                }

                                Toast.makeText(getApplicationContext(), "Wrong OTP", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<SignupResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "OTP Authentication Unsuccessful", Toast.LENGTH_SHORT).show();
                            Log.v("OTP Auth Failed : ", t.toString());
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }
                    });
                }
            }
        });
    }
}
