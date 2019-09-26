package com.thuannluit.buoi_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenGiaoDien2();
            }
        });
    }

    private void chuyenGiaoDien2() {
        Intent myIntent=new Intent(MainActivity.this, Main2Activity.class);
        startActivity(myIntent);
    }

    private void addControls() {
        btnGo = (Button) findViewById(R.id.btn1);
    }
}
