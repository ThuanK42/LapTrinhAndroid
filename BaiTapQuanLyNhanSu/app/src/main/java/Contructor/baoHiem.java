package Contructor;


import java.util.Date;

public class baoHiem {
    String mabh;
    String tenbh;
    String tungaybh;
    String denngaybh;

    public baoHiem(String mabh, String tenbh, String tungaybh, String denngaybh) {
        this.mabh = mabh;
        this.tenbh = tenbh;
        this.tungaybh = tungaybh;
        this.denngaybh = denngaybh;
    }

    public String getMabh() {
        return mabh;
    }

    public void setMabh(String mabh) {
        this.mabh = mabh;
    }

    public String getTenbh() {
        return tenbh;
    }

    public void setTenbh(String tenbh) {
        this.tenbh = tenbh;
    }

    public String getTungaybh() {
        return tungaybh;
    }

    public void setTungaybh(String tungaybh) {
        this.tungaybh = tungaybh;
    }

    public String getDenngaybh() {
        return denngaybh;
    }

    public void setDenngaybh(String denngaybh) {
        this.denngaybh = denngaybh;
    }
}
