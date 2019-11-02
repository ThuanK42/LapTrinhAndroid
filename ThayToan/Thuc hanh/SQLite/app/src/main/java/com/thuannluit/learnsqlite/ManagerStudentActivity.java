package com.thuannluit.learnsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thuannluit.adapter.StudentAdapter;
import com.thuannluit.database.DBManager;
import com.thuannluit.model.Student;

import java.util.ArrayList;

public class ManagerStudentActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn_back, btn_add_student, btn_load;
    EditText txt_masv, txt_tensv, txt_malop;

    ListView lv_danhsachsv;
    ArrayList<Student> studentArrayList;
    StudentAdapter studentAdapter;

    final DBManager dbManager = new DBManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_student);
        addControls();
        addEvents();
    }

    private void addControls() {
        btn_back = findViewById(R.id.btn_back);
        btn_add_student = findViewById(R.id.btn_ins_sv);
        btn_load = findViewById(R.id.btn_load_sv);

        txt_masv = findViewById(R.id.txt_masv);
        txt_tensv = findViewById(R.id.txt_tensv);
        txt_malop = findViewById(R.id.txt_malop);

        lv_danhsachsv = findViewById(R.id.lv_danhsachsv);
    }

    private void addEvents() {
        btn_back.setOnClickListener(this);
        btn_add_student.setOnClickListener(this);
        btn_load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                screen_switch();
                break;
            case R.id.btn_ins_sv:

                String masv = txt_masv.getText().toString().toLowerCase();
                String tensv = txt_tensv.getText().toString().toLowerCase();
                String malop = txt_malop.getText().toString().toLowerCase();
                if (masv.isEmpty() || tensv.isEmpty() || malop.isEmpty()) {
                    Toast.makeText(this, "please enter all information or correct format", Toast.LENGTH_SHORT).show();
                } else {
                    dbManager.ins_student(this, masv, tensv, malop);

                }

                break;
            case R.id.btn_load_sv:

                if ((studentArrayList = dbManager.loadAllStudent(this)) == null) {

                } else {
                    studentArrayList = dbManager.loadAllStudent(this);
                    studentAdapter = new StudentAdapter(ManagerStudentActivity.this,
                            R.layout.item_student,
                            studentArrayList);
                    lv_danhsachsv.setAdapter(studentAdapter);
                }
                break;
        }
    }

    private void screen_switch() {
        Intent intent = new Intent(ManagerStudentActivity.this, ClassroomManagerActivity.class);
        startActivity(intent);
    }


}
