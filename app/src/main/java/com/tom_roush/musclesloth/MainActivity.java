package com.tom_roush.musclesloth;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Resources res = getResources();

		ImageView im = (ImageView) findViewById(R.id.front_image);

		im.setImageResource(R.drawable.sloth_logo);

	}
}
