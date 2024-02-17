package com.example.fooduct.AdminPage;

public class custom_order {
    String order;
    String userName;
    String phoneNumber;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public custom_order(String order, String userName, String phoneNumber) {
        this.order = order;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }
}
