package com.thuannluit.customlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.thuannluit.adapter.HangHoaAdapter;
import com.thuannluit.model.HangHoa;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtMaHang, txtTenHang;
    RadioGroup radChon;
    RadioButton btnSach, btnBut;
    ImageButton btnXoa, btnLuu;

    ImageView img;

    ListView lvHangHoa;
    ArrayList<HangHoa> listHangHoa;
    HangHoaAdapter hangHoaAdapter;

    int vitri = -1;

    String maHang, tenHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {

        img = (ImageView) findViewById(R.id.img);

        txtMaHang = (EditText) findViewById(R.id.txtMaHang);
        txtTenHang = (EditText) findViewById(R.id.txtTenHang);

        btnSach = (RadioButton) findViewById(R.id.btnSach);
        btnBut = (RadioButton) findViewById(R.id.btnBut);
        radChon = (RadioGroup) findViewById(R.id.radChon);
        btnLuu = (ImageButton) findViewById(R.id.btnLuu);
        btnXoa = (ImageButton) findViewById(R.id.btnXoa);

        lvHangHoa = (ListView) findViewById(R.id.listView);

        listHangHoa = new ArrayList<>();

        listHangHoa.add(new HangHoa("s4345", "bút bi thiên long", "Bút", R.drawable.pen));
        listHangHoa.add(new HangHoa("S52ddf", "Sách LTCB tập 1", "Sách", R.drawable.book));
        listHangHoa.add(new HangHoa("B525", "Bút lông Hòa Bình", "Bút", R.drawable.pen));
        listHangHoa.add(new HangHoa("K534", "Sách khoa học máy tính", "Sách", R.drawable.book));

        hangHoaAdapter = new HangHoaAdapter(MainActivity.this, R.layout.row, listHangHoa);

        lvHangHoa.setAdapter(hangHoaAdapter);

        lvHangHoa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Bạn đã chọn :"+listHangHoa.get(position).getTenHang(),Toast.LENGTH_LONG).show();
                Log.e("TAG","Không thấy lỗi gì lum");
                capNhatDuLieu();
                return false;
            }
        });
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuuDuLieu();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXoaDuLieu();
            }
        });

        radChon.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId la id cua thang radio button dang chon
                switch (checkedId) {
                    case R.id.btnSach:
                        break;
                    case R.id.btnBut:
                        break;
                }
            }
        });

    }

    private void capNhatDuLieu() {
        maHang = txtMaHang.getText().toString();
        tenHang = txtTenHang.getText().toString();

        if (btnSach.isChecked()) {
            listHangHoa.set(vitri, new HangHoa(maHang, tenHang, "Sách", R.drawable.book));
        } else {
            listHangHoa.set(vitri, new HangHoa(maHang, tenHang, "Bút", R.drawable.pen));
        }
        hangHoaAdapter.notifyDataSetChanged();//cap nhat du lieu
        txtMaHang.setText("");
        txtTenHang.setText("");// set trang thong tin o dien
        txtMaHang.requestFocus(); // dua con tro ve edit ma hang
    }

    private void xuLyXoaDuLieu() {
        for (int i = lvHangHoa.getChildCount() - 1; i >= 0; i--) {
            View v = lvHangHoa.getChildAt(i);
            CheckBox chk = (CheckBox) v.findViewById(R.id.btnChon);
            if (chk.isChecked()) {
                listHangHoa.remove(i);
            }
        }
        hangHoaAdapter.notifyDataSetChanged();
    }

    private void xuLyLuuDuLieu() {
        maHang = txtMaHang.getText().toString();
        tenHang = txtTenHang.getText().toString();

        if (btnSach.isChecked()) {
            listHangHoa.add(new HangHoa(maHang, tenHang, "Sách", R.drawable.book));
        } else {
            listHangHoa.add(new HangHoa(maHang, tenHang, "Bút", R.drawable.pen));
        }

        hangHoaAdapter.notifyDataSetChanged();//cap nhat du lieu
        txtMaHang.setText("");
        txtTenHang.setText("");// set trang thong tin o dien
        txtMaHang.requestFocus(); // dua con tro ve edit ma hang
    }


}
