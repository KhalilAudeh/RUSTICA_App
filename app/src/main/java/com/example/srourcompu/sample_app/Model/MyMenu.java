package com.example.srourcompu.sample_app.Model;

/**
 * Created by srourcompu on 4/19/2018.
 */

public class MyMenu {
    private String foodName;
    private String foodImage;

    public void MyMenu(){

    }

    public void MyMenu(String foodName, String foodImage){
        this.foodName = foodName;
        this.foodImage = foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public String getFoodName() {
        return foodName;
    }
}
