package com.example.alumnicellsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumnicellsystem.Responses.AlumniLoginResponse;
import com.example.alumnicellsystem.Utils.Utility;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragAlumniLogin extends Fragment {

    private TextView signUp;
    private EditText enrollmentNoET, passwordET;
    private Button login;
    private String enrollmentNo, password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_alumni_login, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle("Alumni Login");

        signUp = getActivity().findViewById(R.id.signUpTxt);
        enrollmentNoET = getActivity().findViewById(R.id.enrollment_no);
        passwordET = getActivity().findViewById(R.id.passwordET);
        login = getActivity().findViewById(R.id.loginBtn);



        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new AddCookiesInterceptor(getActivity())); // VERY VERY IMPORTANT
        builder.addInterceptor(new ReceivedCookiesInterceptor(getActivity())); // VERY VERY IMPORTANT
        client = builder.build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UrlService service = retrofit.create(UrlService.class);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Bug present here. Fragment nor=t replacing properly.

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.alumniLoginFrame, new FragAlumniSignUp());
                ft.commit();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enrollmentNo = enrollmentNoET.getText().toString();
                password = passwordET.getText().toString();

                if(TextUtils.isEmpty(enrollmentNo) || TextUtils.isEmpty(password)){
                    Toast.makeText(getActivity(), "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                /*else if(!Utility.isValidEmail(enrollmentNo)){
                    Toast.makeText(getActivity(), "Please enter valid enrollment No", Toast.LENGTH_SHORT).show();
                }*/
                else{

                    Call<AlumniLoginResponse> call = service.alumniLoginRequest(enrollmentNo, password);
                    call.enqueue(new Callback<AlumniLoginResponse>() {
                        @Override
                        public void onResponse(Call<AlumniLoginResponse> call, Response<AlumniLoginResponse> response) {
                            AlumniLoginResponse loginResponse = response.body();

                            if(loginResponse != null && Utility.isStatusOk(loginResponse.getStatus())){
                                Log.v("Login response ",loginResponse.toString());

                                Toast.makeText(getActivity(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                                SharedPreferences.Editor memes = android.preference.PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                                memes.putString("User_Value", "2").apply();
                                memes.commit();

                                com.example.alumnicellsystem.PreferenceManager preferenceManager =
                                        new com.example.alumnicellsystem.PreferenceManager (getActivity(), loginResponse);
                                preferenceManager.writeAlumniPref();

                                startActivity(new Intent(getActivity(), Dashboard.class));
                                getActivity().finish();
                            }
                            else {
                                if(loginResponse != null){
                                    Log.v("Login response ",loginResponse.toString());
                                    Toast.makeText(getActivity(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                                Toast.makeText(getActivity(), "Wrong enrollmentNo or password", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<AlumniLoginResponse> call, Throwable t) {
                            Toast.makeText(getActivity(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }
}
