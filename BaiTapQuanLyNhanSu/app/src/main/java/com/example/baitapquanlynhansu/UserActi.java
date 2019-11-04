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
import Contructor.User;

public class UserActi extends AppCompatActivity {
    ListView lv;
    ArrayList<User> arrUser=  new ArrayList<User>();
    UserAdapter usad =null;
    Cursor c;
    Button btn,btnsua,btntimkiem;
    EditText tk,mk,timkiemus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        final DatabaseHelper myDbHelper = new DatabaseHelper( this);
      lv =(ListView)findViewById(R.id.lstviewUser);
      btn= (Button)findViewById(R.id.btnThemTKDN);
        btnsua= (Button)findViewById(R.id.btnSuaUser);
        btntimkiem = (Button)findViewById(R.id.btnTimKiemUser);
      tk= (EditText)findViewById(R.id.txtThemTK);
      mk= (EditText)findViewById(R.id.txtThemMK);
      timkiemus=(EditText)findViewById(R.id.txtTimKiemUser);

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
        c = myDbHelper.query("TaiKhoanDangNhap",  null, null  , null, null, null, null);
        if (c.moveToFirst()) {
             do {
                 arrUser.add( new User( c.getString(0),c.getString(1)));
             } while (c.moveToNext());
        }

        usad = new UserAdapter(this,R.layout.activity_users,arrUser);
//        usad.notifyDataSetChanged();

        lv.setAdapter(usad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String usn = ((TextView) view.findViewById(R.id.txtUsername)).getText().toString();
                String pass = ((TextView) view.findViewById(R.id.txtPassword)).getText().toString();
                Toast.makeText(getApplicationContext(),    usn+ " "+ pass,Toast.LENGTH_LONG).show();
                Intent it = new Intent(getApplicationContext(),nhanVienActi.class);
                Bundle extras = new Bundle();
                extras.putString("pass",pass);
                extras.putString("usn",usn);
                it.putExtras(extras);
                startActivity(it);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String[] colString = {"MaNV","MatKhau","TrangThai"};
                    String[] paraString = {tk.getText().toString(),mk.getText().toString(),"1"};
                    String result= myDbHelper.addRecord("TaiKhoanDangNhap",colString,null,paraString,null);
                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                arrUser.clear();
                c = myDbHelper.query("TaiKhoanDangNhap",  null, null  , null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        arrUser.add( new User( c.getString(0),c.getString(1)));
                    } while (c.moveToNext());
                }
                usad.notifyDataSetChanged();
                lv.setAdapter(usad);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] para= {tk.getText().toString()};
                c = myDbHelper.query("TaiKhoanDangNhap",  null, "MaNV = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    myDbHelper.updateRecord("update TaiKhoanDangNhap set  matkhau = '"+mk.getText().toString()+"'  where manv = '"+tk.getText().toString()+"'"  );
                    arrUser.clear();
                    c = myDbHelper.query("TaiKhoanDangNhap",  null, null  , null, null, null, null);
                    if (c.moveToFirst()) {
                        do {
                            arrUser.add( new User( c.getString(0),c.getString(1)));
                        } while (c.moveToNext());
                    }
                    usad.notifyDataSetChanged();
                    lv.setAdapter(usad);
                    Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Mã người dùng  không tồn tại",Toast.LENGTH_LONG).show();
                }
            }
        });
        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrUser.clear();
                String [] para= {timkiemus.getText().toString()};

                c = myDbHelper.query("TaiKhoanDangNhap",  null, "MaNV = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        arrUser.add( new User( c.getString(0),c.getString(1)));
                    } while (c.moveToNext());
                }
                usad.notifyDataSetChanged();
                lv.setAdapter(usad);

            }
        });
    }
}
