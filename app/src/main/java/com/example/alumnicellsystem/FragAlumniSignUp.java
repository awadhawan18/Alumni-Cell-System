package com.example.alumnicellsystem;

import android.content.Context;
import android.content.Intent;
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


public class FragAlumniSignUp extends Fragment {

    private EditText roll_noET, passwordET;
    private TextView login;
    private Button signUp;
    private String roll_no, password;

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

                roll_no = roll_noET.getText().toString();
                password = passwordET.getText().toString();

                //TODO: SignUP code comes here

                startActivity(new Intent(getActivity(), AlumniOTP.class));
                getActivity().finish();
            }
        });
    }
}
