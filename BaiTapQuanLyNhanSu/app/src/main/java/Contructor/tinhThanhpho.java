package Contructor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.baitapquanlynhansu.R;

public class tinhThanhpho  {

    String matinhtp ;
    String tentinhtp ;

    public tinhThanhpho(String matinhtp, String tentinhtp) {
        this.matinhtp = matinhtp;
        this.tentinhtp = tentinhtp;
    }

    public String getMatinhtp() {
        return matinhtp;
    }

    public void setMatinhtp(String matinhtp) {
        this.matinhtp = matinhtp;
    }

    public String getTentinhtp() {
        return tentinhtp;
    }

    public void setTentinhtp(String tentinhtp) {
        this.tentinhtp = tentinhtp;
    }
}

