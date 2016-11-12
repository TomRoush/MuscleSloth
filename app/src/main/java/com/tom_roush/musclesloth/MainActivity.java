package com.tom_roush.musclesloth;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity {

    private Intent workflow;

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
		Toast.makeText(this, "Workout", Toast.LENGTH_LONG).show();
        workflow = new Intent(MainActivity.this, WorkoutWorkflowActivity.class);
        MainActivity.this.startActivity(workflow);
	}

	public void startArcActivity(View v)
	{
		Toast.makeText(this, "Arc", Toast.LENGTH_LONG).show();
	}
}
