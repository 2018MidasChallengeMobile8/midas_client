package com.xema.shopmanager.model;

public class PostOrder {

    private String id;
    private String pw;
    private int state;
    private int year;
    private int month;

    public PostOrder(String id, String pw, int state, int year, int month) {
        this.id = id;
        this.pw = pw;
        this.state = state;
        this.year = year;
        this.month = month;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public int getState() {
        return state;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
