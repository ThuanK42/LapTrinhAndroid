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

import Adapter.UserAdapter;
import Adapter.baoHiemAdapter;
import Adapter.chucVuAdapter;
import Contructor.User;
import Contructor.baoHiem;
import Contructor.chucVu;
import Contructor.nhanVien;

public class baoHiemActi extends AppCompatActivity {
    ListView lv;
    ArrayList<baoHiem> arrBaohiem=  new ArrayList<>();
    baoHiemAdapter bhad =null;
    Cursor c;
    Button btnthem,btnsua,btntimbh;;
    EditText mabh,tenbh,tungay,denngay,timkiembh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baohiemacti);
        final DatabaseHelper myDbHelper = new DatabaseHelper( this);
        lv =(ListView)findViewById(R.id.lstviewBH);
        btnthem= (Button)findViewById(R.id.btnThemBH);
        btnsua= (Button)findViewById(R.id.btnSuaBH);
        btntimbh= (Button)findViewById(R.id.btnTimKiemBH);
        mabh= (EditText)findViewById(R.id.txtThemMaBH);
        tenbh= (EditText)findViewById(R.id.txtThemTenBH);
        tungay= (EditText)findViewById(R.id.txtThemBHTuNgay);
        denngay= (EditText)findViewById(R.id.txtThemBHDenNgay);
        timkiembh= (EditText)findViewById(R.id.txtTimKiemBH);
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
       final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy" );

        c = myDbHelper.query("BaoHiem",  null, null  , null, null, null, null);
        if (c.moveToFirst()) {
            do {

                arrBaohiem.add( new baoHiem( c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
            } while (c.moveToNext());
        }

        bhad = new baoHiemAdapter(this,R.layout.activity_baohiemcontruct,arrBaohiem);
        bhad.notifyDataSetChanged();

        lv.setAdapter(bhad);


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] colString = {"MaBH","TenBH","TuNgayBH","DenNgayBH" };
                String[] paraString = {mabh.getText().toString(),tenbh.getText().toString(),tungay.getText().toString(),denngay.getText().toString() };
                String result= myDbHelper.addRecord("BaoHiem",colString,null,paraString,null);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                arrBaohiem.clear();
                c = myDbHelper.query("BaoHiem",  null, null  , null, null, null, null);
                if (c.moveToFirst()) {
                    do {

                        arrBaohiem.add( new baoHiem( c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
                    } while (c.moveToNext());
                }
                bhad.notifyDataSetChanged();
                lv.setAdapter(bhad);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] para= {mabh.getText().toString()};
                c = myDbHelper.query("BaoHiem",  null, "MaBH = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    myDbHelper.updateRecord("update BaoHiem set  tenbh = '"+tenbh.getText().toString()+"', tungaybh = '"+tungay.getText().toString()+"', denngaybh = '"+denngay.getText().toString()+"'  where mabh = '"+mabh.getText().toString()+"' and tungaybh = '"+tungay.getText().toString()+"'"  );
                    arrBaohiem.clear();
                    c = myDbHelper.query("BaoHiem",  null, null  , null, null, null, null);
                    if (c.moveToFirst()) {
                        do {

                            arrBaohiem.add( new baoHiem( c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
                        } while (c.moveToNext());
                    }
                    bhad.notifyDataSetChanged();
                    lv.setAdapter(bhad);
                    Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Mã bảo hiểm không tồn tại",Toast.LENGTH_LONG).show();
                }
            }
        });
        tungay.setFocusable(false);
        denngay.setFocusable(false);
        tungay.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        datePicker(tungay);
    }
});
        denngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(denngay);
            }
        });
        btntimbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrBaohiem.clear();
                String [] para= {timkiembh.getText().toString()};
                c = myDbHelper.query("BaoHiem",  null, "MaBH = ?"  , para, null, null, null);
                if (c.moveToFirst()) {
                    do {

                        arrBaohiem.add( new baoHiem( c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
                    } while (c.moveToNext());
                }
                bhad.notifyDataSetChanged();
                lv.setAdapter(bhad);
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
