package com.example.alumnicellsystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumnicellsystem.Responses.AlumniUpdateResponse;
import com.example.alumnicellsystem.Utils.Utility;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class AlumniProfileFrag extends Fragment {

    private EditText nameET, enrollmentET, branchET, companyET, emailET, contactET, addressET;
    private String name, email, contact, company, address, id;
    private Button update, logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_alumni_profile, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        nameET = getActivity().findViewById(R.id.nameET);
        enrollmentET = getActivity().findViewById(R.id.roll_noET);
        branchET = getActivity().findViewById(R.id.branchET);
        companyET = getActivity().findViewById(R.id.companyET);
        emailET = getActivity().findViewById(R.id.emailET);
        contactET = getActivity().findViewById(R.id.contactET);
        addressET = getActivity().findViewById(R.id.addressET);

        update = getActivity().findViewById(R.id.updateBtn);
        logout = getActivity().findViewById(R.id.logoutBtn);


        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();


        builder.addInterceptor(new AddCookiesInterceptor(getActivity().getApplicationContext())); // VERY VERY IMPORTANT
        builder.addInterceptor(new ReceivedCookiesInterceptor(getActivity().getApplicationContext())); // VERY VERY IMPORTANT
        client = builder.build();


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UrlService service = retrofit.create(UrlService.class);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailET.getText().toString();
                contact = contactET.getText().toString();
                company = companyET.getText().toString();
                address = addressET.getText().toString();

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Logging In...");
                progressDialog.setCancelable(false);

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(address) || TextUtils.isEmpty(contact) || TextUtils.isEmpty(company) ){
                    Toast.makeText(getActivity().getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!Utility.isValidEmail(email)){
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter valid email", Toast.LENGTH_LONG).show();
                }
                else if(!Utility.isValidPhoneNo(contact)){
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter valid mobile number", Toast.LENGTH_LONG).show();
                }
                else {

                    progressDialog.show();

                    Log.v("values ; ", id);
                    Call<AlumniUpdateResponse> call = service.updateAlumni(email, address, contact, company, id);
                    call.enqueue(new Callback<AlumniUpdateResponse>() {
                        @Override
                        public void onResponse(Call<AlumniUpdateResponse> call, Response<AlumniUpdateResponse> response) {
                            AlumniUpdateResponse alumniUpdateResponse = response.body();


                            if(alumniUpdateResponse != null && Utility.isStatusOk(alumniUpdateResponse.getStatus())){
                                Log.v("Update response ",alumniUpdateResponse.toString());
                                Toast.makeText(getActivity().getApplicationContext(), alumniUpdateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                MyPreferenceManager myPreferenceManager = new MyPreferenceManager(getActivity().getApplicationContext());
                                myPreferenceManager.updateAlumniPref(alumniUpdateResponse.getData().get(0));
                            }
                            else {
                                if(alumniUpdateResponse != null){
                                    Toast.makeText(getActivity().getApplicationContext(), alumniUpdateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.v("Update response ",alumniUpdateResponse.toString());
                                }

                                Toast.makeText(getActivity().getApplicationContext(), "Failed to update profile", Toast.LENGTH_SHORT).show();
                            }

                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<AlumniUpdateResponse> call, Throwable t) {
                            Toast.makeText(getActivity().getApplicationContext(), "Update Unsuccessful", Toast.LENGTH_SHORT).show();
                            Log.v("Update Failed : ", t.toString());
                        }
                    });
                }

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit();
                memes.remove("User_Value");
                memes.remove("userDetail");
                memes.commit();

                startActivity(new Intent(getActivity().getApplicationContext(), ChoiceActivity.class));
                getActivity().finish();
            }
        });

        SharedPreferences details = getActivity().getSharedPreferences("alumniDetails", MODE_PRIVATE);
        nameET.setText(details.getString("Name", null));
        enrollmentET.setText(details.getString("Enrollment", null));
        branchET.setText(details.getString("Department", null));
        companyET.setText(details.getString("Company", null));
        emailET.setText(details.getString("Email", null));
        contactET.setText(details.getString("Contact", null));
        addressET.setText(details.getString("Address", null));
        id = details.getString("id",null);
    }
}
