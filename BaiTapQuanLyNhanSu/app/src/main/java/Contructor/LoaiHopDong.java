package Contructor;

public class LoaiHopDong {
    String maloaihd;
    String tenloaihd;

    public LoaiHopDong(String maloaihd, String tenloaihd) {
        this.maloaihd = maloaihd;
        this.tenloaihd = tenloaihd;
    }

    public String getMaLoaiHD() {
        return maloaihd;
    }

    public void setMaLoaiHD(String maloaihd) {
        this.maloaihd = maloaihd;
    }

    public String getTenLoaiHD() {
        return tenloaihd;
    }

    public void setTenLoaiHD(String tenloaihd) {
        this.tenloaihd = tenloaihd;
    }
}

