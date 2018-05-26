package com.xema.shopmanager.model;

public class OrderCategory {
    //private String im_url;
    private int id;
    private String name;


    public OrderCategory(int id,String name ) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
