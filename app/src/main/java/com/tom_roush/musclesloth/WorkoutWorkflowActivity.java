package com.tom_roush.musclesloth;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;

public class WorkoutWorkflowActivity extends Activity {

    private final String _WORKOUTS = "WORKOUT";
    private ArrayList<Workout> _workouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_workflow);

        loadSavedWorkout();
    }

    /**
     * Loads the stored workouts on the phone
     */
    public void loadSavedWorkout()
    {
        // perform check if the preferences exist
        Gson gson = new Gson();

    }
}
