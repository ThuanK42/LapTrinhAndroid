package com.thuannluit.truyendulieugiuacacmanhinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.thuannluit.model.SinhVien;

public class Main2Activity extends AppCompatActivity {
    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addControls();
    }

    private void addControls() {
        txtKetQua = findViewById(R.id.txtKetQua);
        Intent intent = getIntent();
        boolean kieuBoolean = intent.getBooleanExtra("KIEU_BOOLEAN", false);
        char kieuChar = intent.getCharExtra("KIEU_CHAR", 'w');
        int kieuInt = intent.getIntExtra("KIEU_INT", 0);
        double kieuDouble = intent.getDoubleExtra("KIEU_DOUBLE", 0.0);
        String kieuChuoi = intent.getStringExtra("KIEU_CHUOI");

        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("SINHVIEN");
        txtKetQua.setText("Kieu boolean = " + kieuBoolean + "\n"
                + "Kieu char = " + kieuChar + "\n"
                + "Kieu int = " + kieuInt + "\n"
                + "Kieu double = " + kieuDouble + "\n"
                + "Kieu chuoi = " + kieuChuoi + "\n"
                + "Kieu doi tuong = "+sinhVien
        );
    }
}
