package com.example.alumnicellsystem;

import android.content.Intent;
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

import com.example.alumnicellsystem.Responses.LoginResponse;
import com.example.alumnicellsystem.Utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragLogin extends Fragment {

    private TextView signUp;
    private EditText emailET, passwordET;
    private Button login;
    private String email, password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_login, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        signUp = getActivity().findViewById(R.id.signUpTxt);
        emailET = getActivity().findViewById(R.id.emailET);
        passwordET = getActivity().findViewById(R.id.passwordET);
        login = getActivity().findViewById(R.id.loginBtn);


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UrlService service = retrofit.create(UrlService.class);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.loginFrame, new FragSignUp());
                ft.commit();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailET.getText().toString();
                password = passwordET.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(getActivity(), "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!Utility.isValidEmail(email)){
                    Toast.makeText(getActivity(), "Please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else{

                    Call<LoginResponse> call = service.loginRequest(email, password);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            LoginResponse loginResponse = response.body();


                            if(loginResponse != null && Utility.isStatusOk(loginResponse.getStatus())){
                                Log.v("Login response ",loginResponse.toString());
                                startActivity(new Intent(getActivity(), Dashboard.class));
                                getActivity().finish();
                            }
                            else {
                                if(loginResponse != null){
                                    Log.v("Login response ",loginResponse.toString());
                                }

                                Toast.makeText(getActivity(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(getActivity(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



            }
        });
    }
}
