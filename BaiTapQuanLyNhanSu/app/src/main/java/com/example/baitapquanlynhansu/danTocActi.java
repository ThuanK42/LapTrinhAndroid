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

import Adapter.danTocAdapter;
import Contructor.danToc;

public class danTocActi extends AppCompatActivity {

    ListView lv;
    ArrayList<danToc> arrDantoc=  new ArrayList<>();
    danTocAdapter dtad =null;
    Cursor c;
    Button btnthem,btnsua,btntimkiem;
    EditText madt,tendt,timkiemdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dantocacti);
        final DatabaseHelper myDbHelper = new DatabaseHelper( this);
        lv =(ListView)findViewById(R.id.lstviewDantoc);
        btnthem= (Button)findViewById(R.id.btnThemDT);
        btnsua= (Button)findViewById(R.id.btnSuaDT);
        btntimkiem= (Button)findViewById(R.id.btnTimKiemDT);
        madt = (EditText)findViewById(R.id.txtThemMaDT);
        tendt= (EditText)findViewById(R.id.txtThemTenDT);
        timkiemdt=(EditText)findViewById(R.id.txtTimKiemDT);
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
        c = myDbHelper.query("DanToc",  null, null  , null, null, null, null);
        if (c.moveToFirst()) {
            do {
                arrDantoc.add( new danToc( c.getString(0),c.getString(1)));
            } while (c.moveToNext());
        }

        dtad = new danTocAdapter(this,R.layout.activity_dantoccontruct,arrDantoc);
        dtad.notifyDataSetChanged();

        lv.setAdapter(dtad);


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] colString = {"MaDT","TenDT" };
                String[] paraString = {madt.getText().toString(),tendt.getText().toString() };
                String result= myDbHelper.addRecord("DanToc",colString,null,paraString,null);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                arrDantoc.clear();
                c = myDbHelper.query("DanToc",  null, null  , null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        arrDantoc.add( new danToc( c.getString(0),c.getString(1)));
                    } while (c.moveToNext());
                }
                dtad.notifyDataSetChanged();
                lv.setAdapter(dtad);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] para= {madt.getText().toString()};
                c = myDbHelper.query("DanToc",  null, "MaDT = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    myDbHelper.updateRecord("update DanToc set  tendt = '"+tendt.getText().toString()+"'  where madt = '"+madt.getText().toString()+"'"  );
                    arrDantoc.clear();
                    c = myDbHelper.query("DanToc",  null, null  , null, null, null, null);
                    if (c.moveToFirst()) {
                        do {
                            arrDantoc.add( new danToc( c.getString(0),c.getString(1)));
                        } while (c.moveToNext());
                    }
                    dtad.notifyDataSetChanged();
                    lv.setAdapter(dtad);
                    Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Mã dân tộc không tồn tại",Toast.LENGTH_LONG).show();
                }
            }
        });

        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrDantoc.clear();
                String [] para= {madt.getText().toString()};

                c = myDbHelper.query("DanToc",  null, "MaDT = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        arrDantoc.add( new danToc( c.getString(0),c.getString(1)));
                    } while (c.moveToNext());
                }
                dtad.notifyDataSetChanged();
                lv.setAdapter(dtad);

            }
        });




    }
}
