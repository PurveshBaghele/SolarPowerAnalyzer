package com.example.administrator.mymapapp;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;


public class Graph {
    BarGraphSeries<DataPoint> series;
    GraphView graph;
    String word;
    String[] months={"Jan","Feb","March","April","May","June","July","Aug","Sep","Octo","Nov","Dec"};
    BarGraphSeries<DataPoint> generate(){

        series=new BarGraphSeries<DataPoint>(getData());
        return series;
    }

    DataPoint[] getData(){
        Double d;
        int i=0;
        DataPoint[] dp= new DataPoint[12];
        String[] s=word.split(" ");
        for(String temp:s){
            d=Double.parseDouble(temp);
            dp[i]=new DataPoint(i+1,d);
            i++;
        }
        return dp;
    }

    void setText(String s){
        word=s;
    }
}