package com.example.administrator.mymapapp;

import java.io.Serializable;

/**
 * Created by ASUS on 12-Nov-17.
 */

public class Seller implements Serializable {
    String name;
    String business_name;
    String city;
    String cities_of_operation;
    String number;
    String address;

    public String getName() {
        return name;
    }
Seller()
{

}
    public void setName(String name) {
        this.name = name;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCities_of_operation() {
        return cities_of_operation;
    }

    public void setCities_of_operation(String cities_of_operation) {
        this.cities_of_operation = cities_of_operation;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;
    public Seller(String name,String business_name,String city,String cities_of_operation,String number,String address,String email)
    {
        this.name=name;
        this.business_name=business_name;
        this.city=city;
        this.cities_of_operation=cities_of_operation;
        this.number=number;
        this.address=address;
        this.email=email;
    }


}
