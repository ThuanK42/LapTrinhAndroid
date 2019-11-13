package com.thuannluit.learnsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thuannluit.database.DBManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_create_database, btn_delete_database, btn_create_table, btn_del_table;
    private ImageButton btn_next;
    private RadioButton rad_btn_tbllop, rad_btn_tblsinhvien;
    final DBManager dbManager = new DBManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btn_create_database.setOnClickListener(this);
        btn_delete_database.setOnClickListener(this);
        btn_create_table.setOnClickListener(this);
        btn_del_table.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    private void addControls() {
        btn_create_database = findViewById(R.id.btn_create_database);
        btn_delete_database = findViewById(R.id.btn_delete_database);
        btn_create_table = findViewById(R.id.btn_create_table);
        btn_del_table = findViewById(R.id.btn_del_table);
        rad_btn_tbllop = findViewById(R.id.rad_btn_tbllop);
        rad_btn_tblsinhvien = findViewById(R.id.rad_btn_tblsinhvien);
        btn_next = findViewById(R.id.btn_next);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                screen_switch();
                break;
            case R.id.btn_create_database:
                dbManager.createDatabase(this);
                Toast.makeText(this, "Successfully Imported", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete_database:
                dbManager.deleteDataBase(this);
                break;
            case R.id.btn_create_table:
                new DBManager().createTable(this);
                break;
            case R.id.btn_del_table:
                if (rad_btn_tbllop.isChecked()) {
                    dbManager.deleteTable_lop(this);
                } else if (rad_btn_tblsinhvien.isChecked()) {
                    dbManager.deleteTable_sinhvien(this);
                }
                break;
        }
    }

    private void screen_switch() {
        Intent intent = new Intent(MainActivity.this, ClassroomManagerActivity.class);
        startActivity(intent);
    }
}
