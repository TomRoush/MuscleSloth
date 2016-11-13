package com.tom_roush.musclesloth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class WorkoutWorkflowActivity extends AppCompatActivity {

    private final String _WORKOUTS = "WORKOUT";
    private ArrayList<Workout> _workouts;
    private ListView _listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_workflow);

        // Edit Actionbar name
        getSupportActionBar().setTitle(R.string.manage_work);

        // get the list view item for binding purposes
        _listview = (ListView) findViewById(R.id.listview);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Get the needed data
        SharedPreferences sharedPref = WorkoutWorkflowActivity.this.getPreferences(Context.MODE_PRIVATE);
        loadSavedWorkout(sharedPref);
        Toast.makeText(this, _workouts.get(0).toString(), Toast.LENGTH_LONG).show();
        //_workouts.add(new Workout("banana"));

        // Connect the ListView with the array list
        ArrayAdapter<Workout> workoutAdapter = new ArrayAdapter<Workout>(
                this,
                R.layout.list_view_elem,
                R.id.listview_elem,
                _workouts);

        _listview.setAdapter(workoutAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPref = WorkoutWorkflowActivity.this.getPreferences(Context.MODE_PRIVATE);
        saveSavedWorkout(sharedPref);
    }

    /**
     * Loads the stored workouts on the phone.
     * If the workouts don't exist, we simply create an empty workout list
     * @param sharedPref The needed preferences where the data is stored
     */
    private void loadSavedWorkout(SharedPreferences sharedPref)
    {
        // set a default value
        _workouts = new ArrayList<Workout>();

        // perform check if the preferences exist
        if(sharedPref.contains(_WORKOUTS))
        {
            // If something is found, we need to push the saved data into the workouts global
            String workoutsJSON = sharedPref.getString(_WORKOUTS, "");
            ArrayList<Workout> tempWorkouts = (new Gson()).fromJson(workoutsJSON,
                    new TypeToken<ArrayList<Workout>>(){}.getType());

            // check if it is null
            if(tempWorkouts != null) {_workouts = tempWorkouts;}
        }
    }

    /**
     * Save the workout to the _WORKOUT key
     * @param sharedPref The needed preferences where the data is stored
     */
    private void saveSavedWorkout(SharedPreferences sharedPref)
    {
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        String workoutsJSON = (new Gson()).toJson(_workouts);
        prefsEditor.putString(_WORKOUTS, workoutsJSON);
        prefsEditor.apply();
    }
}
