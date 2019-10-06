package com.thuannluit.truyendulieugiuacacmanhinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thuannluit.model.SinhVien;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xuLyMoVaGuiDuLieu(View view) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("KIEU_BOOLEAN", true);
        intent.putExtra("KIEU_CHAR", "x");
        intent.putExtra("KIEU_INT", 100);
        intent.putExtra("KIEU_DOUBLE", 15.99);
        intent.putExtra("KIEU_CHUOI", "Le Van Thuan");

        SinhVien sinhVien = new SinhVien("1","Le van thuan");
        intent.putExtra("SINHVIEN",sinhVien);

        startActivity(intent);
    }
}
