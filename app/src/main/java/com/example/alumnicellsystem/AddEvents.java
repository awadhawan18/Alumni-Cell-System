package com.example.alumnicellsystem;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddEvents extends AppCompatActivity {

    private EditText titleET, dateET, startTimeET, endTimeET, descET;
    private Button add;
    private String title, date, startTime, endTime, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);

        titleET = findViewById(R.id.titleET);
        dateET = findViewById(R.id.dateET);
        startTimeET = findViewById(R.id.startTimeET);
        endTimeET = findViewById(R.id.endTimeET);
        descET = findViewById(R.id.descriptionET);
        add = findViewById(R.id.addEventBtn);

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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = titleET.getText().toString();
                date = dateET.getText().toString();
                startTime = startTimeET.getText().toString();
                endTime = endTimeET.getText().toString();
                desc = descET.getText().toString();

                //TODO: API call comes here.
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
