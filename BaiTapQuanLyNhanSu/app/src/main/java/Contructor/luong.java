package Contructor;

import java.util.Date;

public class luong {
    String ngaycapnhatluong;
    String maluong;
    String mucluong;
    String batluong;

    public luong(String ngaycapnhatluong, String maluong, String mucluong, String batluong) {
        this.ngaycapnhatluong = ngaycapnhatluong;
        this.maluong = maluong;
        this.mucluong = mucluong;
        this.batluong = batluong;
    }

    public String getNgaycapnhatluong() {
        return ngaycapnhatluong;
    }

    public void setNgaycapnhatluong(String ngaycapnhatluong) {
        this.ngaycapnhatluong = ngaycapnhatluong;
    }

    public String getMaluong() {
        return maluong;
    }

    public void setMaluong(String maluong) {
        this.maluong = maluong;
    }

    public String getMucluong() {
        return mucluong;
    }

    public void setMucluong(String mucluong) {
        this.mucluong = mucluong;
    }

    public String getBatluong() {
        return batluong;
    }

    public void setBatluong(String batluong) {
        this.batluong = batluong;
    }
}
