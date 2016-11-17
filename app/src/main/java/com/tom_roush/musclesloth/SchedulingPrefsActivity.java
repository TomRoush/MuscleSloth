package com.tom_roush.musclesloth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SchedulingPrefsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scheduling_prefs);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), SuggestedTimesActivity.class);
				DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker);
				Calendar time = GregorianCalendar.getInstance();
				time.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
				intent.putExtra("pickedDate", time.getTimeInMillis());
				startActivity(intent);
			}
		});
	}

}
