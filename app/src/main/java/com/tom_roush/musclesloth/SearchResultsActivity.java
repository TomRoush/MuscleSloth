package com.tom_roush.musclesloth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class SearchResultsActivity extends AppCompatActivity {

    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        String[] free = new String[]{"Flat Bench", "Incline Bench", "Decline Bench"};
        ArrayList<String> freeList = new ArrayList<>();
        freeList.addAll(Arrays.asList(free));

        listAdapter = new ArrayAdapter<String>(SearchResultsActivity.this, R.layout.simplerow, freeList);
        mainListView = (ListView) findViewById(R.id.freeListView);
        mainListView.setAdapter(listAdapter);
    }
}
