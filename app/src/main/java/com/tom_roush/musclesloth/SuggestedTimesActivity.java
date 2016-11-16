package com.tom_roush.musclesloth;

import android.Manifest;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class SuggestedTimesActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suggested_times);
		final List times = new ArrayList<>();
		Random r = new Random();
		String[] actualValues = new String[4];
		SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a", Locale.US); // XXX Locale
		for(int i = 0; i < 4; i++) {
			Calendar time = GregorianCalendar.getInstance();
			time.add(Calendar.DAY_OF_WEEK, r.nextInt(7)); // TODO: limit by range
			time.set(Calendar.HOUR_OF_DAY, r.nextInt(24));
			time.set(Calendar.MINUTE, r.nextInt(4) * 15);
			time.set(Calendar.SECOND, 0);
			time.set(Calendar.MILLISECOND, 0);

			Calendar endTime = (Calendar)time.clone();
			endTime.add(Calendar.HOUR_OF_DAY, 1);
			times.add(time);
			actualValues[i] = dayFormat.format(time.getTime()) + ", " + dateFormat.format(time.getTime()) + " - " + dateFormat.format(endTime.getTime());

		}

		final String[] values = actualValues;
		ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_element_suggested_times, values);
		final ListView listView = (ListView) findViewById(R.id.suggested_times_listview);
		listView.setAdapter(adapter);
		findViewById(R.id.suggested_times_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Add a notification
				Context applicationContext = getApplicationContext();
				Resources resources = applicationContext.getResources();
				NotificationCompat.Builder builder = new NotificationCompat.Builder(applicationContext);

				builder.setSmallIcon(R.drawable.gears) // FIXME
					.setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)) // FIXME
					.setContentText(values[listView.getCheckedItemPosition()]) // FIXME
					.setContentTitle(resources.getString(R.string.app_name));
//					.setWhen() TODO: schedule
				NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				notificationManager.notify(0, builder.build());

				// Add an event to the calendar
				long calendarId = getCalendarId();
				if(calendarId != -1) {
					ContentValues values = new ContentValues();
					long time = ((Calendar)times.get(listView.getCheckedItemPosition())).getTimeInMillis();
					values.put(CalendarContract.Events.DTSTART, time);
					values.put(CalendarContract.Events.DTEND, time + 3600000); // FIXME
	//				values.put(CalendarContract.Events.RRULE,
	//					"FREQ=DAILY;COUNT=20;BYDAY=MO,TU,WE,TH,FR;WKST= // TODO: allow user to fix if they want to repeat
					values.put(CalendarContract.Events.TITLE, "Workout at ARC"); // TODO: add to settings
					values.put(CalendarContract.Events.EVENT_LOCATION, "201 E Peabody Drive, Urbana, IL");
					values.put(CalendarContract.Events.CALENDAR_ID, calendarId);
					values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/Chicago");
					values.put(CalendarContract.Events.DESCRIPTION, "Time to workout at ARC"); // TODO add workout name here
					values.put(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
					values.put(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
					if(ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
						// TODO: Consider calling
						//    ActivityCompat#requestPermissions
						// here to request the missing permissions, and then overriding
						//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
						//                                          int[] grantResults)
						// to handle the case where the user grants the permission. See the documentation
						// for ActivityCompat#requestPermissions for more details.
						return;
					}
					Uri uri = getContentResolver().insert(CalendarContract.Events.CONTENT_URI, values);
				}

				startActivity(new Intent(applicationContext, SchedulingFinishedActivity.class));
			}
		});
	}

	private long getCalendarId() {
		String[] projection = new String[] {CalendarContract.Calendars._ID};
		String selection = CalendarContract.Calendars.ACCOUNT_NAME +
			" = ? AND " +
			CalendarContract.Calendars.ACCOUNT_TYPE +
			" = ? ";
		// use the same values as above:
		String[] selArgs = new String[] {"Toms Account", CalendarContract.ACCOUNT_TYPE_LOCAL};
		if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return -1;
		}
		Cursor cursor = getContentResolver().
			query(CalendarContract.Calendars.CONTENT_URI, projection, null, null, null);
//			query(CalendarContract.Calendars.CONTENT_URI, projection, selection, selArgs, null); // TODO allow user to pick calendar
		if (cursor.moveToFirst()) {
			long retval = cursor.getLong(0);
			cursor.close();
			return retval;
		}
		return -1;
	}
}
