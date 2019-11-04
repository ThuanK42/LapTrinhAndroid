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

import Adapter.trinhdohocvanAdapter;
import Contructor.trinhdohocvan;

public class trinhdohocvanActi extends AppCompatActivity {
    ListView lv;
    ArrayList<trinhdohocvan> arrTrinhDoHV=  new ArrayList<>();
    trinhdohocvanAdapter tdhvad =null;
    Cursor c;
    Button btnthem,btnsua,btntimbh;;
    EditText bangcaphv,mall,diemhv,timkiemhv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trinhdohocvanacti);
        final DatabaseHelper myDbHelper = new DatabaseHelper( this);
        lv =(ListView)findViewById(R.id.lstviewTrinhdohocvan);
        btnthem= (Button)findViewById(R.id.btnThemTDHV);
        btnsua= (Button)findViewById(R.id.btnSuaTDHV);
        btntimbh= (Button)findViewById(R.id.btnTimKiemTDHV);
        bangcaphv= (EditText)findViewById(R.id.txtThemBangcapHV);
        mall= (EditText)findViewById(R.id.txtThemMaLL);
        diemhv= (EditText)findViewById(R.id.txtThemDiemHV);
        timkiemhv= (EditText)findViewById(R.id.txtTimKiemTDHV);
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

        c = myDbHelper.query("TrinhDoHocVan",  null, null  , null, null, null, null);
        if (c.moveToFirst()) {
            do {

                arrTrinhDoHV.add( new trinhdohocvan( c.getString(0),c.getString(1),c.getString(2)));
            } while (c.moveToNext());
        }

        tdhvad = new trinhdohocvanAdapter(this,R.layout.activity_trinhdohocvancontruct,arrTrinhDoHV);
        tdhvad.notifyDataSetChanged();

        lv.setAdapter(tdhvad);


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] colString = {"BangCap","MaLL","Diem"};
                String[] paraString = {bangcaphv.getText().toString(),mall.getText().toString(),diemhv.getText().toString()};
                String result= myDbHelper.addRecord("TrinhDoHocVan",colString,null,paraString,null);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                arrTrinhDoHV.clear();
                c = myDbHelper.query("TrinhDoHocVan",  null, null  , null, null, null, null);
                if (c.moveToFirst()) {
                    do {

                        arrTrinhDoHV.add( new trinhdohocvan( c.getString(0),c.getString(1),c.getString(2)));
                    } while (c.moveToNext());
                }
                tdhvad.notifyDataSetChanged();
                lv.setAdapter(tdhvad);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] para= {bangcaphv.getText().toString(),mall.getText().toString()};
                c = myDbHelper.query("TrinhDoHocVan",  null, "BangCap = ? and MaLL= ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    myDbHelper.updateRecord("update TrinhDoHocVan set    Diem = '"+diemhv.getText().toString()+"'  where  BangCap= '"+bangcaphv.getText().toString()+"' and MaLL = '"+mall.getText().toString()+"'"  );
                    arrTrinhDoHV.clear();
                    c = myDbHelper.query("TrinhDoHocVan",  null, null  , null, null, null, null);
                    if (c.moveToFirst()) {
                        do {

                            arrTrinhDoHV.add( new trinhdohocvan( c.getString(0),c.getString(1),c.getString(2)));
                        } while (c.moveToNext());
                    }
                    tdhvad.notifyDataSetChanged();
                    lv.setAdapter(tdhvad);
                    Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Bằng cấp không tồn tại",Toast.LENGTH_LONG).show();
                }
            }
        });
        btntimbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timkiemhv.getText().toString() == null){
                    arrTrinhDoHV.clear();
                    String [] para= {timkiemhv.getText().toString()};
                    c = myDbHelper.query("TrinhDoHocVan",  null, null  , null, null, null, null);
                    if (c.moveToFirst()) {
                        do {

                            arrTrinhDoHV.add( new trinhdohocvan( c.getString(0),c.getString(1),c.getString(2)));
                        } while (c.moveToNext());
                    }
                    tdhvad.notifyDataSetChanged();
                    lv.setAdapter(tdhvad);
                }else {
                    arrTrinhDoHV.clear();
                    String [] para= {timkiemhv.getText().toString()};
                    c = myDbHelper.query("TrinhDoHocVan",  null, "BangCap = ? "  , para, null, null, null);
                    if (c.moveToFirst()) {
                        do {

                            arrTrinhDoHV.add( new trinhdohocvan( c.getString(0),c.getString(1),c.getString(2)));
                        } while (c.moveToNext());
                    }
                    tdhvad.notifyDataSetChanged();
                    lv.setAdapter(tdhvad);
                }

            }
        });
    }
}

