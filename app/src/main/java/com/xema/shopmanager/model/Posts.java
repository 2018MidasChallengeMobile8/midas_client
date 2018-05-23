package com.xema.shopmanager.model;

import java.io.File;

public class Posts {
    private String title;
    private String text;
    private File image;
    private int post_id;

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public File getImage() {
        return image;
    }

    public int getPost_id() {
        return post_id;
    }

    public Posts(String title, String text, File image, int post_id) {

        this.title = title;
        this.text = text;
        this.image = image;
        this.post_id = post_id;
    }
}
