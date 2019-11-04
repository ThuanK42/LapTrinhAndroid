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
import Adapter.phongBanAdapter;
import Contructor.User;
import Contructor.chucVu;
import Contructor.nhanVien;
import Contructor.phongBan;

public class phongBanActi extends AppCompatActivity {
    ListView lv;
    ArrayList<phongBan> arrPhongban=  new ArrayList<>();
    phongBanAdapter pbad =null;
    Cursor c;
    Button btnthem,btnsua,btntimkiem;
    EditText mapb,tenpb,timkiemph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phongbanacti);
        final DatabaseHelper myDbHelper = new DatabaseHelper( this);
        lv =(ListView)findViewById(R.id.lstviewPhongban);
        btnthem= (Button)findViewById(R.id.btnThemPB);
        btnsua= (Button)findViewById(R.id.btnSuaPB);
        btntimkiem= (Button)findViewById(R.id.btnTimKiemPB);
        mapb= (EditText)findViewById(R.id.txtThemMaPB);
        tenpb= (EditText)findViewById(R.id.txtThemTenPB);
        timkiemph=(EditText)findViewById(R.id.txtTimKiemPB);
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
        c = myDbHelper.query("PhongBan",  null, null  , null, null, null, null);
        if (c.moveToFirst()) {
            do {
                arrPhongban.add( new phongBan( c.getString(0),c.getString(1)));
            } while (c.moveToNext());
        }

        pbad = new phongBanAdapter(this,R.layout.activity_phongbancontruct,arrPhongban);
        pbad.notifyDataSetChanged();

        lv.setAdapter(pbad);


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] colString = {"MaPB","TenPB" };
                String[] paraString = {mapb.getText().toString(),tenpb.getText().toString() };
                String result= myDbHelper.addRecord("PhongBan",colString,null,paraString,null);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                arrPhongban.clear();
                c = myDbHelper.query("PhongBan",  null, null  , null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        arrPhongban.add( new phongBan( c.getString(0),c.getString(1)));
                    } while (c.moveToNext());
                }
                pbad.notifyDataSetChanged();
                lv.setAdapter(pbad);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] para= {mapb.getText().toString()};

                c = myDbHelper.query("PhongBan",  null, "MaPB = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    myDbHelper.updateRecord("update PhongBan set  tenpb = '"+tenpb.getText().toString()+"'  where mapb = '"+mapb.getText().toString()+"'"  );

                            arrPhongban.clear();
                            c = myDbHelper.query("PhongBan",  null, null  , null, null, null, null);
                            if (c.moveToFirst()) {
                                do {
                                    arrPhongban.add( new phongBan( c.getString(0),c.getString(1)));
                                } while (c.moveToNext());
                            }
                            lv.setAdapter(pbad);

                    Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Mã phòng ban không tồn tại",Toast.LENGTH_LONG).show();
                }
            }
        });

        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrPhongban.clear();
                String [] para= {mapb.getText().toString()};

                c = myDbHelper.query("PhongBan",  null, "MaPB = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        arrPhongban.add( new phongBan( c.getString(0),c.getString(1)));
                    } while (c.moveToNext());
                }
                pbad.notifyDataSetChanged();
                lv.setAdapter(pbad);

            }
        });




    }
}
