package com.example.baitapquanlynhansu;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import Adapter.LoaiHopDongAdapter;
import Contructor.LoaiHopDong;

public class LoaiHopDongActi extends AppCompatActivity {
    ListView lv;
    ArrayList<LoaiHopDong> arrLoaiHopDong=  new ArrayList<>();
    LoaiHopDongAdapter lhd =null;
    Cursor c;
    Button btnthem,btnsuahd,btntimkiem;
    EditText maloaihd,tenloaihd,timkiemloaihd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaihopdongacti);
        final DatabaseHelper myDbHelper = new DatabaseHelper( this);
        lv =(ListView)findViewById(R.id.lstviewLoaiHD);
        btnthem= (Button)findViewById(R.id.btnThemLoaiHD);
        btnsuahd= (Button)findViewById(R.id.btnSuaLoaiHD);
        btntimkiem= (Button)findViewById(R.id.btnTimKiemLoaiHD);
        maloaihd= (EditText)findViewById(R.id.txtThemMaLoaiHD);
        tenloaihd= (EditText)findViewById(R.id.txtThemTenLoaiHD);
        timkiemloaihd=(EditText)findViewById(R.id.txtTimKiemLoaiHD);
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
        c = myDbHelper.query("LoaiHDLD",  null, null  , null, null, null, null);
        if (c.moveToFirst()) {
            do {
                arrLoaiHopDong.add( new LoaiHopDong( c.getString(0),c.getString(1)));
            } while (c.moveToNext());
        }

        lhd = new LoaiHopDongAdapter(this,R.layout.activity_loaihopdongcontruct,arrLoaiHopDong);
        lhd.notifyDataSetChanged();

        lv.setAdapter(lhd);


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] colString = {"MaloaiHDLD","TenloaiHDLD" };
                String[] paraString = {maloaihd.getText().toString(),tenloaihd.getText().toString() };
                String result= myDbHelper.addRecord("LoaiHDLD",colString,null,paraString,null);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                arrLoaiHopDong.clear();
                c = myDbHelper.query("LoaiHDLD",  null, null  , null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        arrLoaiHopDong.add( new LoaiHopDong( c.getString(0),c.getString(1)));
                    } while (c.moveToNext());
                }
                lhd.notifyDataSetChanged();
                lv.setAdapter(lhd);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        });

        btnsuahd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] para= {maloaihd.getText().toString()};
                c = myDbHelper.query("LoaiHDLD",  null, "MaloaiHDLD = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    myDbHelper.updateRecord("update LoaiHDLD set  tenloaihdld = '"+tenloaihd.getText().toString()+"'  where MaloaiHDLD = '"+maloaihd.getText().toString()+"'"  );
                    arrLoaiHopDong.clear();
                    c = myDbHelper.query("LoaiHDLD",  null, null  , null, null, null, null);
                    if (c.moveToFirst()) {
                        do {
                            arrLoaiHopDong.add( new LoaiHopDong( c.getString(0),c.getString(1)));
                        } while (c.moveToNext());
                    }
                    lhd.notifyDataSetChanged();
                    lv.setAdapter(lhd);
                    Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Mã loại hợp đồng không tồn tại",Toast.LENGTH_LONG).show();
                }
            }
        });

        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrLoaiHopDong.clear();
                String [] para= {timkiemloaihd.getText().toString()};

                c = myDbHelper.query("LoaiHDLD",  null, "MaloaiHDLD = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        arrLoaiHopDong.add( new LoaiHopDong( c.getString(0),c.getString(1)));
                    } while (c.moveToNext());
                }
                lhd.notifyDataSetChanged();
                lv.setAdapter(lhd);

            }
        });




    }
}
