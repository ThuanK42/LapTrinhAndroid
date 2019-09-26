package com.thuannluit.hoctabselector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtA, txtB;
    Button btnCong;

    ListView lvHistory;
    ArrayList<String> dsCong;
    ArrayAdapter<String> adapterCong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyPhepCong();
            }
        });
    }

    private void xuLyPhepCong() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtA.getText().toString());
        String c = a+" + "+b+" = "+(a+b);
        dsCong.add(c);
        adapterCong.notifyDataSetChanged();
        txtA.setText("");
        txtB.setText("");

    }

    private void addControls() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        //  tab1.setIndicator("1.Phep Cong");
        tab1.setIndicator("", getResources().getDrawable(R.drawable.add));
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        // tab2.setIndicator("2.Lich Su");
        tab2.setIndicator("", getResources().getDrawable(R.drawable.history));
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);

        txtA = (EditText) findViewById(R.id.txtNhapa);
        txtB = (EditText) findViewById(R.id.txtNhapb);
        btnCong = (Button) findViewById(R.id.btnCong);
        lvHistory = (ListView) findViewById(R.id.lvHistory);

        dsCong = new ArrayList<>();

        adapterCong = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                dsCong);
        lvHistory.setAdapter(adapterCong);

    }
}
