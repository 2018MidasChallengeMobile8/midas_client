package com.xema.shopmanager.model;

public class OrderMenu
{
    private String im_url;
    private String category;
    private String name;
    private String price;

    public OrderMenu(String im_url, String category, String name, String price) {
        this.im_url = im_url;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getIm_url() {
        return im_url;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
