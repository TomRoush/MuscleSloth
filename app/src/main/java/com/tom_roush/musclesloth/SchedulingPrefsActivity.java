package com.tom_roush.musclesloth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import io.apptik.widget.MultiSlider;

public class SchedulingPrefsActivity extends AppCompatActivity {
	TextView timeRangeTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scheduling_prefs);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		MultiSlider dataRangeSlider = (MultiSlider)findViewById(R.id.date_range_slider);
		dataRangeSlider.setMax(1440);
		dataRangeSlider.getThumb(0).setValue(600); // Doesn't work
		dataRangeSlider.getThumb(1).setValue(1200);
		timeRangeTextView = (TextView)findViewById(R.id.time_title);
		dataRangeSlider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
			@Override
			public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
				if(thumbIndex == 0) {
					timeRangeTextView.setText("TIME RANGE: " + value / 60 + ":" + String.format("%02d", value % 60) + " - " + multiSlider.getThumb(1).getValue() / 60 + ":" + String.format("%02d", multiSlider.getThumb(1).getValue() % 60));
				} else {
]]
					timeRangeTextView.setText("TIME RANGE: " + multiSlider.getThumb(0).getValue() / 60 + ":" + String.format("%02d", multiSlider.getThumb(0).getValue() % 60) + " - " + value / 60 + ":" + String.format("%02d", value % 60));
				}
			}
		});

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
