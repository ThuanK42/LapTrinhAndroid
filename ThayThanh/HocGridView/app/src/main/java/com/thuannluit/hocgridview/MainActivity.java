package com.thuannluit.hocgridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.thuannluit.adapter.HinhAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gvHinh;
    ArrayList<Integer> dsHinh;
    HinhAdapter adapter;

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
        gvHinh = (GridView) findViewById(R.id.gvHinh);

        dsHinh = new ArrayList<>();
        dsHinh.add(R.drawable.bau);
        dsHinh.add(R.drawable.cua);
        dsHinh.add(R.drawable.tom);
        dsHinh.add(R.drawable.ca);
        dsHinh.add(R.drawable.ga);
        dsHinh.add(R.drawable.nai);

        adapter= new HinhAdapter(MainActivity.this,R.layout.item,dsHinh);

        gvHinh.setAdapter(adapter);
    }
}
