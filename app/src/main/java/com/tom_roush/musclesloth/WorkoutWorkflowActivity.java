package com.tom_roush.musclesloth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class WorkoutWorkflowActivity extends AppCompatActivity {

    private final String _WORKOUTS = "WORKOUT";
    private ArrayList<Workout> _workouts;
    private ListView _listview;
    private Workout _workout;
    private Toolbar _toolbar;
    private EditText _nameText;
    private Button _findTimesBtn;
    private TextView _titleView;
    private ViewSwitcher _vswitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_workout);

        // get the list view item for binding purposes
        _listview = (ListView) findViewById(R.id.listview);
        _toolbar = (Toolbar) findViewById(R.id.toolbar);
        _nameText = (EditText) findViewById(R.id.nameText);
        _findTimesBtn = (Button) findViewById(R.id.findTimesBtn);
        _titleView = (TextView) findViewById(R.id.titleBar);
        _vswitcher = (ViewSwitcher) findViewById(R.id.vswitcher);

        setSupportActionBar(_toolbar);
        _vswitcher.showNext();
    }

    @Override
    public void onBackPressed() {
        if(_workout == null) {
            super.onBackPressed();
        } else {
           updateViewToManage();
        }
    }

    private void updateViewToWorkout()
    {
        // set up the onclick listener
        _listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // TODO : add code for Machines
            }
        });

        // Connect the ListView with the array list
        ArrayAdapter<Machine> workoutAdapter = new ArrayAdapter<Machine>(
                this,
                R.layout.list_view_elem,
                R.id.listview_elem,
                _workout.getMachines());

        _listview.setAdapter(workoutAdapter);

        _nameText.setText(_workout.toString());
        _nameText.setVisibility(View.VISIBLE);
        _nameText.setFocusable(false);
        _nameText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                _nameText.setFocusableInTouchMode(true);
                return false;
            }
        });
        View this_view = this.findViewById(R.id.edit_workout);
        this_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _nameText.setFocusable(false);
                _nameText.setFocusableInTouchMode(false);
            }});

        // Find times button
        _findTimesBtn.setVisibility(View.VISIBLE);
        _vswitcher.showNext();
    }

    private void updateViewToManage()
    {
        if(_workout != null) updateWorkout(_workout);
        _workout = null;
        // set up the onclick listener
        _listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                _workout = (Workout)_listview.getItemAtPosition(position);
                updateViewToWorkout();
            }
        });

        // Connect the ListView with the array list
        ArrayAdapter<Workout> workoutAdapter = new ArrayAdapter<Workout>(
                this,
                R.layout.list_view_elem,
                R.id.listview_elem,
                _workouts);

        _listview.setAdapter(workoutAdapter);

        // Title bar
        _nameText.setVisibility(View.INVISIBLE);
        getSupportActionBar().setTitle(R.string.manage_work);

        // Find times button
        _findTimesBtn.setVisibility(View.INVISIBLE);
        _vswitcher.showNext();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Get the needed data
        SharedPreferences sharedPref = WorkoutWorkflowActivity.this.getPreferences(Context.MODE_PRIVATE);
        loadSavedWorkout(sharedPref);

        //_workouts.add(new Workout("banana"));

        // Start with manage view
        updateViewToManage();

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

    /**
     * Simply creates a new workout
     * @param w the view that triggers that thing
     */
    public void createNewWorkout(View w)
    {
        _workout = new Workout("New Workout",_workouts.size());
        _workouts.add(_workout);
        updateViewToWorkout();
    }

    private void updateWorkout(Workout updatedWorkout)
    {
        _workout.setName(_nameText.getText().toString());
        _workouts.set(updatedWorkout.getIndex(), updatedWorkout);
    }
}
