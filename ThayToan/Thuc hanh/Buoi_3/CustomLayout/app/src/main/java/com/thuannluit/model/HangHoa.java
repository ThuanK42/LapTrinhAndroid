package com.thuannluit.model;

public class HangHoa {
    private String maHang, tenHang, loaiHang;
    private int anh;

    public HangHoa(String maHang, String tenHang, String loaiHang, int anh) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.loaiHang = loaiHang;
        this.anh = anh;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getLoaiHang() {
        return loaiHang;
    }

    public void setLoaiHang(String loaiHang) {
        this.loaiHang = loaiHang;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }
}