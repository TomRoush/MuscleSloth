package com.tom_roush.musclesloth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class RoomPageActivity extends AppCompatActivity {
    private ListView ScheduleListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_page);

        ScheduleListView = (ListView) findViewById(R.id.freeListView);

        String[] Schedule = new String[]{"Mixed Martial Arts 7:00 PM-8:00 PM", "Judo Club 8:00 PM-9:00 PM", "Taek-Won-Do 9:00 PM-10:00 PM"};
        ArrayList<String> scheduleList = new ArrayList<String>();
        scheduleList.addAll(Arrays.asList(Schedule));

        ListAdapter scheduleAdapter = new ArrayAdapter<String>(RoomPageActivity.this, R.layout.simplerow, scheduleList);

        ScheduleListView.setAdapter(scheduleAdapter);
    }
}
