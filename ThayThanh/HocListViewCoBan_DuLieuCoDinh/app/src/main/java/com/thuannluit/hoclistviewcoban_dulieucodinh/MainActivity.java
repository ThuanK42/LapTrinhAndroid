package com.thuannluit.hoclistviewcoban_dulieucodinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lvLich;
    String[] arrLich;
    ArrayAdapter<String> adapterLich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvLich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Ban chon [" + arrLich[position] + "]", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addControls() {
        arrLich = getResources().getStringArray(R.array.Lich);
        adapterLich = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrLich);
        lvLich = (ListView) findViewById(R.id.lvLich);
        lvLich.setAdapter(adapterLich);
    }
}
