package com.tom_roush.musclesloth;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FloorPlan extends AppCompatActivity {
    static final int NUM_ITEMS = 4;

    private Intent _workflow;

    public static int currentPage = 0;

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //              Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                     .setAction("Action", null).show();
                mViewPager.setCurrentItem(0);
            }
        });
    }

    public void hackyWork(View v) {
        startActivity(new Intent(this, (currentPage == 1 || currentPage == 3 ? RoomPageActivity.class : MachinePageActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_floor_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        ImageView imgZoom;
        Matrix matrix = new Matrix();
        Float scale = 1f;
        ScaleGestureDetector SGD;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_floor_plan, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //    textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            textView.setText("Floor " + getArguments().getInt(ARG_SECTION_NUMBER));

            //ImageView imageView = (ImageView) rootView.findViewById(R.id.room_image);
	        imgZoom = (ImageView) rootView.findViewById(R.id.room_image);
            SGD = new ScaleGestureDetector(rootView.getContext(), new ScaleListener());
            imgZoom.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    SGD.onTouchEvent(motionEvent);
                    return true;
                }
            });

            if (getArguments().getInt(ARG_SECTION_NUMBER) == 0) {
                currentPage = 0;
                imgZoom.setImageResource(R.drawable.lower_level);
            }
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                currentPage = 1;
                imgZoom.setImageResource(R.drawable.arc_court);
            }

            if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                currentPage = 2;
                imgZoom.setImageResource(R.drawable.arc_court2);
            }
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 3){
                currentPage = 3;
                imgZoom.setImageResource(R.drawable.top);
            }
            return rootView;
        }

        private class ScaleListener extends ScaleGestureDetector.
                SimpleOnScaleGestureListener {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                scale *= detector.getScaleFactor();
                scale = Math.max(0.1f, Math.min(scale, 1f));
                matrix.setScale(scale, scale);
                imgZoom.setImageMatrix(matrix);
                return true;
            }
        }
        /*public boolean onTouchEvent(MotionEvent ev) {
            SGD.onTouchEvent(ev);
            return true;
        }*/
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


            if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {

                if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                    currentPage = 2;

                    imageView.setImageResource(R.drawable.arc_court2);
                }
                if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                    currentPage = 3;
                    imageView.setImageResource(R.drawable.top);
                }
                return rootView;
            }

        return rootView;
        }
    }


//passing listener object to button


        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         */
        public class SectionsPagerAdapter extends FragmentPagerAdapter {


            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                // getItem is called to instantiate the fragment for the given page.
                // Return a PlaceholderFragment (defined as a static inner class below).
                return PlaceholderFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                // Show 4 total pages.
                return NUM_ITEMS;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "SECTION 1";
                    case 1:
                        return "SECTION 2";
                    case 2:
                        return "SECTION 3";
                    case 3:
                        return "SECTION 4";
                }
                return null;
            }


        }
    }




