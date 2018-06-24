package com.example.daniellee.dcpcapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Event event = (Event) a.getItemAtPosition(position); 				// get the item that was tapped

                Intent intent = new Intent(v.getContext(), EventDetailsActivity.class);	// CHANGE ME
                intent.putExtra("nz.net.crane.dcbc_events.Event", event); 			// CHANGE ME

                startActivity(intent); 										// start the activity passing the selected Event
            }
        });

        EventAdapter adapter = new EventAdapter(this, events);
        ListView listView = findViewById(R.id.list_view1);      // note this view ID is the one that was referred to in the XML layout above
        listView.setAdapter(adapter);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
