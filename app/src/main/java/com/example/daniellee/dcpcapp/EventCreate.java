package com.example.daniellee.dcpcapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.DialogFragment;
import android.view.View;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;

import java.util.Calendar;

import android.widget.TimePicker;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.widget.EditText;

import android.util.Log;


public class EventCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action",
                        Snackbar.LENGTH_LONG).setAction(
                        "Action", null).show();
            }
        });
    }

    public void showTimePickerDialog(View v) {

        Log.i("EventCreate", "In the show time picker dialog");

        DialogFragment newFragment = new TimePickerFragment();
//        newFragment.show(getFragmentManager(), "timePicker");
        newFragment.show(getFragmentManager(), "timePicker");

//        FragmentManager mgr = getSupportFragmentManager();
//        FragmentTransaction show = mgr.beginTransaction().show(newFragment);
//        show.commit();

//        newFragment.show(mgr, "timePicker");
    }


    @SuppressLint("ValidFragment")
    public class TimePickerFragment  extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(),
                    this, hour, minute, true);
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cal.set(Calendar.MINUTE, minute);
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm", Locale.US);
            ((EditText)findViewById(R.id.eventTime)).setText(
                    sdf.format(cal.getTime()));
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    @SuppressLint("ValidFragment")
    public class DatePickerFragment
            extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);
            return new DatePickerDialog(getActivity(), this,
                    year, month, day);
        }
        @Override
        public void onDateSet(DatePicker view,
                              int year, int month, int dayOfMonth) {
            SimpleDateFormat sdf = new SimpleDateFormat(
                    "dd/MM/yyyy", Locale.US);
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, dayOfMonth);
            ((EditText)findViewById(R.id.eventDate)).setText(
                    sdf.format(cal.getTime()));
        }
    }





} // this is the last brace in the class