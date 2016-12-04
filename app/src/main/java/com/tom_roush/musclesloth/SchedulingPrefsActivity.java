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

		final MultiSlider multiSlider = (MultiSlider)findViewById(R.id.date_range_slider);
		multiSlider.setMax(1440);
		multiSlider.getThumb(0).setValue(600); // Doesn't work
		multiSlider.getThumb(1).setValue(1200);
		multiSlider.setStepsThumbsApart(90); // workout length
		timeRangeTextView = (TextView)findViewById(R.id.time_title);
		multiSlider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
			@Override
			public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
				if(thumbIndex == 0) {
					timeRangeTextView.setText("TIME RANGE: " + value / 60 + ":" + String.format("%02d", value % 60) + " - " + multiSlider.getThumb(1).getValue() / 60 + ":" + String.format("%02d", multiSlider.getThumb(1).getValue() % 60));
				} else {
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
				intent.putExtra("startHour", multiSlider.getThumb(0).getValue() / 60);
				intent.putExtra("startMin", multiSlider.getThumb(0).getValue() % 60);
				intent.putExtra("endHour", multiSlider.getThumb(1).getValue() / 60);
				intent.putExtra("endMin", multiSlider.getThumb(1).getValue() % 60);
				startActivity(intent);
			}
		});
	}
}
