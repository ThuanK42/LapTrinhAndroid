package Contructor;

public class nhanVien {
    private String manv;
    private String tennv;
    private String mall;
    private String macv;
    private String mapb;
    private String mabh;
    private String sdt;
    private String email;
    private String hinhanh;
    private String trangthai;

    public nhanVien(String manv, String tennv, String mall, String macv, String mapb, String mabh, String sdt, String email, String hinhanh, String trangthai) {
        this.manv = manv;
        this.tennv = tennv;
        this.mall = mall;
        this.macv = macv;
        this.mapb = mapb;
        this.mabh = mabh;
        this.sdt = sdt;
        this.email = email;
        this.hinhanh = hinhanh;
        this.trangthai = trangthai;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }

    public String getMacv() {
        return macv;
    }

    public void setMacv(String macv) {
        this.macv = macv;
    }

    public String getMapb() {
        return mapb;
    }

    public void setMapb(String mapb) {
        this.mapb = mapb;
    }

    public String getMabh() {
        return mabh;
    }

    public void setMabh(String mabh) {
        this.mabh = mabh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
