package com.thuannluit.learnsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thuannluit.adapter.ClassroomAdapter;
import com.thuannluit.database.DBManager;
import com.thuannluit.model.Classroom;

import java.util.ArrayList;

public class ClassroomManagerActivity extends AppCompatActivity implements View.OnClickListener {

    private static ImageButton btn_back, btn_next, btn_add, btn_edit, btn_del, btn_load;
    private static EditText txt_malop, txt_tenlop, txt_siso;

    ListView lvClassroom;
    ArrayList<Classroom> listClassroom;
    ClassroomAdapter classroomAdapter;

    final DBManager dbManager = new DBManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_manager);
        addControls();
        addEvents();
    }

    private void addControls() {
        btn_back = findViewById(R.id.btn_back);
        btn_next = findViewById(R.id.btn_next);

        txt_malop = findViewById(R.id.txt_malop);
        txt_tenlop = findViewById(R.id.txt_tenlop);
        txt_siso = findViewById(R.id.txt_siso);

        btn_add = findViewById(R.id.btn_ins_lop);
        btn_del = findViewById(R.id.btn_del_lop);
        btn_edit = findViewById(R.id.btn_edit_lop);
        btn_load = findViewById(R.id.btn_load_lop);

        lvClassroom = findViewById(R.id.lv_danhsachlop);

    }

    private void addEvents() {
        btn_back.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_edit.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_back:
                screen_switch();
                break;

            case R.id.btn_next:
                Intent intent2 = new Intent(ClassroomManagerActivity.this, ManagerStudentActivity.class);
                startActivity(intent2);
                break;

            case R.id.btn_ins_lop:

                String malop = txt_malop.getText().toString().toLowerCase().trim();
                String tenlop = txt_tenlop.getText().toString().toLowerCase().trim();
                int siso = Integer.valueOf(txt_siso.getText().toString().trim());
                if (malop.isEmpty() || tenlop.isEmpty() || String.valueOf(siso).isEmpty() || siso < 0) {
                    Toast.makeText(this, "please enter all information or correct format", Toast.LENGTH_SHORT).show();
                } else {
                    dbManager.ins_class(this, malop, tenlop, siso);

                }

                break;

            case R.id.btn_edit_lop:
                String malop2 = txt_malop.getText().toString().toLowerCase().trim();
                String tenlop2 = txt_tenlop.getText().toString().toLowerCase().trim();
                int siso2 = Integer.valueOf(txt_siso.getText().toString().trim());
                if (malop2.isEmpty() || tenlop2.isEmpty() || String.valueOf(siso2).isEmpty() || siso2 < 0) {
                    Toast.makeText(this, "please enter all information or correct format", Toast.LENGTH_SHORT).show();
                } else {
                    dbManager.updateClass(this, malop2, tenlop2, siso2);
                }

                break;

            case R.id.btn_del_lop:
                String malop3 = txt_malop.getText().toString().toLowerCase().trim();
                if (malop3.isEmpty()) {
                    Toast.makeText(this, "please enter all information or correct format", Toast.LENGTH_SHORT).show();
                } else {
                    dbManager.del_class(this, malop3);
                }

                break;

            case R.id.btn_load_lop:
                if ((listClassroom = dbManager.loadAllClass(this)) == null) {
                } else {
                    listClassroom = dbManager.loadAllClass(this);
                    classroomAdapter = new ClassroomAdapter(ClassroomManagerActivity.this,
                            R.layout.item_classroom,
                            listClassroom);
                    lvClassroom.setAdapter(classroomAdapter);
                }
                break;
        }
    }

    private void screen_switch() {
        Intent intent = new Intent(ClassroomManagerActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
