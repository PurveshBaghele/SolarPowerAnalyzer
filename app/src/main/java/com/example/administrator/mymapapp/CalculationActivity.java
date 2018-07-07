package com.example.administrator.mymapapp;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class CalculationActivity extends AppCompatActivity {
    GraphView graph;
    EditText e1,e2,e3;
    TextView tv;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        getSupportActionBar().hide();
        graph=(GraphView)findViewById(R.id.graph);

        new doit(getIntent().getExtras().getString("LAT"),getIntent().getExtras().getString("LONG")).execute();
        new Graph();


    }

    public class doit extends AsyncTask<Void,Void,Void>
    {
        String words="";
        int i=0;
        String lat,longi;
        ArrayList<String> arr = new ArrayList<>();
        doit(String lat,String longi)
        {
            this.lat=lat;
            this.longi=longi;
        }
        // String latitude=e1.getText().toString();
        //String longitude=e2.getText().toString();
        @Override
        protected Void doInBackground(Void... voids)
        {
            try {
                String url1 = "https://eosweb.larc.nasa.gov/cgi-bin/sse/grid.cgi?&num=100124&lat=";
                String url2 = "&hgt=100&submit=Submit&veg=17&sitelev=&email=skip@larc.nasa.gov&p=grid_id&p=swvdwncook&p=ret_tlt0&step=2&lon=";
                String url = url1 +lat+ url2 +longi;
                Document doc = Jsoup.connect(url).get();
                Element table = doc.select("table").get(4);
                Element rows = table.select("tr").get(1);
                for(Element td:rows.select("td")) {
                    // Elements td = row.select("td");
                    if(i==0) {
                        i++;
                        continue;
                    }
                    else{

                        arr.add(td.text().toString());
                    }
                }

                //words = doc.text();
                for(String s:arr) {
                    words += s+" ";
                }
            }
            catch(Exception e){e.printStackTrace();};
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Graph g=new Graph();
            g.setText(words);

            //arr.remove(i);
            GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
            gridLabel.setHorizontalAxisTitle("Month");

            gridLabel.setVerticalAxisTitle("Electricity(kWH)");
           BarGraphSeries<DataPoint> series= g.generate();
            graph.addSeries(series);
            graph.getViewport().setXAxisBoundsManual(true);
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
            staticLabelsFormatter.setHorizontalLabels(new String[] {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","   "});
            graph.getGridLabelRenderer().setHorizontalLabelsAngle(90);
            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

            series.setAnimated(true);
           // series.setThickness(15);
            series.setSpacing(70);


           // series.setDataPointsRadius(10);
            //series.setDrawDataPoints(true);
          //  graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(0); // set the min value of the viewport of x axis<br />
            graph.getViewport().setMaxX(13.0); // set the max value of the viewport of x-axix</p>*/
            // set manual Y bounds<br />
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(0.0);  // set the min value of the viewport of y axis<br />
            graph.getViewport().setMaxY(8.0);
            graph.getGridLabelRenderer().setLabelsSpace(2);
            graph.getGridLabelRenderer().setLabelHorizontalHeight(50);

            series.setDrawValuesOnTop(true);
            series.setValuesOnTopColor(Color.RED);
            // set the max value of the viewport of y-axis<br />
            graph.getViewport().setScrollable(true);
          // graph.getViewport().setScrollableY(true);
           graph.setHorizontalScrollBarEnabled(true);

           // graph.getViewport().setScalable(true);
          //  graph.getViewport().setScalableY(true);

        }
    }
}