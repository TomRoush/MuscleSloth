package com.tom_roush.musclesloth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SchedulingFinishedActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scheduling_finished);
	}

	public void returnToFront(View v) {
		startActivity(new Intent(this, MainActivity.class));
	}
}
