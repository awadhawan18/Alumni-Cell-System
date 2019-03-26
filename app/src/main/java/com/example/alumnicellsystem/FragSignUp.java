package com.example.alumnicellsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumnicellsystem.Constants.DesignationValues;
import com.example.alumnicellsystem.Constants.UserFields;
import com.example.alumnicellsystem.Responses.SignupResponse;
import com.example.alumnicellsystem.Responses.SignupResponse;
import com.example.alumnicellsystem.Utils.Utility;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragSignUp extends Fragment {

    private TextView login;
    private EditText nameET, emailET, contactET, passwordET;
    private Spinner designationSp, departmentSp;
    private String name, email, designation, department, contact, password;
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
        passwordET = getActivity().findViewById(R.id.passwordET);
        contactET = getActivity().findViewById(R.id.contactET);
        designationSp = getActivity().findViewById(R.id.designation);
        departmentSp = getActivity().findViewById(R.id.department);
        signUp = getActivity().findViewById(R.id.signUpBtn);

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
                ft.replace(R.id.loginFrame, new FragLogin());
                ft.commit();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameET.getText().toString();
                email = emailET.getText().toString();
                password = passwordET.getText().toString();
                contact = contactET.getText().toString();
                designation = designationSp.getSelectedItem().toString();
                department = departmentSp.getSelectedItem().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(contact)
                        || TextUtils.isEmpty(designation) || TextUtils.isEmpty(department) || TextUtils.isEmpty(name)){
                    Toast.makeText(getActivity(), "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!Utility.isValidEmail(email)){
                    Toast.makeText(getActivity(), "Please enter valid email", Toast.LENGTH_LONG).show();
                }
                else if(!Utility.isValidPhoneNo(contact)){
                    Toast.makeText(getActivity(), "Please enter valid mobile number", Toast.LENGTH_LONG).show();
                }
                else {

                    int designationValue = Utility.getDesignationValue(designation);

                    Call<SignupResponse> call = service.signupRequest(email, password, name, designationValue, department, contact);
                    call.enqueue(new Callback<SignupResponse>() {
                        @Override
                        public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                            SignupResponse signupResponse = response.body();


                            if(signupResponse != null && Utility.isStatusOk(signupResponse.getStatus())){
                                Log.v("SignUp response ",signupResponse.toString());
                                Toast.makeText(getActivity(), signupResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(),EnterOTP.class);
                                intent.putExtra(UserFields.EMAIL, email);
                                startActivity(intent);
                                getActivity().finish();
                            }
                            else {
                                if(signupResponse != null){
                                    Log.v("SignUp response ",signupResponse.toString());
                                }

                                Toast.makeText(getActivity(), "Failed to make account", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<SignupResponse> call, Throwable t) {
                            Toast.makeText(getActivity(), "SignUp Unsuccessful", Toast.LENGTH_SHORT).show();
                            Log.v("SignUp Failed : ", t.toString());
                        }
                    });
                }

            }
        });
    }

}
