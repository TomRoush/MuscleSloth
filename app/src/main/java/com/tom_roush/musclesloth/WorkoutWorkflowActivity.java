package com.tom_roush.musclesloth;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class WorkoutWorkflowActivity extends Activity {

    private final String _WORKOUTS = "WORKOUT";
    private ArrayList<Workout> _workouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_workflow);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPref = WorkoutWorkflowActivity.this.getPreferences(Context.MODE_PRIVATE);
        loadSavedWorkout(sharedPref);
        Toast.makeText(this, Integer.toString(_workouts.size()), Toast.LENGTH_LONG).show();
        _workouts.add(new Workout("banana"));
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
            Gson gson = new Gson();
            String workoutsJSON = sharedPref.getString(_WORKOUTS, "");
            ArrayList<Workout> tempWorkouts = gson.fromJson(workoutsJSON,_workouts.getClass());

            // check if it is null
            if(tempWorkouts != null)
            {
                _workouts = tempWorkouts;
            }
            Toast.makeText(this, _workouts.getClass().toString(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Save the workout to the _WORKOUT key
     * @param sharedPref The needed preferences where the data is stored
     */
    private void saveSavedWorkout(SharedPreferences sharedPref)
    {
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        Gson gson = new Gson();
        String workoutsJSON = gson.toJson(_workouts);
        prefsEditor.putString(_WORKOUTS, workoutsJSON);
        prefsEditor.apply();
    }
}
