package com.xema.shopmanager.model;

public class BuyList {
    private int id;
    private Profile profile;
    private String date;
    private String comment;
    private int state;
    private int taking_time;
    private int price;

    public BuyList(int id, Profile profile, String date, String comment, int state, int taking_time, int price) {
        this.id = id;
        this.profile = profile;
        this.date = date;
        this.comment = comment;
        this.state = state;
        this.taking_time = taking_time;
        this.price = price;
    }

    public Profile getProfile() {
        return profile;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public int getState() {
        return state;
    }

    public int getTaking_time() {
        return taking_time;
    }

    public int getPrice() {
        return price;
    }
}
