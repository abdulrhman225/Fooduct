package com.example.fooduct.Orders;

public class All_Pro_same_section {
    String Image;
    String ProName;
    String ProPrice;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getProName() {
        return ProName;
    }

    public void setProName(String proName) {
        ProName = proName;
    }

    public String getProPrice() {
        return ProPrice;
    }

    public void setProPrice(String proPrice) {
        ProPrice = proPrice;
    }

    public All_Pro_same_section(String image, String proName, String proPrice) {
        Image = image;
        ProName = proName;
        ProPrice = proPrice;
    }
}
