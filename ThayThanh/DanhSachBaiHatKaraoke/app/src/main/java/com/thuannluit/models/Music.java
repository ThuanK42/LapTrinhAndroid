package com.thuannluit.models;

public class Music {
    private String ma, tenBaiHat, caSi;
    private boolean thich;

    public Music() {
    }

    public Music(String ma, String tenBaiHat, String caSi, boolean thich) {
        this.ma = ma;
        this.tenBaiHat = tenBaiHat;
        this.caSi = caSi;
        this.thich = thich;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getCaSi() {
        return caSi;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }

    public boolean isThich() {
        return thich;
    }

    public void setThich(boolean thich) {
        this.thich = thich;
    }
}
