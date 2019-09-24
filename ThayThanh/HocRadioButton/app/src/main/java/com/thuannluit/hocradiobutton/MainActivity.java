package com.thuannluit.hocradiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton rad1Star, rad2Star, rad3Star, rad4Star, rad5Star;
    Button btnBinhChon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }

    private void addControls() {
        rad1Star = (RadioButton) findViewById(R.id.rad1Star);
        rad2Star = (RadioButton) findViewById(R.id.rad2Star);
        rad3Star = (RadioButton) findViewById(R.id.rad3Star);
        rad4Star = (RadioButton) findViewById(R.id.rad4Star);
        rad5Star = (RadioButton) findViewById(R.id.rad5Star);
        btnBinhChon = (Button) findViewById(R.id.btnBinhChon);
    }

    private void addEvents() {
        btnBinhChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyLyBinhChon();
            }
        });
    }

    private void xyLyBinhChon() {
        String s="";
        if (rad1Star.isChecked()){
            s=rad1Star.getText().toString();
        }else if (rad2Star.isChecked()){
            s=rad2Star.getText().toString();
        }
        else if (rad3Star.isChecked()){
            s=rad3Star.getText().toString();
        }
        else if (rad4Star.isChecked()){
            s=rad4Star.getText().toString();
        }
        else if (rad5Star.isChecked()){
            s=rad5Star.getText().toString();
        }
        Toast.makeText(MainActivity.this,"Ban chon: "+s,Toast.LENGTH_LONG).show();
    }
}
