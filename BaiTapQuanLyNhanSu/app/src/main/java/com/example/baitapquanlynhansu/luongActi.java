package com.example.baitapquanlynhansu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Adapter.LuongAdapter;
import Adapter.UserAdapter;
import Adapter.baoHiemAdapter;
import Adapter.chucVuAdapter;
import Contructor.User;
import Contructor.baoHiem;
import Contructor.chucVu;
import Contructor.luong;
import Contructor.nhanVien;

public class luongActi extends AppCompatActivity {
    ListView lv;
    ArrayList<luong> arrLuong=  new ArrayList<>();
    LuongAdapter lad =null;
    Cursor c;
    Button btnthem,btnsua,btntimluong;;
    EditText maluong,mucluong,ngaycapnhat,bacluong,timkiemluong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luongacti);
        final DatabaseHelper myDbHelper = new DatabaseHelper( this);
        lv =(ListView)findViewById(R.id.lstviewLuong);
        btnthem= (Button)findViewById(R.id.btnThemLuong);
        btnsua= (Button)findViewById(R.id.btnSuaLuong);
        btntimluong= (Button)findViewById(R.id.btnTimKiemLuong);
        maluong= (EditText)findViewById(R.id.txtThemMaLuong);
        mucluong= (EditText)findViewById(R.id.txtThemMucLuong);
        ngaycapnhat= (EditText)findViewById(R.id.txtThemNgayCapNhapLuong);
        bacluong= (EditText)findViewById(R.id.txtThemBacLuong);
        timkiemluong= (EditText)findViewById(R.id.txtTimKiemLuong);
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

        c = myDbHelper.query("Luong",  null, null  , null, null, null, null);
        if (c.moveToFirst()) {
            do {

                arrLuong.add( new luong( c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
            } while (c.moveToNext());
        }

        lad = new LuongAdapter(this,R.layout.activity_luongcontruct,arrLuong);
        lad.notifyDataSetChanged();

        lv.setAdapter(lad);


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] colString = {"NgayCapNhatLuong","MaLuong","MucLuong","BacLuong" };
                String[] paraString = {ngaycapnhat.getText().toString(),maluong.getText().toString(),mucluong.getText().toString(),bacluong.getText().toString() };
                String result= myDbHelper.addRecord("Luong",colString,null,paraString,null);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                arrLuong.clear();
                c = myDbHelper.query("Luong",  null, null  , null, null, null, null);
                if (c.moveToFirst()) {
                    do {

                        arrLuong.add( new luong( c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
                    } while (c.moveToNext());
                }
                lad.notifyDataSetChanged();
                lv.setAdapter(lad);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] para= {maluong.getText().toString(),ngaycapnhat.getText().toString()};
                c = myDbHelper.query("Luong",  null, "MaLuong = ? and ngaycapnhatluong =? "  , para, null, null, null);
                if (c.moveToFirst()) {
                    myDbHelper.updateRecord("update Luong set  mucluong = '"+mucluong.getText().toString()+"', bacluong = '"+bacluong.getText().toString()+"', ngaycapnhatluong = strftime('%d/%m/%Y','now','localtime')  where maluong = '"+maluong.getText().toString()+"' and ngaycapnhatluong = '"+ngaycapnhat.getText().toString()+"'"  );
                    arrLuong.clear();
                    c = myDbHelper.query("Luong",  null, null  , null, null, null, null);
                    if (c.moveToFirst()) {
                        do {

                            arrLuong.add( new luong( c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
                        } while (c.moveToNext());
                    }
                    lad.notifyDataSetChanged();
                    lv.setAdapter(lad);
                    Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Mã lương không tồn tại",Toast.LENGTH_LONG).show();
                }
            }
        });
        ngaycapnhat.setFocusable(false);

        ngaycapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(ngaycapnhat);
            }
        });

        btntimluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrLuong.clear();
                String [] para= {timkiemluong.getText().toString()};
                c = myDbHelper.query("Luong",  null, "MaLuong = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    do {

                        arrLuong.add( new luong( c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
                    } while (c.moveToNext());
                }
                lad.notifyDataSetChanged();
                lv.setAdapter(lad);
            }
        });
    }
    String date_time = "";
    int mYear;
    int mMonth;
    int mDay;

    int mHour;
    int mMinute;
    private void datePicker(final EditText txt){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date_time = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        txt.setText(date_time);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
