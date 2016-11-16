package com.tom_roush.musclesloth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class EditWorkoutActivity extends AppCompatActivity {

    private Workout _workout;
    private ListView _listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_workout);

        // retrieve the input workout
        getInputWorkout();

        _listview = (ListView) findViewById(R.id.listview);

        // Connect the ListView with the array list
        ArrayAdapter<Machine> workoutAdapter = new ArrayAdapter<Machine>(
                this,
                R.layout.list_view_elem,
                R.id.listview_elem,
                _workout.getMachines());

        _listview.setAdapter(workoutAdapter);

        // set the layout title
        //getSupportActionBar().setCustomView(R.layout.actionbar_edit);
        //getSupportActionBar().setTitle(_workout.toString());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void getInputWorkout()
    {
        String jsonMyObject;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("workout");
            _workout = new Gson().fromJson(jsonMyObject,
                    new TypeToken<Workout>(){}.getType());
        }
        else
        {
            // ToDO: create new object
        }
    }
}
