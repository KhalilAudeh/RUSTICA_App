package com.example.srourcompu.sample_app.Model;

import java.util.List;

/**
 * Created by srourcompu on 4/25/2018.
 */

public class Personal {
    private String phone_number, customer_name, customer_address, total, status;
    private List<Order> foods;

    public Personal(){

    }

    public Personal(String phone_number, String customer_name, String customer_address, String total, List<Order> foods){
        this.phone_number = phone_number;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.total = total;
        this.status = "0";
        this.foods = foods;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }
}
