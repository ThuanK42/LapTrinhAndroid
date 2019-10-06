package com.thuannluit.myapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText txtA, txtB;
    Button btnXuLy;
    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnXuLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLayUSCLN();
            }
        });
    }

    private void xuLyLayUSCLN() {
        Intent intent = new Intent(MainActivity.this,ManHinhXuLyActivity.class);
        intent.putExtra("a",Integer.parseInt(txtA.getText().toString()));
        intent.putExtra("b",Integer.parseInt(txtB.getText().toString()));
        startActivityForResult(intent,99);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode==33){
            txtKetQua.setText("USCLN : " +data.getIntExtra("USCLN",1));
        }
    }

    private void addControls() {
        txtA = findViewById(R.id.txtA);
        txtB = findViewById(R.id.txtB);
        btnXuLy = findViewById(R.id.btnXuLy);
        txtKetQua = findViewById(R.id.txtKetQua);
    }
}
