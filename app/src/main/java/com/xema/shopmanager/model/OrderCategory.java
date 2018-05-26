package com.xema.shopmanager.model;

import android.graphics.drawable.Drawable;

public class OrderCategory {
    private Drawable category_image;
    private  String category_name;

    public OrderCategory(Drawable category_image, String category_name) {
        this.category_image = category_image;
        this.category_name = category_name;
    }

    public Drawable getCategory_image() {
        return category_image;
    }

    public String getCategory_name() {
        return category_name;
    }
}
