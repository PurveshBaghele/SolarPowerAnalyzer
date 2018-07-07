package com.example.administrator.mymapapp;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

import static java.lang.Math.floor;

public class TbbdAct extends AppCompatActivity {

    static int flaggg=1;
    static doit asyn;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    static TextView t1,t2,t3,t4,t5;
    static GraphView graphEN,graphAN,graphDH,graphTP;
    static BarChart chart;
    static LineChart chart2,chart3,chart4;
    static String lat,lng;
    static String gg[];
    static ProgressDialog dialog;

    static int days[]={31,28,31,30,31,30,31,31,30,31,30,31};
    static double energy[]=new double[12], dayhour[]=new double[12],temp[]=new double[12], angle[]=new double[12],arrea,totalEn,finENE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        lat=getIntent().getExtras().getString("LAT");
        lng=getIntent().getExtras().getString("LONG");
        asyn= new doit(lat,lng);
        asyn.execute();

    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
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

            View rootView=null;

            switch (getArguments().getInt(ARG_SECTION_NUMBER))
            {
                case 1: {
                    rootView = inflater.inflate(R.layout.tab1estimations, container, false);

                    t1=(TextView) rootView.findViewById(R.id.are);
                    t2=(TextView)rootView.findViewById(R.id.pan);
                    t3=(TextView)rootView.findViewById(R.id.sys);
                    t4=(TextView)rootView.findViewById(R.id.ene);
                    t5=(TextView)rootView.findViewById(R.id.effi);

                    double y=Double.parseDouble(gg[1]),rat;
                    int q,pp=0,i;
                    for(i=1;i<=3;i++)
                        pp=pp*10+gg[2].charAt(i)-'0';

                    String rr=new String(gg[2].toCharArray(),0,3);

                    t1.setText("Installation area \n(Recommended 75%)(sq.ft.)  \n: "+String.valueOf(17.5*floor(y/17.5))+" sq. ft.");

                    t2.setText("Number of Solar Panels  \n: "+String.valueOf(q=(int)Math.floor(y/17.5))+" panels");

                    t3.setText("Rating of Solar Plant (kW)  \n: "+String.valueOf(rat= pp*q/1000.0)+" kW");

                    finENE=(totalEn*y*0.092903*rat)/(2*52);
                    finENE=(int)(10000*finENE);
                    finENE/=10000;

                    t4.setText("Energy per annum (kWH/year) \n: "+String.valueOf(finENE)+" kWH");

                    double efi=(pp*100)/(1000*1.625803);
                    efi=(int)(efi*10000);
                    efi/=10000;
                    t5.setText("Efficiency of Solar Panels \n: "+String.valueOf(efi) +" %");
                    break;
                }
                case 2: {
                    rootView = inflater.inflate(R.layout.tab2graphs, container, false);


                    /*graphEN=(GraphView) rootView.findViewById(R.id.graphEN);
                    graphEN.setVisibility(GraphView.INVISIBLE);
                    graphAN=(GraphView)rootView.findViewById(R.id.graphAN);
                    graphAN.setVisibility(GraphView.INVISIBLE);*/
                    graphDH=(GraphView)rootView.findViewById(R.id.graphDH);
                    graphDH.setVisibility(GraphView.INVISIBLE);
                    graphTP=(GraphView)rootView.findViewById(R.id.graphTP);
                    graphTP.setVisibility(GraphView.INVISIBLE);


                    chart = (BarChart) rootView.findViewById(R.id.chart);
                    chart2 = (LineChart) rootView.findViewById(R.id.chart2);

                    /*final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {


                        }
                    }, 3000);*/
                    asyn.showGraphs();

                    BarData data = new BarData(getXAxisValues(), getDataSet1());
                    chart.setData(data);
                    chart.setDescription("");
                    chart.animateXY(2000, 2000);
                    chart.invalidate();

                    LineData data2=new LineData(getXAxisValues(), getDataSet2());
                    chart2.setDescription("");
                    chart2.setData(data2);
                    chart2.animateXY(2000,2000);
                    chart2.invalidate();

                    break;
                }
            }
            return rootView;
        }

        private ArrayList<BarDataSet> getDataSet1() {
            ArrayList<BarDataSet> dataSets = null;

            ArrayList<BarEntry> valueSet1 = new ArrayList<>();
            for(int i=0;i<12;i++)
                valueSet1.add(new BarEntry((float)(energy[i]*days[i]*(finENE/totalEn)), i));

            BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Averaged Energy Production (kWh/Month)");

            barDataSet1.setColors(new int[]{Color.rgb(233,30,99),Color.rgb(3,169,244),Color.rgb(255,183,77)});

            //barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

            dataSets = new ArrayList<>();
            dataSets.add(barDataSet1);

            return dataSets;
        }

        private ArrayList<LineDataSet> getDataSet2() {
            ArrayList<LineDataSet> dataSets = null;

            ArrayList<Entry> valueSet1 = new ArrayList<>();
            for(int i=0;i<12;i++)
                valueSet1.add(new Entry((float)(angle[i]), i));

            LineDataSet set1 = new LineDataSet(valueSet1, "Opt. Tilt Angle For Panels");

            set1.setFillAlpha(110);
            // set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleSize(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            //set1.setColors(new int[]{Color.rgb(233,30,99),Color.rgb(3,169,244),Color.rgb(255,183,77)});

            //barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

            dataSets = new ArrayList<>();
            dataSets.add(set1);

            return dataSets;
        }

        private ArrayList<String> getXAxisValues() {
            ArrayList<String> xAxis = new ArrayList<>();
            xAxis.add("JAN");
            xAxis.add("FEB");
            xAxis.add("MAR");
            xAxis.add("APR");
            xAxis.add("MAY");
            xAxis.add("JUN");
            xAxis.add("JUL");
            xAxis.add("AUG");
            xAxis.add("SEP");
            xAxis.add("OCT");
            xAxis.add("NOV");
            xAxis.add("DEC");
            return xAxis;
        }
    }

    static DataPoint[] getData(int flag)
    {
        DataPoint[] dp= new DataPoint[12];
        switch(flag)
        {
            case 1:
                totalEn=0;
                Double en;
                for(int i=0;i<12;i++) {
                    dp[i] = new DataPoint(i + 1.0, en=  (energy[i]*days[i]));
                    totalEn+=en;
                }
                break;

            case 2:
                for(int i=0;i<12;i++) {
                    dp[i] = new DataPoint(i + 1.0, angle[i]);
                }
                break;

            case 3:
                for(int i=0;i<12;i++) {
                    dp[i] = new DataPoint(i + 1.0, dayhour[i]);
                }
                break;

            case 4:
                for(int i=0;i<12;i++) {
                    dp[i] = new DataPoint(i + 1.0, temp[i]);
                }
                break;
        }
        return dp;
    }

    public class doit extends AsyncTask<Void,Void,Void>
    {
        String words="";
        String lat,longi;

        doit(String lat,String longi)
        {
            this.lat=lat;
            this.longi=longi;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            try {
                String url1 = "https://eosweb.larc.nasa.gov/cgi-bin/sse/grid.cgi?&num=100124&lat=";
                String url2 = "&hgt=100&submit=Submit&veg=17&sitelev=&email=skip@larc.nasa.gov&p=swv_dwn&p=exp_dif&p=avg_dnr&p=daylight&p=ret_tlt0&p=TSKIN&step=2&lon=";
                String url = url1 +lat+ url2 +longi;
                Document doc = Jsoup.connect(url).get();

                Element table,rows;

                table = doc.select("table").get(2);
                rows = table.select("tr").get(1);
                for(int i=1;i<=12;i++)
                    energy[i-1]=Double.parseDouble(rows.child(i).text());

                table = doc.select("table").get(9);
                rows = table.select("tr").get(1);
                for(int i=1;i<=12;i++)
                    dayhour[i-1]=Double.parseDouble(rows.child(i).text());

                table = doc.select("table").get(10);
                rows = table.select("tr").get(11);
                for(int i=1;i<=12;i++)
                    angle[i-1]=Double.parseDouble(rows.child(i).text());

                table = doc.select("table").get(12);
                rows = table.select("tr").get(1);
                for(int i=1;i<=12;i++)
                    temp[i-1]=Double.parseDouble(rows.child(i).text());
            }
            catch(Exception e){e.printStackTrace();}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TbbdAct.getData(1);

            gg=getIntent().getExtras().getStringArray("input");

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */


            setContentView(R.layout.activity_tbbd);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);

            dialog.dismiss();
        }

        private void showGraphs() {

            GridLabelRenderer gridLabel;

            /*gridLabel = graphEN.getGridLabelRenderer();
            gridLabel.setHorizontalAxisTitle("Month");
            gridLabel.setVerticalAxisTitle("Averaged Electricity(kWH/sq.m/Month)");
            setGraph(graphEN,1);

            gridLabel = graphAN.getGridLabelRenderer();
            gridLabel.setHorizontalAxisTitle("Month");
            gridLabel.setVerticalAxisTitle("Opt. Tilt Angle For Panels");
            setGraph(graphAN,2);*/

            gridLabel = graphDH.getGridLabelRenderer();
            gridLabel.setHorizontalAxisTitle("Month");
            gridLabel.setVerticalAxisTitle("Hours of Daylight");
            setGraph(graphDH,3);

            gridLabel = graphTP.getGridLabelRenderer();
            gridLabel.setHorizontalAxisTitle("Month");
            gridLabel.setVerticalAxisTitle("Average Temperature");
            setGraph(graphTP,4);

        }

        void setGraph(GraphView graph,int flag)
        {
            //graph.setBackgroundColor();
            LineGraphSeries<DataPoint> series=new LineGraphSeries<DataPoint>(getData(flag));
            graph.addSeries(series);

            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
            staticLabelsFormatter.setHorizontalLabels(new String[] {"Jan ","Feb ","Mar ","Apr ","May ","Jun ","Jul ","Aug ","Sep ","Oct ","Nov ","Dec "});
            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalLabelsAngle(90);
            graph.getGridLabelRenderer().setPadding(40);

            graph.setBackgroundColor(Color.rgb(235,237,239));

            /*graph.setDrawValuesOnTop(true);
            series.setValuesOnTopColor(Color.RED);*/
            series.setBackgroundColor(Color.rgb(26,188,156));

            series.setAnimated(true);
            series.setThickness(15);
            series.setDataPointsRadius(10);
            series.setDrawDataPoints(true);
            series.setOnDataPointTapListener(new OnDataPointTapListener() {
                @Override
                public void onTap(Series series, DataPointInterface dataPoint) {
                    Toast.makeText(getApplicationContext(),""+dataPoint, Toast.LENGTH_SHORT).show();
                }
            });

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
            graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling

            //graph.getViewport().setDrawBorder(true);
            graph.setVisibility(GraphView.VISIBLE);
        }

    }

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
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ESTIMATIONS";
                case 1:
                    return "GRAPHS";
            }
            return null;
        }
    }
}


