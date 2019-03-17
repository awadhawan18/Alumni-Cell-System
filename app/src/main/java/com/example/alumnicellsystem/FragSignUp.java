package com.example.alumnicellsystem;

import android.app.Activity;
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
import android.widget.Spinner;
import android.widget.TextView;


public class FragSignUp extends Fragment {

    private TextView login;
    private EditText nameET, emailET, contactET;
    private Spinner designationSp, departmentSp;
    private String name, email, designation, department, contact;
    private Button signUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_sign_up, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        login = getActivity().findViewById(R.id.loginTxt);
        nameET = getActivity().findViewById(R.id.nameET);
        emailET = getActivity().findViewById(R.id.emailET);
        contactET = getActivity().findViewById(R.id.contactET);
        designationSp = getActivity().findViewById(R.id.designation);
        departmentSp = getActivity().findViewById(R.id.department);
        signUp = getActivity().findViewById(R.id.signUpBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.loginFrame, new FragLogin());
                ft.commit();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameET.getText().toString();
                email = emailET.getText().toString();
                contact = contactET.getText().toString();
                designation = designationSp.getSelectedItem().toString();
                department = departmentSp.getSelectedItem().toString();

                //TODO: Enter the signUp logic here.

                startActivity(new Intent(getActivity(), EnterOTP.class));
                getActivity().finish();
            }
        });
    }

}
