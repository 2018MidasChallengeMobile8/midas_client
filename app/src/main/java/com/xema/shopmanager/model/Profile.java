package com.xema.shopmanager.model;

public class Profile {
    private int uid;
    private String name;
    private String profile_image;
    private String comment;
    private int type;
    private int point;


    public Profile(int uid, String name, String profile_image, String comment, int type, int point) {
        this.uid = uid;
        this.name = name;
        this.profile_image = profile_image;
        this.comment = comment;
        this.type = type;
        this.point = point;
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getComment() {
        return comment;
    }

    public int getType() {
        return type;
    }

    public int getPoint() {
        return point;
    }
}
