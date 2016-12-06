package com.tom_roush.musclesloth;

import java.util.Random;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.widget.ImageView;

import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.GraphView;

public class MachineSpecificActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_specific);

        GraphView graph = (GraphView) findViewById(R.id.graph1);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(-2.5, 5.3),
                new DataPoint(-2, 2.2),
                new DataPoint(-1.5, 1.1),
                new DataPoint(-1, 7),
                new DataPoint(-0.5, 3),
                new DataPoint(0, 2)
        });

        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 2.4),
                new DataPoint(0.5, 3.1),
                new DataPoint(1, 4.3),
                new DataPoint(1.5, 3),
                new DataPoint(2, 4),
                new DataPoint(2.5, 3)
        });

        graph.addSeries(series);
        graph.addSeries(series2);

    }

}
