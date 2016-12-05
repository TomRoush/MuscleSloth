package com.tom_roush.musclesloth;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class MachinePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_page);

	    ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.gym_one);
    }
}