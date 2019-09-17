package com.example.srourcompu.sample_app.Model;

/**
 * Created by srourcompu on 4/21/2018.
 */

public class Food {
    private String foodDescription;
    private String foodImage;
    private String foodMenuId;
    private String foodName;
    private String foodPrice;

    public void Food(){

    }

    public void Food(String foodDescription, String foodImage, String foodMenuId, String foodName, String foodPrice){
        this.foodDescription = foodDescription;
        this.foodImage = foodImage;
        this.foodMenuId = foodMenuId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public void setFoodMenuId(String foodMenuId) {
        this.foodMenuId = foodMenuId;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public String getFoodMenuId() {
        return foodMenuId;
    }

    public String getFoodPrice() {
        return foodPrice;
    }
}
