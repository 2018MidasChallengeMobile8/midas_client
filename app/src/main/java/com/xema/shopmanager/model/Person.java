package com.xema.shopmanager.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by xema0 on 2018-02-15.
 */

public class Person extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String name;
    private String phone;
    private String memo;
    //private int visit;//방문 횟수
    private Date createdAt = new Date();
    //private Date recentAt;//최근방문일자
    //private long totalPrice;//총 구매금
    private String profileImage;
    private RealmList<Sales> sales = new RealmList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    //public int getVisit() {
    //    return visit;
    //}

    //public void setVisit(int visit) {
    //    this.visit = visit;
    //}

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    //public Date getRecentAt() {
    //    return recentAt;
    //}
//
    //public void setRecentAt(Date recentAt) {
    //    this.recentAt = recentAt;
    //}
//
    //public long getTotalPrice() {
    //    return totalPrice;
    //}
//
    //public void setTotalPrice(long totalPrice) {
    //    this.totalPrice = totalPrice;
    //}

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public RealmList<Sales> getSales() {
        return sales;
    }

    public void setSales(RealmList<Sales> sales) {
        this.sales = sales;
    }
}