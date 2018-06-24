package com.example.daniellee.dcpcapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Event event = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);
        }

        // TODO figure out how to display a single item in the list
        // you’re going to fill this part in shortly

        // here’s an example to get you started...
//        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
//        tvTitle.setText(event.getTitle());

        // Return the completed view to render on screen
        return convertView;
    }
}
