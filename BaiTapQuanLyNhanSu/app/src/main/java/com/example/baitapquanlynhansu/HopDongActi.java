package com.example.baitapquanlynhansu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


import Adapter.HopDongAdapter;
import Contructor.HopDong;


public class HopDongActi extends AppCompatActivity {
    ListView lv;
    ArrayList<HopDong> arrHopDong=  new ArrayList<>();
    HopDongAdapter hdad =null;
    Cursor c;
    Button btnthem,btnsua,btntimhd;;
    EditText mahd,mall,maloaihd,ngaycapluong,ngayki,nguoiki,apdungtungay,dieukhoan,ghichu, timkiemhd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hopdongacti);
        final DatabaseHelper myDbHelper = new DatabaseHelper( this);
        lv =(ListView)findViewById(R.id.lstviewHopDong);
        btnthem= (Button)findViewById(R.id.btnThemHopDong);
        btnsua= (Button)findViewById(R.id.btnSuaHopDong);
        btntimhd= (Button)findViewById(R.id.btnTimKiemHopDong);
        mahd= (EditText)findViewById(R.id.txtHopDongMaHD);
        mall= (EditText)findViewById(R.id.txtHopDongMaLL);
        maloaihd= (EditText)findViewById(R.id.txtHopDongMaLoaiHD);
        ngaycapluong= (EditText)findViewById(R.id.txtThemHDNgayCapLuong);
        ngayki= (EditText)findViewById(R.id.txtThemHDNgayKi);
        nguoiki= (EditText)findViewById(R.id.txtHopDongNguoiKi);
        apdungtungay= (EditText)findViewById(R.id.txtThemHDApDungTN);
        dieukhoan= (EditText)findViewById(R.id.txtHopDongDieuKhoan);
        ghichu= (EditText)findViewById(R.id.txtHopDongGhiChu);
        timkiemhd= (EditText)findViewById(R.id.txtTimKiemHopDong);
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

        c = myDbHelper.query("HopDongLaoDong",  null, null  , null, null, null, null);
        if (c.moveToFirst()) {
            do {

                arrHopDong.add( new HopDong( c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8)));
            } while (c.moveToNext());
        }

        hdad = new HopDongAdapter(this,R.layout.activity_hopdongcontruct,arrHopDong);
        hdad.notifyDataSetChanged();

        lv.setAdapter(hdad);


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] colString = {"MaHD","MaLL","MaloaiHDLD","NgayCapLuong", "NgayKi", "NguoiKi", "ApDungTuNgay", "DieuKhoan","GhiChuHDLD" };
                String[] paraString = {mahd.getText().toString(),mall.getText().toString(),maloaihd.getText().toString(),ngaycapluong.getText().toString(), ngayki.getText().toString(), nguoiki.getText().toString(),apdungtungay.getText().toString(),dieukhoan.getText().toString(),ghichu.getText().toString() };
                String result= myDbHelper.addRecord("HopDongLaoDong",colString,null,paraString,null);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                arrHopDong.clear();
                c = myDbHelper.query("HopDongLaoDong",  null, null  , null, null, null, null);
                if (c.moveToFirst()) {
                    do {

                        arrHopDong.add( new HopDong( c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8)));
                    } while (c.moveToNext());
                }
                hdad.notifyDataSetChanged();
                lv.setAdapter(hdad);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] para= {mahd.getText().toString(),mall.getText().toString()};
                c = myDbHelper.query("HopDongLaoDong",  null, "MaHD = ? and MaLL = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    myDbHelper.updateRecord("update HopDongLaoDong set    ngaycapluong = '"+ngaycapluong.getText().toString()+"', ngayki = '"+ngayki.getText().toString()+"',nguoiki = '"+nguoiki.getText().toString()+"', apdungtungay = '"+apdungtungay.getText().toString()+"', dieukhoan = '"+dieukhoan.getText().toString()+"', ghichuHDLD = '"+ghichu.getText().toString()+"'  where mahd = '"+mahd.getText().toString()+"' and mall = '"+mall.getText().toString()+"'"  );
                    arrHopDong.clear();
                    c = myDbHelper.query("HopDongLaoDong",  null, null  , null, null, null, null);
                    if (c.moveToFirst()) {
                        do {

                            arrHopDong.add( new HopDong( c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8)));
                        } while (c.moveToNext());
                    }
                    hdad.notifyDataSetChanged();
                    lv.setAdapter(hdad);
                    Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Mã hợp đồng không tồn tại",Toast.LENGTH_LONG).show();
                }
            }
        });
        ngaycapluong.setFocusable(false);
        ngayki.setFocusable(false);
        apdungtungay.setFocusable(false);
        ngaycapluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(ngaycapluong);
            }
        });
        ngayki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(ngayki);
            }
        });
        apdungtungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(apdungtungay);
            }
        });
        btntimhd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrHopDong.clear();
                String [] para= {timkiemhd.getText().toString()};
                c = myDbHelper.query("HopDongLaoDong",  null, "MaHD = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    do {

                        arrHopDong.add( new HopDong( c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8)));
                    } while (c.moveToNext());
                }
                hdad.notifyDataSetChanged();
                lv.setAdapter(hdad);
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
