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

import com.example.alumnicellsystem.Responses.AlumniSignupResponse;
import com.example.alumnicellsystem.Utils.Utility;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragAlumniSignUp extends Fragment {

    private EditText roll_noET, passwordET;
    private TextView login;
    private Button signUp;
    private String rollNo, password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_alumni_sign_up, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle("Register");

        roll_noET = getActivity().findViewById(R.id.roll_noET);
        passwordET = getActivity().findViewById(R.id.passwordET);
        signUp = getActivity().findViewById(R.id.signUpBtn);
        login = getActivity().findViewById(R.id.loginTxt);

        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UrlService service = retrofit.create(UrlService.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.alumniLoginFrame, new FragAlumniLogin());
                ft.commit();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rollNo = roll_noET.getText().toString();
                password = passwordET.getText().toString();

                if(TextUtils.isEmpty(rollNo) || TextUtils.isEmpty(password) ){
                    Toast.makeText(getActivity(), "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    Call<AlumniSignupResponse> call = service.alumniSignUpRequest(rollNo);
                    call.enqueue(new Callback<AlumniSignupResponse>() {
                        @Override
                        public void onResponse(Call<AlumniSignupResponse> call, Response<AlumniSignupResponse> response) {
                            AlumniSignupResponse signupResponse = response.body();


                            if(signupResponse != null && Utility.isStatusOk(signupResponse.getStatus())){
                                Log.v("Alumni SignUp response ",signupResponse.toString());
                                Toast.makeText(getActivity(), signupResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(),AlumniOTP.class);
                                intent.putExtra("rollNo", rollNo);
                                intent.putExtra("password",password);
                                startActivity(intent);
                                getActivity().finish();
                            }
                            else {
                                if(signupResponse != null){
                                    Toast.makeText(getActivity(), signupResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.v("Alumni SignUp response ",signupResponse.toString());
                                }

                                Toast.makeText(getActivity(), "Couldn't Signup", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<AlumniSignupResponse> call, Throwable t) {
                            Toast.makeText(getActivity(), "SignUp Unsuccessful", Toast.LENGTH_SHORT).show();
                            Log.v("SignUp Failed : ", t.toString());
                        }
                    });
                }

            }
        });
    }
}
