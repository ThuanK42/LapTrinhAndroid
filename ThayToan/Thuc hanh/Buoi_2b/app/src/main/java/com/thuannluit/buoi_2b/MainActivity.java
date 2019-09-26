package com.thuannluit.buoi_2b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editA, editB, editC;
    ImageButton btnTinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(editA.getText().toString());
                int b = Integer.parseInt(editB.getText().toString());
                int c = Integer.parseInt(editC.getText().toString());

                if (a != 0 && editA.getText() != null && editB.getText() != null && editC.getText() != null) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                    Bundle bundle = new Bundle();

                    bundle.putInt("soa", a);
                    Log.e("a",""+a);
                    bundle.putInt("sob", b);
                    Log.e("b",""+b);
                    bundle.putInt("soc", c);
                    Log.e("c",""+c);

                    intent.putExtra("Result", bundle);

                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Nhập đầy đủ thông tin đi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addControls() {
        editA = (EditText) findViewById(R.id.editA);
        editB = (EditText) findViewById(R.id.editB);
        editC = (EditText) findViewById(R.id.editC);
        btnTinh = (ImageButton) findViewById(R.id.btnTinh);
    }
}
