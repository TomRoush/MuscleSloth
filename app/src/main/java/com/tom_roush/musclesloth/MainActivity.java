package com.tom_roush.musclesloth;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Resources for the given activity
		Resources res = getResources();

		// Update image
		ImageView im = (ImageView) findViewById(R.id.front_image);
		im.setImageResource(R.drawable.sloth_logo);
	}

	public void startWorkActivity(View v)
	{
//		Toast.makeText(this, "Workout", Toast.LENGTH_LONG).show();
		startActivity(new Intent(this, SuggestedTimesActivity.class));
	}

	public void startArcActivity(View v)
	{
		startActivity(new Intent(this, SchedulingFinishedActivity.class));
//		Toast.makeText(this, "Arc", Toast.LENGTH_LONG).show();
	}
}
