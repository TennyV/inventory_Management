package com.snhu.inventorymanagement;

import androidx.annotation.NonNull;

public class InventoryUser {

    private int userID;
    private String userName;
    private String userPass;


    public InventoryUser(int userID, String userName, String userPass) {
        this.userID = userID;
        this.userName = userName;
        this.userPass = userPass;

    }

    public InventoryUser() {

    }

    public InventoryUser(int i, String toString) {
    }

    @NonNull
    @Override
    public String toString() {
        return "InventoryUser{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }


}
