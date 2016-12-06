package com.tom_roush.musclesloth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.GraphView;

public class MachineSpecific2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_specific2);


        GraphView graph = (GraphView) findViewById(R.id.graph2);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(-2.5, 9.4),
                new DataPoint(-2, 1.3),
                new DataPoint(-1.5, 5.4),
                new DataPoint(-1, 1.3),
                new DataPoint(-0.5, 2.7),
                new DataPoint(0, 4.8)
        });

        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 5.0),
                new DataPoint(0.5, 4.3),
                new DataPoint(1, 4.1),
                new DataPoint(1.5, 3.1),
                new DataPoint(2, 2.7),
                new DataPoint(2.5, 2.4)
        });

        graph.addSeries(series);
        graph.addSeries(series2);

    }

}
