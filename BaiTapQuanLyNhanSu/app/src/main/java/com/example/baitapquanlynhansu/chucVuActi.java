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

import Adapter.UserAdapter;
import Adapter.chucVuAdapter;
import Contructor.User;
import Contructor.chucVu;
import Contructor.nhanVien;

public class chucVuActi extends AppCompatActivity {
    ListView lv;
    ArrayList<chucVu> arrChucvu=  new ArrayList<>();
    chucVuAdapter cvad =null;
    Cursor c;
    Button btnthem,btnsua,btntimkiem;
    EditText macv,tencv,timcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chucvuacti);
        final DatabaseHelper myDbHelper = new DatabaseHelper( this);
        lv =(ListView)findViewById(R.id.lstviewChucvu);
        btnthem= (Button)findViewById(R.id.btnThemChucVu);
        btnsua= (Button)findViewById(R.id.btnSuaChucVu);
        btntimkiem= (Button)findViewById(R.id.btnTimKiemCV);
        macv= (EditText)findViewById(R.id.txtThemMaCV);
        tencv= (EditText)findViewById(R.id.txtThemTenCV);
        timcv = (EditText)findViewById(R.id.txtTimKiemCV);
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
        c = myDbHelper.query("ChucVu",  null, null  , null, null, null, null);
        if (c.moveToFirst()) {
            do {
                arrChucvu.add( new chucVu( c.getString(0),c.getString(1)));
            } while (c.moveToNext());
        }

        cvad = new chucVuAdapter(this,R.layout.activity_chucvucontruct,arrChucvu);
        cvad.notifyDataSetChanged();

        lv.setAdapter(cvad);


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] colString = {"MaCV","TenCV" };
                String[] paraString = {macv.getText().toString(),tencv.getText().toString() };
                String result= myDbHelper.addRecord("ChucVu",colString,null,paraString,null);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                arrChucvu.clear();
                c = myDbHelper.query("ChucVu",  null, null  , null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        arrChucvu.add( new chucVu( c.getString(0),c.getString(1)));
                    } while (c.moveToNext());
                }
                cvad.notifyDataSetChanged();
                lv.setAdapter(cvad);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] para= {macv.getText().toString()};
                c = myDbHelper.query("ChucVu",  null, "MaCV = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    myDbHelper.updateRecord("update chucvu set  tencv = '"+tencv.getText().toString()+"'  where macv = '"+macv.getText().toString()+"'"  );
                    arrChucvu.clear();
                    c = myDbHelper.query("ChucVu",  null, null  , null, null, null, null);
                    if (c.moveToFirst()) {
                        do {
                            arrChucvu.add( new chucVu( c.getString(0),c.getString(1)));
                        } while (c.moveToNext());
                    }
                    cvad.notifyDataSetChanged();
                    lv.setAdapter(cvad);
                    Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Mã chức vụ không tồn tại",Toast.LENGTH_LONG).show();
                }
            }
        });
        btntimkiem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            arrChucvu.clear();
            String[] selectionArgs ={timcv.getText().toString()};
            c = myDbHelper.query("ChucVu",  null, "MaCV = ?"  , selectionArgs, null, null, null);
            if (c.moveToFirst()) {
                do {
                    arrChucvu.add( new chucVu( c.getString(0),c.getString(1)));
                } while (c.moveToNext());
            }
            cvad.notifyDataSetChanged();
            lv.setAdapter(cvad);
        }
        });
    }
}
