package com.example.demo.bean;

import java.io.Serializable;

public class Category implements Serializable {
    private int stt;
    private int id;
    private String ten;

    public Category() {

    }

    public Category(int stt, int id, String ten) {
        this.stt = stt;
        this.id = id;
        this.ten = ten;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
