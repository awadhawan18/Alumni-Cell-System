package com.example.alumnicellsystem;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alumnicellsystem.Responses.AddEventResponse;
import com.example.alumnicellsystem.Utils.Utility;

import java.util.Calendar;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEvents extends AppCompatActivity {

    private EditText titleET, dateET, venueET, startTimeET, endTimeET, descET;
    private Button add;
    private String title, date, startTime, endTime, desc, venue;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);

        setTitle("Add Events");

        titleET = findViewById(R.id.titleET);
        dateET = findViewById(R.id.dateET);
        venueET = findViewById(R.id.venueET);
        descET = findViewById(R.id.descriptionET);
        startTimeET = findViewById(R.id.startTimeET);
        endTimeET = findViewById(R.id.endTimeET);
        add = findViewById(R.id.addEventBtn);
        progressBar = findViewById(R.id.progress_bar);

        dateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(1);
            }
        });

        startTimeET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(2);
            }
        });

        endTimeET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(3);
            }
        });




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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = titleET.getText().toString();
                date = dateET.getText().toString();
                venue = venueET.getText().toString();
                desc = descET.getText().toString();
                startTime = startTimeET.getText().toString();
                endTime = endTimeET.getText().toString();


                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(date) || TextUtils.isEmpty(venue) || TextUtils.isEmpty(desc)
                        || TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    String eventDateTime = Utility.getEventTime(date, startTime, endTime);
                    Call<AddEventResponse> call = service.addEventRequest(title, venue, eventDateTime, desc);
                    call.enqueue(new Callback<AddEventResponse>() {
                        @Override
                        public void onResponse(Call<AddEventResponse> call, Response<AddEventResponse> response) {
                            AddEventResponse addEventResponse = response.body();

                            if (addEventResponse != null && Utility.isStatusOk(addEventResponse.getStatus())) {
                                Log.v("Add Events response ", addEventResponse.toString());
                                Toast.makeText(getApplicationContext(), "Event added Successfully", Toast.LENGTH_SHORT).show();

                            } else if (addEventResponse != null) {
                                Log.v("Add Events response ", addEventResponse.toString());
                                Toast.makeText(getApplicationContext(), addEventResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            progressBar.setVisibility(View.INVISIBLE);

                        }

                        @Override
                        public void onFailure(Call<AddEventResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Request Unsuccessful, server error", Toast.LENGTH_SHORT).show();
                            Log.v("request failed", t.toString());
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }


            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        final Calendar c = Calendar.getInstance();

        switch (id){

            case 1: return new DatePickerDialog(this, myDateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            case 2: return new TimePickerDialog(this, myStartTimeSetListener, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);
            case 3: return new TimePickerDialog(this, myEndTimeSetListener, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);
        }

        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            StringBuilder ss = new StringBuilder();
            ss.append(dayOfMonth);
            ss.append("-");
            ss.append(month + 1);
            ss.append("-");
            ss.append(year);

            dateET.setText(ss);
        }
    };

    private TimePickerDialog.OnTimeSetListener myStartTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            StringBuilder ss = new StringBuilder();
            ss.append(hourOfDay);
            ss.append(":");
            ss.append(minute);

            startTimeET.setText(ss);
        }
    };

    private TimePickerDialog.OnTimeSetListener myEndTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            StringBuilder ss = new StringBuilder();
            ss.append(hourOfDay);
            ss.append(":");
            ss.append(minute);

            endTimeET.setText(ss);
        }
    };
}
