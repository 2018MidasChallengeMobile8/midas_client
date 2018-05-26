package com.xema.shopmanager.model;

import java.io.Serializable;

public class BuyDetail implements Serializable {
    private String name;
    private String num;
    private String price;

    public BuyDetail(String name, String num, String price) {
        this.name = name;
        this.num = num;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public String getPrice() {
        return price;
    }
}
