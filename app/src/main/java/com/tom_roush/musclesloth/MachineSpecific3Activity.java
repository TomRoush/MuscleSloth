package com.tom_roush.musclesloth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class MachineSpecific3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_specific3);


        GraphView graph = (GraphView) findViewById(R.id.graph3);

         LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
            new DataPoint(-2.5, 2.4),
            new DataPoint(-2, 6.2),
            new DataPoint(-1.5, 3.2),
            new DataPoint(-1, 1.6),
            new DataPoint(-0.5, 2.8),
            new DataPoint(0, 3.2)
        });

        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<DataPoint>(new DataPoint[]{
            new DataPoint(0, 3.3),
            new DataPoint(0.5, 3.6),
            new DataPoint(1, 4.1),
            new DataPoint(1.5, 5.3),
            new DataPoint(2, 5.0),
            new DataPoint(2.5, 5.0)
        });

        graph.addSeries(series);
        graph.addSeries(series2);
    }

}
