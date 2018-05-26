package com.xema.shopmanager.model;

public class OrderMenu
{
    private int id;
    private int price;
    private String name;
    private String image;
    private int type;
    private String category;


    public OrderMenu(int id, int price, String name, String image, int type, String category) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.image = image;
        this.type = type;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
