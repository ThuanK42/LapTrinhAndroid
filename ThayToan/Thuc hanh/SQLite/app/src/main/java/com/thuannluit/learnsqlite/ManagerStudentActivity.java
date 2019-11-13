package com.thuannluit.learnsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thuannluit.adapter.StudentAdapter;
import com.thuannluit.database.DBManager;
import com.thuannluit.model.Student;

import java.util.ArrayList;

public class ManagerStudentActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    ImageButton btn_back, btn_add_student, btn_load, btn_edit_student;
    EditText txt_masv, txt_tensv, txt_malop;

    ListView lv_danhsachsv;
    ArrayList<Student> studentArrayList;
    StudentAdapter studentAdapter;

    Spinner spinner;
    String ma_so_lop;
    ArrayList<String> danhSachLop;
    ArrayAdapter<String> adapter;

    final DBManager dbManager = new DBManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_student);
        addControls();
        addEvents();
        get_ID_Class(spinner);
    }

    private void addControls() {
        btn_back = findViewById(R.id.btn_back);
        btn_add_student = findViewById(R.id.btn_ins_sv);
        btn_edit_student = findViewById(R.id.btn_update_sv);
        btn_load = findViewById(R.id.btn_load_sv);

        txt_masv = findViewById(R.id.txt_masv);
        txt_tensv = findViewById(R.id.txt_tensv);
        txt_malop = findViewById(R.id.txt_malop);

        spinner = findViewById(R.id.spn_malop);

        lv_danhsachsv = findViewById(R.id.lv_danhsachsv);

        if ((studentArrayList = dbManager.loadAllStudent(this)) == null) {
        } else {
            studentArrayList = dbManager.loadAllStudent(ManagerStudentActivity.this);
            studentAdapter = new StudentAdapter(ManagerStudentActivity.this,
                    R.layout.item_student,
                    studentArrayList);
            lv_danhsachsv.setAdapter(studentAdapter);
        }
    }

    private void addEvents() {
        btn_back.setOnClickListener(this);
        btn_add_student.setOnClickListener(this);
        btn_edit_student.setOnClickListener(this);
        btn_load.setOnClickListener(this);

        lv_danhsachsv.setOnItemLongClickListener(this);
        lv_danhsachsv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_back:
                Intent intent = new Intent(ManagerStudentActivity.this, ClassroomManagerActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_ins_sv:

                if (checkNull() == false) {
                    dbManager.ins_student(this, txt_masv.getText().toString().toLowerCase().trim(),
                            txt_tensv.getText().toString().toLowerCase().trim(),
                            ma_so_lop);
                    if ((studentArrayList = dbManager.loadAllStudent(this)) == null) {
                        Toast.makeText(this, "danh sach sv rong", Toast.LENGTH_SHORT).show();
                    } else {
                        clear();
                        studentArrayList = dbManager.loadAllStudent(this);
                        studentAdapter = new StudentAdapter(this, R.layout.item_student,
                                studentArrayList);
                        lv_danhsachsv.setAdapter(studentAdapter);
                    }
                } else {
                    Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_update_sv:

                if (checkNull() == false) {
                    dbManager.updateStudent(this, txt_masv.getText().toString().toLowerCase().trim(),
                            txt_tensv.getText().toString().toLowerCase().trim());
                    if ((studentArrayList = dbManager.loadAllStudent(this)) == null) {
                    } else {
                        clear();
                        txt_masv.setEnabled(true);
                        studentArrayList = dbManager.loadAllStudent(this);
                        studentAdapter = new StudentAdapter(ManagerStudentActivity.this,
                                R.layout.item_student,
                                studentArrayList);
                        lv_danhsachsv.setAdapter(studentAdapter);
                    }
                } else {
                    Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btn_load_sv:

                if (txt_masv.getText().toString().toLowerCase().trim().isEmpty() &&
                        txt_tensv.getText().toString().toLowerCase().trim().isEmpty()) {
                    Toast.makeText(this, "Please enter your student ID or name to find it! Only 1 of 2 can be entered!", Toast.LENGTH_SHORT).show();
                }

                if (!txt_masv.getText().toString().toLowerCase().trim().isEmpty()) {

                    if ((studentArrayList = dbManager.findStudentByIDStudent(this,
                            txt_masv.getText().toString().toLowerCase().trim())) == null) {
                    } else {
                        studentArrayList.clear();
                        studentArrayList = dbManager.findStudentByIDStudent(this,
                                txt_masv.getText().toString().toLowerCase().trim());
                        studentAdapter = new StudentAdapter(ManagerStudentActivity.this,
                                R.layout.item_student,
                                studentArrayList);
                        lv_danhsachsv.setAdapter(studentAdapter);
                        clear();
                    }
                }

                if (!txt_tensv.getText().toString().toLowerCase().trim().isEmpty() &&
                        txt_masv.getText().toString().toLowerCase().trim().isEmpty()) {

                    Log.d("TAG", txt_tensv.getText().toString().toLowerCase().trim());

                    if ((studentArrayList = dbManager.findStudentByNameStudent(this,
                            txt_tensv.getText().toString().toLowerCase().trim())) == null) {
                    } else {
                        studentArrayList.clear();
                        studentArrayList = dbManager.findStudentByNameStudent(this,
                                txt_tensv.getText().toString().toLowerCase().trim());
                        studentAdapter = new StudentAdapter(ManagerStudentActivity.this,
                                R.layout.item_student,
                                studentArrayList);
                        lv_danhsachsv.setAdapter(studentAdapter);
                        clear();
                    }
                }
                break;
        }
    }

    private void get_ID_Class(Spinner spinner) {
        final ArrayList<String> list = dbManager.getIDClass(ManagerStudentActivity.this);
        adapter = new ArrayAdapter<String>(ManagerStudentActivity.this, android.R.layout.simple_list_item_single_choice, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ma_so_lop = list.get(position);
                setIDClass(ma_so_lop);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setIDClass(String id_class) {
        ma_so_lop = id_class;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Student student = studentArrayList.get(position);
        dbManager.deleteStudent(ManagerStudentActivity.this, student.getMasv());
        studentArrayList = dbManager.loadAllStudent(ManagerStudentActivity.this);
        studentAdapter = new StudentAdapter(ManagerStudentActivity.this,
                R.layout.item_student,
                studentArrayList);
        lv_danhsachsv.setAdapter(studentAdapter);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Student student = studentArrayList.get(position);
        txt_masv.setEnabled(false);
        txt_masv.setText(student.getMasv());
        txt_tensv.setText(student.getTensv());
    }

    public void clear() {
        txt_masv.setText("");
        txt_tensv.setText("");
        txt_masv.requestFocus();
    }

    private boolean checkNull() {
        if (txt_masv.getText().toString().toLowerCase().trim().equals("")) {
            return true;
        } else if (txt_tensv.getText().toString().toLowerCase().trim().equals("")) {
            return true;
        }
        return false;
    }

}
