package com.example.baitapquanlynhansu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Cursor c;
    EditText tk,mk;
    TextView kq;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper myDbHelper = new DatabaseHelper( this);
        btn =(Button)findViewById(R.id.btnDangNhap);
        tk =(EditText)findViewById(R.id.txtTk);
        mk =(EditText)findViewById(R.id.txtMK);
        kq = (TextView)findViewById(R.id.txtKQ);

        try {
            myDbHelper.createDataBase();
        } catch (IOException e) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        Toast.makeText(this, "Successfully Imported", Toast.LENGTH_SHORT).show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] para = {tk.getText().toString(),mk.getText().toString()};
                c = myDbHelper.query("TaiKhoanDangNhap",  null, "MaNV = ? and MatKhau = ? "  , para, null, null, null);
                if (c.moveToFirst()) {
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                   startActivity(new Intent(getApplicationContext(),Master.class));
                    finish();
                }
                else {
                    kq.setText("Sai tài khoản hoặc mật khẩu");
                }


            }
        });

    }
}
