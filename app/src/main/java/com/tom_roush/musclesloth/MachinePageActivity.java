package com.tom_roush.musclesloth;

<<<<<<< Updated upstream
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
=======
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;

>>>>>>> Stashed changes

public class MachinePageActivity extends AppCompatActivity {

    private Intent _workflow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_page);
<<<<<<< Updated upstream
=======

        Resources res = getResources();

    }

    public void startThreadmill(View v)
    {
        _workflow = new Intent(MachinePageActivity.this, MachineSpecificActivity.class);
        MachinePageActivity.this.startActivity(_workflow);
    }

    public void startExerciseBike(View v)
    {
        _workflow = new Intent(MachinePageActivity.this, MachineSpecific2Activity.class);
        MachinePageActivity.this.startActivity(_workflow);
    }

    public void startBenchPress(View v)
    {
        _workflow = new Intent(MachinePageActivity.this, MachineSpecific3Activity.class);
        MachinePageActivity.this.startActivity(_workflow);
    }

}

>>>>>>> Stashed changes

	    ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.gym_one);
    }
}