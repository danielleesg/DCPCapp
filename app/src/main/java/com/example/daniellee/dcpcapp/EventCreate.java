package com.example.daniellee.dcpcapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.DialogFragment;
import android.view.View;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import android.widget.TimePicker;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.widget.EditText;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventCreate extends AppCompatActivity {

    private String fieldIsNotEmpty(EditText editText) {
        if (editText.getText().length() == 0) {
            editText.setError("Field cannot be empty");
            return null;
        }
        return String.valueOf(editText.getText());
    }

    private Handler mHandler = new Handler();


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

                String title = String.valueOf((
                        (EditText) findViewById(R.id.eventTitle)
                ).getText());

                // fill this stuff in using the pattern from the title above

                String location = String.valueOf((
                        (EditText) findViewById(R.id.eventLocation)
                ).getText());

                String date = String.valueOf((
                        (EditText) findViewById(R.id.eventDate)
                ).getText());

                String time = String.valueOf((
                        (EditText) findViewById(R.id.eventTime)
                ).getText());

                String host = String.valueOf((
                        (EditText) findViewById(R.id.eventHost)
                ).getText());

                String street = String.valueOf((
                        (EditText) findViewById(R.id.eventStreetAddress)
                ).getText());

                String suburb = String.valueOf((
                        (EditText) findViewById(R.id.eventSuburb)
                ).getText());

                String city = String.valueOf((
                        (EditText) findViewById(R.id.eventCity)
                ).getText());

                if (title == null || street == null || host == null ||
                        city == null || date == null || time == null ||
                        location == null || suburb == null) {
                // one of the fields has not been entered
                // so stop processing the onClickEvent
                    return;
                }

                Log.i("CreateEvent", title + " "
                        + location + " "
                        + date + " "
                        + time + " "
                        + host + " "
                        + street + " "
                        + suburb + " "
                        + city);


                String datetime = "";
                try {
                    // join the date and time from the user’s input into one
                    // line and transform it into a Date object
                    Date d = new SimpleDateFormat("dd/MM/yyyy hh:mm")
                            .parse(date + " " + time);
                    // convert it to ISO8601 format
                    TimeZone tz = TimeZone.getTimeZone("UTC");
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
                    df.setTimeZone(tz);
                    // generate a string version to send to the API
                    datetime = df.format(d);

                    Event e = new Event(title, location,
                            datetime, host,
                            street, suburb, city);


//                    e = new Event("title", "location", datetime, "host", "street", "suburb", "city");

                    Log.i("Event", e.toString());

                    StrictMode.ThreadPolicy policy =
                            new StrictMode.ThreadPolicy.Builder()
                                    .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String adminToken = "fbb24ba2589e13f5f9728f6210efb44dc69dacb6";
                    EventService service = EventServiceGenerator
                            .createService(EventService.class, adminToken);
                    Call<Event> addEvent = service.addEvent(e);
                    //addEvent.execute();
                    addEvent.enqueue(new Callback<Event>() {
                        @Override
                        public void onResponse(Call<Event> call, Response<Event> response) {
                            final Response<Event> r = response;
                            mHandler.post(new Runnable() {
                                public void run(){
                                    AlertDialog.Builder builder =
                                            new AlertDialog.Builder(EventCreate.this);
                                    builder.setMessage("Success!");

                                    if (r.isSuccessful()) {
                                        builder.setMessage("Success!");
                                    } else {
                                        builder.setMessage(r.message());
                                    }
                                    builder.show();
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<Event> call, Throwable t) {
                            final Throwable throwable = t;
                            mHandler.post(new Runnable() {
                                public void run(){
                                    AlertDialog.Builder builder =
                                            new AlertDialog.Builder(EventCreate.this);
                                    builder.setMessage("Failure: " +
                                            throwable.getLocalizedMessage());
                                    builder.show();
                                }
                            });

                        }
                    });
                } catch(ParseException e) {
                    e.printStackTrace();
                }
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