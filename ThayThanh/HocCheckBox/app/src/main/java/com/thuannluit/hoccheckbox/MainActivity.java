package com.thuannluit.hoccheckbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    CheckBox chk, chk2, chk3, chk4, chk5, chk6;
    Button btnChon;
    EditText txtNoidung;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        chk = (CheckBox) findViewById(R.id.chk);
        chk2 = (CheckBox) findViewById(R.id.chk2);
        chk3 = (CheckBox) findViewById(R.id.chk3);
        chk4 = (CheckBox) findViewById(R.id.chk4);
        chk5 = (CheckBox) findViewById(R.id.chk5);
        chk6 = (CheckBox) findViewById(R.id.chk6);
        btnChon  = (Button) findViewById(R.id.btnChon);
        txtNoidung = (EditText) findViewById(R.id.txtNoidung);

    }

    private void addEvents() {
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChonTheLoai();
            }
        });
    }

    private void xuLyChonTheLoai() {
        String s = "";

        // Kiem tra checkbox nao duoc chon
        if (chk.isChecked()) {
            s += chk.getText().toString() + "\n";
        }
        if (chk2.isChecked()) {
            s += chk2.getText().toString() + "\n";
        }
        if (chk3.isChecked()) {
            s += chk3.getText().toString() + "\n";
        }
        if (chk4.isChecked()) {
            s += chk4.getText().toString() + "\n";
        }
        if (chk5.isChecked()) {
            s += chk5.getText().toString() + "\n";
        }
        if (chk6.isChecked()) {
            s += chk6.getText().toString() + "\n";
        }
        // Set noi dung da chon cho edittext
        txtNoidung.setText(s);
    }
}
