package com.thuannluit.kythuatxulysukien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
    EditText txtA, txtB;
    Button btnTru, btnNhan, btnChia, btnAn, btnThoat;

    View.OnClickListener suKienChiaSe = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        txtA = (EditText) findViewById(R.id.txtA);
        txtB = (EditText) findViewById(R.id.txtB);
        btnTru = (Button) findViewById(R.id.btnTru);
        btnNhan = (Button) findViewById(R.id.btnNhan);
        btnChia = (Button) findViewById(R.id.btnChia);
        btnAn = (Button) findViewById(R.id.btnAn);
        btnThoat = (Button) findViewById(R.id.btnThoat);
    }

    private void addEvents() {

        // set nut voi su kien
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyPhepTru();
            }
        });

        // Mot su kien 2 nut bam
        suKienChiaSe = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnNhan) {
                    xuLyPhepNhan();
                } else if (v.getId() == R.id.btnChia) {
                    xuLyPhepChia();
                }
            }
        };
        btnNhan.setOnClickListener(suKienChiaSe);
        btnChia.setOnClickListener(suKienChiaSe);
        btnAn.setOnLongClickListener(this);
        btnThoat.setOnLongClickListener(new MyEvent());
    }

    //Gan su kien truc tiep tu nut roi qua day viet code xu ly
    public void xuLyPhepCong(View view) {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        int c = a + b;
        Toast.makeText(MainActivity.this, "Tong c: " + c, Toast.LENGTH_LONG).show();
        Log.e("Tong cua a va b la ", c + "");
    }

    private void xuLyPhepTru() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        int c = a - b;
        Toast.makeText(MainActivity.this, "Hieu c: " + c, Toast.LENGTH_LONG).show();
        Log.e("hieu cua a va b la ", c + "");
    }

    private void xuLyPhepNhan() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        int c = a * b;
        Toast.makeText(MainActivity.this, "Tich c: " + c, Toast.LENGTH_LONG).show();
        Log.e("Tich cua a va b la ", c + "");
    }

    private void xuLyPhepChia() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        int c = a / b;
        Toast.makeText(MainActivity.this, "Thuong c: " + c, Toast.LENGTH_LONG).show();
        Log.e("Thuong cua a va b la ", c + "");
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.btnAn) {
            btnAn.setVisibility(View.INVISIBLE);//An nut
        }
        return false;
    }

    public void xuLyDoiManHinhKhac(View view) {
        Button btnMoi = new Button(MainActivity.this) {
            @Override
            public boolean performClick() {
                setContentView(R.layout.activity_main);
                addControls();
                addEvents();
                return super.performClick();
            }
        };
        btnMoi.setText("Quay ve");
        btnMoi.setWidth(200);
        btnMoi.setHeight(200);

        setContentView(btnMoi);
    }

    public class MyEvent implements View.OnClickListener, View.OnLongClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnThoat) {
                finish();
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
