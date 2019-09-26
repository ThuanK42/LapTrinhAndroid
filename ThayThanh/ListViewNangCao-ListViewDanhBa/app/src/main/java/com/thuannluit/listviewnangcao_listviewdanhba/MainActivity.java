package com.thuannluit.listviewnangcao_listviewdanhba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.thuannluit.adapter.DanhBaAdapter;
import com.thuannluit.model.DanhBa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvDanhBa;
    ArrayList<DanhBa> dsDanhBa;
    DanhBaAdapter danhBaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
        dsDanhBa = new ArrayList<>();
        dsDanhBa.add(new DanhBa(1, "Nhà", "0966616269"));
        dsDanhBa.add(new DanhBa(2, "Lê Hoàng", "0354851515"));
        dsDanhBa.add(new DanhBa(3, "Hồ Thị Mỹ Trang", "0968976201"));
        dsDanhBa.add(new DanhBa(4, "Trần Viết Sơn", "0932191045"));
        dsDanhBa.add(new DanhBa(5, "Nguyễn Văn Quang", "0968104244"));
        dsDanhBa.add(new DanhBa(6, "Nguyễn Hiếu", "0344704400"));
        dsDanhBa.add(new DanhBa(7, "Tô Thanh Sang", "0349103131"));

        danhBaAdapter = new DanhBaAdapter(MainActivity.this, R.layout.item, dsDanhBa);

        lvDanhBa.setAdapter(danhBaAdapter);
    }
}
