package com.xema.shopmanager.model;

import java.io.Serializable;

public class BuyDetail implements Serializable {
    private int id;
    private String name;
    private String num;
    private String price;

    public BuyDetail(int id, String name, String num, String price) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.price = price;
    }

    public int getId() {
        return id;
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

    public void setNum(String num) {
        this.num = num;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
