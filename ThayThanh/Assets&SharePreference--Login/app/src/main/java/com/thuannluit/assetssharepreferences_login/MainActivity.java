package com.thuannluit.assetssharepreferences_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txtUserName, txtPassword;
    Button btnDangNhap, btnThoat;
    CheckBox chkLuuThongTin;
    String tenThongTinDangNhap = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        txtUserName = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        chkLuuThongTin = findViewById(R.id.chkLuu);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnThoat = findViewById(R.id.btnThoat);
    }

    @Override
    //Luu trang thai
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences(tenThongTinDangNhap, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserName", txtUserName.getText().toString());
        editor.putString("PassWord", txtPassword.getText().toString());
        editor.putBoolean("SAVE", chkLuuThongTin.isChecked());
        editor.commit();
    }
    // Phuc hoi trang thai

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(tenThongTinDangNhap, MODE_PRIVATE);
        String userName = sharedPreferences.getString("UserName", "");
        String password = sharedPreferences.getString("PassWord", "");
        boolean save = sharedPreferences.getBoolean("SAVE", false);
        if (save) {
            txtUserName.setText(userName);
            txtPassword.setText(password);
        }
    }
}
