package com.example.alumnicellsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.ResultReceiver;
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
import com.example.alumnicellsystem.Responses.AlumniUpdateResponse;
import com.example.alumnicellsystem.Utils.Utility;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlumniProfile extends AppCompatActivity {

    private EditText nameET, enrollmentET, branchET, companyET, emailET, contactET, addressET;
    private String name, email, contact, company, address, id;
    private Button update, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_profile);

        nameET = findViewById(R.id.nameET);
        enrollmentET = findViewById(R.id.roll_noET);
        branchET = findViewById(R.id.branchET);
        companyET = findViewById(R.id.companyET);
        emailET = findViewById(R.id.emailET);
        contactET = findViewById(R.id.contactET);
        addressET = findViewById(R.id.addressET);

        update = findViewById(R.id.updateBtn);
        logout = findViewById(R.id.logoutBtn);


        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();


        builder.addInterceptor(new AddCookiesInterceptor(getApplicationContext())); // VERY VERY IMPORTANT
        builder.addInterceptor(new ReceivedCookiesInterceptor(getApplicationContext())); // VERY VERY IMPORTANT
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

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(address) || TextUtils.isEmpty(contact) || TextUtils.isEmpty(company) ){
                    Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!Utility.isValidEmail(email)){
                    Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_LONG).show();
                }
                else if(!Utility.isValidPhoneNo(contact)){
                    Toast.makeText(getApplicationContext(), "Please enter valid mobile number", Toast.LENGTH_LONG).show();
                }
                else {

                    Log.v("values ; ", id);
                    Call<AlumniUpdateResponse> call = service.updateAlumni(email, address, contact, company, id);
                    call.enqueue(new Callback<AlumniUpdateResponse>() {
                        @Override
                        public void onResponse(Call<AlumniUpdateResponse> call, Response<AlumniUpdateResponse> response) {
                            AlumniUpdateResponse alumniUpdateResponse = response.body();


                            if(alumniUpdateResponse != null && Utility.isStatusOk(alumniUpdateResponse.getStatus())){
                                Log.v("Update response ",alumniUpdateResponse.toString());
                                Toast.makeText(getApplicationContext(), alumniUpdateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                MyPreferenceManager myPreferenceManager = new MyPreferenceManager(getApplicationContext());
                                myPreferenceManager.updateAlumniPref(alumniUpdateResponse.getData().get(0));
                            }
                            else {
                                if(alumniUpdateResponse != null){
                                    Toast.makeText(getApplicationContext(), alumniUpdateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.v("Update response ",alumniUpdateResponse.toString());
                                }

                                Toast.makeText(getApplicationContext(), "Failed to update profile", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<AlumniUpdateResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Update Unsuccessful", Toast.LENGTH_SHORT).show();
                            Log.v("Update Failed : ", t.toString());
                        }
                    });
                }

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                memes.remove("User_Value");
                memes.remove("userDetail");
                memes.commit();

                startActivity(new Intent(getApplicationContext(), ChoiceActivity.class));
                AlumniProfile.this.finish();
                ((ResultReceiver)getIntent().getParcelableExtra("finisher")).send(1, new Bundle());
            }
        });

        SharedPreferences details = getSharedPreferences("alumniDetails", MODE_PRIVATE);
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
