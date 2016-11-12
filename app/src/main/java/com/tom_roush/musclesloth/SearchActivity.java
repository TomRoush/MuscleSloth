package com.tom_roush.musclesloth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tom_roush.musclesloth.R.id.editText;


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

        String[] machines = new String[]{"Flat Bench", "Incline Bench", "Decline Bench"};
        ArrayList<String> machinesList = new ArrayList<String>();
        machinesList.addAll(Arrays.asList(machines));

        listAdapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.simplerow, machinesList);

        listAdapter.add("Incline Bench");
        listAdapter.add("Decline Bench");

        mainListView.setAdapter(listAdapter);
    }
}