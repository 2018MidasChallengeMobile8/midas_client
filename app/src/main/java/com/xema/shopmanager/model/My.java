package com.xema.shopmanager.model;

public class My {
    String id;
    String pw;
    String nickname;

    public My(String id, String pw, String nickname) {
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getNickname() {
        return nickname;
    }
}
