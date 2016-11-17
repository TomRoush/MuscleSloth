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
        mainListView = (ListView) findViewById(R.id.freeListView);

        String[] machines = new String[]{"Flat Bench 1", "Flat Bench 2", "Incline Bench 1", "Incline Bench 2", "Decline Bench 1", "Decline Bench 2"};
        ArrayList<String> machinesList = new ArrayList<>();
        machinesList.addAll(Arrays.asList(machines));

        listAdapter = new ArrayAdapter<>(SearchActivity.this, R.layout.simplerow, machinesList);

        mainListView.setAdapter(listAdapter);
    }
}