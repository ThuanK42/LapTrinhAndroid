package com.example.baitapquanlynhansu;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

import Adapter.nhanVienAdapter;
import Contructor.nhanVien;

public class nhanVienActi extends AppCompatActivity {
    ImageView img;
    ListView lv;
    ArrayList<nhanVien> arrNhanVien=  new ArrayList<nhanVien>();
    nhanVienAdapter nvad =null;
    Cursor c;
    Button btn,btnsua;
    EditText manv,tennv,mall,macv,mabh,mapb,email,sdt,hinhanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvienacti);
        final DatabaseHelper myDbHelper = new DatabaseHelper( this);
        img =(ImageView)findViewById(R.id.imageView);

        lv =(ListView)findViewById(R.id.lstviewNhanvien);
        btn= (Button)findViewById(R.id.btnThemNhanVien);
        btnsua = (Button)findViewById(R.id.btnSuaNhanVien);
        manv= (EditText)findViewById(R.id.txtNhanVienmanv);
        tennv= (EditText)findViewById(R.id.txtNhanVientennv);
        mall= (EditText)findViewById(R.id.txtNhanVienlylich);
        macv= (EditText)findViewById(R.id.txtNhanVienMacv);
        mabh= (EditText)findViewById(R.id.txtNhanVienMabh);
        mapb= (EditText)findViewById(R.id.txtNhanVienmapb);
        email= (EditText)findViewById(R.id.txtNhanVienemail);
        sdt= (EditText)findViewById(R.id.txtNhanViensdt);
        hinhanh= (EditText)findViewById(R.id.txtNhanVienduongdan);
//        hinhanh.setVisibility(View.GONE);
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
        c = myDbHelper.query("NhanVien",  null, null  , null, null, null, null);
        if (c.moveToFirst()) {
            do {
                arrNhanVien.add( new nhanVien( c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8),c.getString(9)));
            } while (c.moveToNext());
        }

        nvad = new nhanVienAdapter(this,R.layout.activity_nhan_vien,arrNhanVien);
        nvad.notifyDataSetChanged();

        lv.setAdapter(nvad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String usn = ((TextView) view.findViewById(R.id.txtUsername)).getText().toString();
//                String pass = ((TextView) view.findViewById(R.id.txtPassword)).getText().toString();
//                Toast.makeText(getApplicationContext(),    usn+ " "+ pass,Toast.LENGTH_LONG).show();
//                Intent it = new Intent(getApplicationContext(),nhanVienActi.class);
//                Bundle extras = new Bundle();
//                extras.putString("pass",pass);
//                extras.putString("usn",usn);
//                it.putExtras(extras);
//                startActivity(it);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String[] colString = {"MaNV","TenNV","MaLL","MaCV","MaPB","MaBH","SDT","Email","HinhAnh","TrangThai"};
                    String[] paraString = {manv.getText().toString(),tennv.getText().toString(),mall.getText().toString(),macv.getText().toString(),mapb.getText().toString(),mabh.getText().toString(),sdt.getText().toString(),email.getText().toString(),"''","1"};
                    String result= myDbHelper.addRecord("NhanVien",colString,null,paraString,null);
                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                    arrNhanVien.clear();
                    c = myDbHelper.query("NhanVien",  null, null  , null, null, null, null);
                    if (c.moveToFirst()) {
                        do {
                            arrNhanVien.add( new nhanVien( c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8),c.getString(9)));
                        } while (c.moveToNext());
                    }
                    nvad.notifyDataSetChanged();
                    lv.setAdapter(nvad);
                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            }
        });
    btnsua.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String [] para= {manv.getText().toString()};
            c = myDbHelper.query("NhanVien",  null, "MaNV = ?"  , para, null, null, null);
            if (c.moveToFirst()) {
                myDbHelper.updateRecord("update nhanvien set  tennv = '"+tennv.getText().toString()+"',mall= '"+mall.getText().toString()+"' , macv = '"+macv.getText().toString()+"', mapb = '"+mapb.getText().toString()+"', mabh = '"+mabh.getText().toString()+"', sdt = '"+sdt.getText().toString()+"' , email = '"+email.getText().toString()+"' , hinhanh = '"+hinhanh.getText().toString()+"'  where manv = '"+manv.getText().toString()+"'"  );
                arrNhanVien.clear();
                c = myDbHelper.query("NhanVien",  null, null  , null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        arrNhanVien.add( new nhanVien( c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8),c.getString(9)));
                    } while (c.moveToNext());
                }
                nvad.notifyDataSetChanged();
                lv.setAdapter(nvad);
                Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Mã nhân viên không tồn tại",Toast.LENGTH_LONG).show();
            }
        }
    });






    }
}
