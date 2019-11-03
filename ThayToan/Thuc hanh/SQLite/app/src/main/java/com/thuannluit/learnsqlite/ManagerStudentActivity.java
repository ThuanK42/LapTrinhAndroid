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

    ImageButton btn_back, btn_add_student, btn_load, btn_delete_student, btn_edit_student;
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
        btn_edit_student = findViewById(R.id.btn_update_sv);
        btn_delete_student = findViewById(R.id.btn_del_sv);
        btn_load = findViewById(R.id.btn_load_sv);

        txt_masv = findViewById(R.id.txt_masv);
        txt_tensv = findViewById(R.id.txt_tensv);
        txt_malop = findViewById(R.id.txt_malop);

        lv_danhsachsv = findViewById(R.id.lv_danhsachsv);
    }

    private void addEvents() {
        btn_back.setOnClickListener(this);
        btn_add_student.setOnClickListener(this);
        btn_edit_student.setOnClickListener(this);
        btn_delete_student.setOnClickListener(this);
        btn_load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_back:
                screen_switch();
                break;

            case R.id.btn_ins_sv:
                String ma_sv_1 = txt_masv.getText().toString().toLowerCase().trim();
                String tensv = txt_tensv.getText().toString().toLowerCase().trim();
                String malop = txt_malop.getText().toString().toLowerCase().trim();
                if (ma_sv_1.isEmpty() || tensv.isEmpty() || malop.isEmpty()) {
                    Toast.makeText(this, "please enter all information or correct format", Toast.LENGTH_SHORT).show();
                } else {
                    dbManager.ins_student(this, ma_sv_1, tensv, malop);
                }
                break;

            case R.id.btn_update_sv:
                String ma_sv_2 = txt_masv.getText().toString().toLowerCase().trim();
                String ten_sv_2 = txt_tensv.getText().toString().toLowerCase().trim();
                if (ma_sv_2.isEmpty() || ten_sv_2.isEmpty()) {
                    Toast.makeText(this, "please enter all information or correct format", Toast.LENGTH_SHORT).show();
                } else {
                    dbManager.updateStudent(this, ma_sv_2, ten_sv_2);
                }
                break;

            case R.id.btn_del_sv:
                String ma_sv_3 = txt_masv.getText().toString().toLowerCase().trim();
                if (ma_sv_3.isEmpty()) {
                    Toast.makeText(this, "please enter all information or correct format", Toast.LENGTH_SHORT).show();
                } else {
                    dbManager.deleteStudent(this, ma_sv_3);
                }
                break;

            case R.id.btn_load_sv:
                String ten_sv_4 = txt_tensv.getText().toString().toLowerCase().trim();
                String ma_sv_4 = txt_masv.getText().toString().toLowerCase();
                String ma_lop_4 = txt_malop.getText().toString().toLowerCase();
                if (ma_lop_4.isEmpty() && ma_sv_4.isEmpty() && ten_sv_4.isEmpty()) {
                    Toast.makeText(this, "Please fill 1 of the list filter conditions", Toast.LENGTH_SHORT).show();
                } else {
                    if (!ma_sv_4.isEmpty()) {
                        if ((studentArrayList = dbManager.findStudentByIDStudent(this, ma_sv_4)) == null) {
                        } else {
                            studentArrayList = dbManager.findStudentByIDStudent(this, ma_sv_4);
                            studentAdapter = new StudentAdapter(ManagerStudentActivity.this,
                                    R.layout.item_student,
                                    studentArrayList);
                            lv_danhsachsv.setAdapter(studentAdapter);
                        }
                    }
                    if (ma_sv_4.isEmpty() && ten_sv_4.isEmpty() && !(ma_lop_4.isEmpty())) {
                        if ((studentArrayList = dbManager.loadAllStudentByMaLop(this, ma_lop_4)) == null) {
                        } else {
                            studentArrayList = dbManager.loadAllStudentByMaLop(this, ma_lop_4);
                            studentAdapter = new StudentAdapter(ManagerStudentActivity.this,
                                    R.layout.item_student,
                                    studentArrayList);
                            lv_danhsachsv.setAdapter(studentAdapter);
                        }
                    }
                    if (ma_sv_4.isEmpty() && ma_lop_4.isEmpty() && !(ten_sv_4.isEmpty())) {
                        if ((studentArrayList = dbManager.findStudentByNameStudent(this, ten_sv_4)) == null) {
                        } else {
                            studentArrayList = dbManager.findStudentByNameStudent(this, ten_sv_4);
                            studentAdapter = new StudentAdapter(ManagerStudentActivity.this,
                                    R.layout.item_student,
                                    studentArrayList);
                            lv_danhsachsv.setAdapter(studentAdapter);
                        }
                    }
                }
                break;
        }
    }

    private void screen_switch() {
        Intent intent = new Intent(ManagerStudentActivity.this, ClassroomManagerActivity.class);
        startActivity(intent);
    }

}
