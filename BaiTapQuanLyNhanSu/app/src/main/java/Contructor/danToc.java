package Contructor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.baitapquanlynhansu.R;

public class danToc{

    String madt;
    String tendt;

    public danToc(String madt, String tendt) {
        this.madt = madt;
        this.tendt = tendt;
    }

    public String getMadt() {
        return madt;
    }

    public void setMadt(String madt) {
        this.madt = madt;
    }

    public String getTendt() {
        return tendt;
    }

    public void setTendt(String tenpb) {
        this.tendt = tendt;
    }
}
