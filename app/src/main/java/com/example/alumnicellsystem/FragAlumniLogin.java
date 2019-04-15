package com.example.alumnicellsystem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FragAlumniLogin extends Fragment {

    private TextView signUp;
    private EditText emailET, passwordET;
    private Button login;
    private String email, password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_alumni_login, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle("Login");

        signUp = getActivity().findViewById(R.id.signUpTxt);
        emailET = getActivity().findViewById(R.id.emailET);
        passwordET = getActivity().findViewById(R.id.passwordET);
        login = getActivity().findViewById(R.id.loginBtn);

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

                email = emailET.getText().toString();
                password = passwordET.getText().toString();

                //TODO: Login Code comes here
            }
        });

    }
}
