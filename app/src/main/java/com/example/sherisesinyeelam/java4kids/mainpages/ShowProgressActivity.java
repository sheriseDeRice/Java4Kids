package com.example.sherisesinyeelam.java4kids.mainpages;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.sherisesinyeelam.java4kids.NavigationDrawer;
import com.example.sherisesinyeelam.java4kids.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ShowProgressActivity extends NavigationDrawer {

    GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_show_progress);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_show_progress, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(2).setChecked(true);

        // todo, change this to a login status for each week to get more point.
        graph = (GraphView) findViewById(R.id.progress_graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3)
        });
        graph.addSeries(series);
    }
}

// Jonas Gehring (2019). GraphView - open source graph plotting library for Android [online]. available at http://www.android-graphview.org/ [accessed 1/03/2019]