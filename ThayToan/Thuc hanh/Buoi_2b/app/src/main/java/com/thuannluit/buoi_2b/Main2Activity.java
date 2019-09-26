package com.thuannluit.buoi_2b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    TextView txtKetQua;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        btnBack = (Button) findViewById(R.id.btnTroLai);
        txtKetQua = (TextView) findViewById(R.id.txtKetQua);

        Intent callerIntent = getIntent();

        Bundle packageFromCaller =
                callerIntent.getBundleExtra("Result");

        int a = packageFromCaller.getInt("soa");
        int b = packageFromCaller.getInt("sob");
        int c = packageFromCaller.getInt("soc");

        giaipt(a, b, c);
    }

    private void giaipt(int a, int b, int c) {
        String kq = "";
        double delta = b * b - 4 * a * c;
        if (delta < 0) {
            kq = "Phương trình vô nghiệm";
        } else if (delta == 0) {
            kq = "Phương trình có nghiệm kép x1=x2= " + (double) ((-b) / (2 * a));
        } else if(delta>0){
            kq = "Phương trình có 2 nghiệm phân biệt: x1= "
                    + (double) ((-b + Math.sqrt(delta)) / (2 * a))
                    + " và x2= "
                    + (double) ((-b - Math.sqrt(delta)) / (2 * a));
        }
        txtKetQua.setText(kq);
    }
}
