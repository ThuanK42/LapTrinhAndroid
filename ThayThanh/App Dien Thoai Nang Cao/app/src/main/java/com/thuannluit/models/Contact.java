package com.thuannluit.models;

import java.io.Serializable;

public class Contact implements Serializable {
    private String ten, soDienThoai;
    private int anhDaiDien;

    public Contact() {
    }

    public Contact(String ten, String soDienThoai, int anhDaiDien) {
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.anhDaiDien = anhDaiDien;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(int anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }
}
