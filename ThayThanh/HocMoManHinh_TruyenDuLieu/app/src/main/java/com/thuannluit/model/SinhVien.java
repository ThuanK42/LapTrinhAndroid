package com.thuannluit.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private String ma, hoTen;

    public SinhVien() {
    }

    public SinhVien(String ma, String hoTen) {
        this.ma = ma;
        this.hoTen = hoTen;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    @NonNull
    @Override
    public String toString() {
        return this.ma+"\n"+this.hoTen;
    }
}
