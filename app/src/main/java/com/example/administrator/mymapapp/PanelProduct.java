package com.example.administrator.mymapapp;

/**
 * Created by Administrator on 15-Nov-17.
 */

public class PanelProduct {
    String cost;
    PanelProduct(){

    }
PanelProduct(String n,String c)
{
    cost=c;
    name=n;
}
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

}
