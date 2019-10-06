package com.thuannluit.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ManHinhXuLyActivity extends AppCompatActivity {
    TextView txtNhan;
    Button btnTinhUSCLN;
    Intent intent;
    int a, b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_xu_ly);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTinhUSCLN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTinhUSCLN();
            }
        });
    }

    private void xuLyTinhUSCLN() {
        int min = Math.min(a, b);
        int uscln = 1;
        for (int i = min; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                uscln = i;
                break;
            }
        }
        intent.putExtra("USCLN", uscln);
        setResult(33,intent);
        finish();
    }

    private void addControls() {
        txtNhan = findViewById(R.id.txtNhan);
        btnTinhUSCLN = findViewById(R.id.btnTinhUSCLN);
        // buoc 2 : Lay du lieu ra
        intent = getIntent();
        a = intent.getIntExtra("a", -1);
        b = intent.getIntExtra("b", -1);
        txtNhan.setText("a = " + a + " ; " + "b = " + b);
    }
}
