package Contructor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.baitapquanlynhansu.R;

public class trinhdohocvan {

    String bangcaphv;
    String mall;
    String diemhv;


public trinhdohocvan(String bangcaphv, String mall, String diemhv) {
        this.bangcaphv = bangcaphv;
        this.mall = mall;
        this.diemhv = diemhv;
        }

public String getBangcaphv() {
        return bangcaphv;
        }

public void setBangcaphv(String bangcaphv) {
        this.bangcaphv = bangcaphv;
        }

public String getMall() {
        return mall;
        }

public void setMall(String mall) {
        this.mall = mall;
        }

public String getDiemhv() {
        return diemhv;
        }

public void setDiemhv(String diemhv) {
        this.diemhv = diemhv;

        }
}

