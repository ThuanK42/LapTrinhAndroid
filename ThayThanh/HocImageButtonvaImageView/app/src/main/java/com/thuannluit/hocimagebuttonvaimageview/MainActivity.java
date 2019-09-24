package com.thuannluit.hocimagebuttonvaimageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton radAnime,radAnhCho1,radAnhCho2;
    ImageView imgHinhAnh;
    ImageButton btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        radAnime=(RadioButton) findViewById(R.id.radAnime);
        radAnhCho1=(RadioButton) findViewById(R.id.radAnhCho1);
        radAnhCho2=(RadioButton) findViewById(R.id.radAnhCho2);
        imgHinhAnh=(ImageView) findViewById(R.id.imgHinhAnh);
        btnThoat=(ImageButton) findViewById(R.id.btnThoat);

    }
    private void addEvents() {
        radAnime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imgHinhAnh.setImageResource(R.drawable.anime);
                }
            }
        });
        radAnhCho1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imgHinhAnh.setImageResource(R.drawable.dog);
                }
            }
        });
        radAnhCho2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imgHinhAnh.setImageResource(R.drawable.dog2);
                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
