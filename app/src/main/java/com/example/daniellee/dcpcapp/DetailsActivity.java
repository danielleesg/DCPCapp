package com.example.daniellee.dcpcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Event event = getIntent().getParcelableExtra("com.example.daniellee.dcpcapp.Event");
        Log.i("TAG", "received " + event);

    }
}
