package com.tom_roush.musclesloth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
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
    private EditText _nameText;
    private Button _findTimesBtn;
    private TextView _titleView;
    private ViewSwitcher _vswitcher;
    private ViewSwitcher _vswitsearch;
    private ViewSwitcher _vswitlist;

    private boolean _currently_list_view;
    private boolean _workouts_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_workout);

        // get the list view item for binding purposes
        _listview = (ListView) findViewById(R.id.listview);
        _nameText = (EditText) findViewById(R.id.nameText);
        _findTimesBtn = (Button) findViewById(R.id.findTimesBtn);
        _titleView = (TextView) findViewById(R.id.titleBar);
        _vswitcher = (ViewSwitcher) findViewById(R.id.vswitcher);
        _vswitsearch = (ViewSwitcher) findViewById(R.id.vswitsearch);
        _vswitlist = (ViewSwitcher) findViewById(R.id.vswitlist);

        _currently_list_view = true;    // default is the list view
        _workouts_view = true;          // default is the manageing workouts
        //setSupportActionBar(_toolbar);
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
        _workouts_view = false;
        workout_list_callback();

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
        _vswitsearch.showNext();
    }

    private void updateViewToManage()
    {
        Log.d("banana","ananus");
        _workouts_view = true;
        workout_list_callback();

        if(_workout != null) updateWorkout(_workout);
        _workout = null;
        // set up the onclick listener
        _listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId())
                {
                    case R.id.listview_elem:
                        Log.d("banana","melon");
                    case R.id.imageButton:
                        Log.d("banana","peanut");
                }
                TextView tv = (TextView) view.findViewById(R.id.listview_elem);
                ImageButton ib = (ImageButton) view.findViewById(R.id.imageButton);

                ib.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("banana","apple");
                            }
                        }
                );
                _workout = (Workout)_listview.getItemAtPosition(position);
                Log.d("banana",_workout.toString());
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
        _vswitsearch.showNext();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Get the needed data
        SharedPreferences sharedPref = WorkoutWorkflowActivity.this.getPreferences(Context.MODE_PRIVATE);
        loadSavedWorkout(sharedPref);

        //_workouts.add(new Workout("banana"));

        // Start with manage view
        if(_workout == null) updateViewToManage();
        else updateViewToWorkout();

        _vswitcher.showNext();
        _vswitsearch.showNext();
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

            // run the callback in order to determine if we need to choose the textview or the listview
            workout_list_callback();    // default view is the listview
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
        if(_workout == null) {
            _workout = new Workout("New Workout", _workouts.size());
            //_workouts.add(_workout);
            arrListAdd(_workout);
            updateViewToWorkout();
        }
    }

    /**
     * Takes us to the new mahcine page.
     * @param w the view that triggers the thing
     */
    public void createNewMachine(View w)
    {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    private void updateWorkout(Workout updatedWorkout)
    {
        _workout.setName(_nameText.getText().toString());
        _workouts.set(updatedWorkout.getIndex(), updatedWorkout);
    }

	public void findTimeOnClick(View v) {
		Intent intent = new Intent(getApplicationContext(), SchedulingPrefsActivity.class);
		startActivity(intent);
	}

    /**
     * Switches the textView and listview
     */
    private void workout_list_callback()
    {
        if ((_workouts_view && ((_currently_list_view && _workouts.isEmpty()) ||
            (!_currently_list_view && !(_workouts.isEmpty())))) ||
            (!_workouts_view && ((_currently_list_view && _workout.getMachines().isEmpty()) ||
            !_currently_list_view && !_workout.getMachines().isEmpty()) ))
        {
            _vswitlist.showNext();
            _currently_list_view = !_currently_list_view;
        }
    }

    /////////////////////////////////////////////////////////////////
    //////////////////////ARRAYLISTSTUFF/////////////////////////////
    /////////////////////////////////////////////////////////////////
    private void arrListAdd(Workout w) {workout_list_callback(); _workouts.add(w);}
}
