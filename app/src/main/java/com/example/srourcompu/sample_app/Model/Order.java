package com.example.srourcompu.sample_app.Model;

/**
 * Created by srourcompu on 4/24/2018.
 */

public class Order {
    private String FoodName;
    private String FoodID;
    private String Price;
    private String Quantity;

    public Order(){

    }

    public Order(String FoodName, String FoodID, String Price, String Quantity){
        this.FoodName = FoodName;
        this.FoodID = FoodID;
        this.Price = Price;
        this.Quantity = Quantity;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public void setFoodID(String foodID) {
        FoodID = foodID;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getFoodName() {
        return FoodName;
    }

    public String getFoodID() {
        return FoodID;
    }

    public String getPrice() {
        return Price;
    }

    public String getQuantity() {
        return Quantity;
    }
}
