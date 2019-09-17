package com.example.srourcompu.sample_app.Model;

/**
 * Created by srourcompu on 4/18/2018.
 */

public class User {
    private String clientName;
    private String clientPassword;
    private String ID;

    public User(){

    }

    public User(String clientName, String clientPassword){
        this.clientName = clientName;
        this.clientPassword = clientPassword;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
}
