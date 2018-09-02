package com.example.daniellee.dcpcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    public static ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
        );

        ArrayList<Event> events = getAndShowEventList();

        // this is really bad to do, but for the time
        // being (for debug purposes) we'll do this.
        // TODO remove this policy
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String authToken =
                "fbb24ba2589e13f5f9728f6210efb44dc69dacb6";
        EventService service =
                EventServiceGenerator.createService(
                        EventService.class, authToken);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String fullname = preferences.getString("fullname", "NO NAME");
        Log.d("DCBC EVENTS", fullname);

        // other lines are as before
        /*
         * I’m creating some dummy data so I have something to display.
         * I think you’ll all have some form of this event class — so construct it as necessary
         */

        events.add(new Event("Foo", "Here", "Tomorrow"));
        events.add(new Event("Bar", "There", "Next Week"));
        events.add(new Event("Baz", "Everywhere", "Next Month"));

        /*
         * We’ll be creating this EventAdapter in the next step
         */



    }

    @NonNull
    private ArrayList<Event> getAndShowEventList() {
        ArrayList<Event> events = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            events.add(new Event("Foo" + i, "Here", "Tomorrow"));
            events.add(new Event("Bar" + i, "There", "Next Week"));
            events.add(new Event("Baz" + i, "??", "Next Month"));
        }
        EventAdapter adapter = new EventAdapter(this, events);
        ListView listView = findViewById(R.id.list_view1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a,
                                    View v, int position, long id) {
                Event event = (Event)a.getItemAtPosition(position);
                Intent intent = new Intent(v.getContext(),
                        DetailsActivity.class);
                intent.putExtra("nz.net.crane.dcbc_events.Event",
                        event);
                startActivity(intent);
            }
        });
        return events;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent =
                    new Intent(this,
                            Settings.class);
            startActivity(settingsIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
