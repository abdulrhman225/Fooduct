package com.example.fooduct.Orders;

public class order {
    String Pro_Name;
    int Number;

    public String getPro_Name() {
        return Pro_Name;
    }

    public void setPro_Name(String pro_Name) {
        Pro_Name = pro_Name;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public order(String pro_Name, int number) {
        Pro_Name = pro_Name;
        Number = number;
    }
}
