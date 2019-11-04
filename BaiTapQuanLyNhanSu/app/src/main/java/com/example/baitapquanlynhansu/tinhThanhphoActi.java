package com.example.baitapquanlynhansu;


import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

import Adapter.tinhThanhphoAdapter;
import Contructor.tinhThanhpho;


public class tinhThanhphoActi extends AppCompatActivity {

    ListView lv;
    ArrayList<tinhThanhpho> arrTinhTP=  new ArrayList<>();
    tinhThanhphoAdapter ttpad =null;
    Cursor c;
    Button btnthem,btnsua,btntimkiem;
    EditText matinhtp,tentinhtp,timtinhtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinhthanhphoacti);
        final DatabaseHelper myDbHelper = new DatabaseHelper( this);
        lv =(ListView)findViewById(R.id.lstviewTinhthanhpho);
        btnthem= (Button)findViewById(R.id.btnThemTinhTP);
        btnsua= (Button)findViewById(R.id.btnSuaTinhTP);
        btntimkiem= (Button)findViewById(R.id.btnTimKiemTinhTP);
        matinhtp= (EditText)findViewById(R.id.txtThemMaTinhTP);
        tentinhtp= (EditText)findViewById(R.id.txtThemTenTinhTP);
        timtinhtp = (EditText)findViewById(R.id.txtTimKiemTinhTP);
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
        c = myDbHelper.query("TinhThanhPho",  null, null  , null, null, null, null);
        if (c.moveToFirst()) {
            do {
                arrTinhTP.add( new tinhThanhpho( c.getString(0),c.getString(1)));
            } while (c.moveToNext());
        }

        ttpad = new tinhThanhphoAdapter(this,R.layout.activity_tinhthanhphocontruct,arrTinhTP);
        ttpad.notifyDataSetChanged();

        lv.setAdapter(ttpad);


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] colString = {"MaTP","TenTP" };
                String[] paraString = {matinhtp.getText().toString(),tentinhtp.getText().toString() };
                String result= myDbHelper.addRecord("TinhThanhPho",colString,null,paraString,null);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                arrTinhTP.clear();
                c = myDbHelper.query("TinhThanhPho",  null, null  , null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        arrTinhTP.add( new tinhThanhpho( c.getString(0),c.getString(1)));
                    } while (c.moveToNext());
                }
                ttpad.notifyDataSetChanged();
                lv.setAdapter(ttpad);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] para= {matinhtp.getText().toString()};
                c = myDbHelper.query("TinhThanhPho",  null, "MaTP = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    myDbHelper.updateRecord("update TinhThanhPho set  tenTp = '"+tentinhtp.getText().toString()+"'  where maTP = '"+matinhtp.getText().toString()+"'"  );
                    arrTinhTP.clear();
                    c = myDbHelper.query("TinhThanhPho",  null, null  , null, null, null, null);
                    if (c.moveToFirst()) {
                        do {
                            arrTinhTP.add( new tinhThanhpho( c.getString(0),c.getString(1)));
                        } while (c.moveToNext());
                    }
                    ttpad.notifyDataSetChanged();
                    lv.setAdapter(ttpad);
                    Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Mã tỉnh thành phố không tồn tại",Toast.LENGTH_LONG).show();
                }
            }
        });
        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrTinhTP.clear();
                String[] selectionArgs ={timtinhtp.getText().toString()};
                c = myDbHelper.query("TinhThanhPho",  null, "MaTP = ?"  , selectionArgs, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        arrTinhTP.add( new tinhThanhpho( c.getString(0),c.getString(1)));
                    } while (c.moveToNext());
                }
                ttpad.notifyDataSetChanged();
                lv.setAdapter(ttpad);
            }
        });
    }
}
