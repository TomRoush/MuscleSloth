package com.tom_roush.musclesloth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class SearchActivity extends AppCompatActivity {

    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String[] free = new String[]{"Flat Bench 1", "Flat Bench 2", "Incline Bench 1", "Incline Bench 2", "Decline Bench 1", "Decline Bench 2"};
        ArrayList<String> freeList = new ArrayList<>();
        freeList.addAll(Arrays.asList(free));

        String[] machines = new String[]{"Hip Abductor", "Assisted Pull-ups", "Cable Tower", "Leg Curl", "Leg Press", "Chest Flies"};
        ArrayList<String> machinesList = new ArrayList<>();
        machinesList.addAll(Arrays.asList(machines));

        String[] cardio = new String[]{"Treadmill", "Elliptical", "Stair Stepper", "Stationary Bike", "Incline Treadmill", "Free-Move Elliptical"};
        ArrayList<String> cardioList = new ArrayList<>();
        cardioList.addAll(Arrays.asList(cardio));

        listAdapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.simplerow, freeList);
        mainListView = (ListView) findViewById(R.id.freeListView);
        mainListView.setAdapter(listAdapter);

        listAdapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.simplerow, machinesList);
        mainListView = (ListView) findViewById(R.id.machineListView);
        mainListView.setAdapter(listAdapter);

        listAdapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.simplerow, cardioList);
        mainListView = (ListView) findViewById(R.id.cardioListView);
        mainListView.setAdapter(listAdapter);
    }

}
