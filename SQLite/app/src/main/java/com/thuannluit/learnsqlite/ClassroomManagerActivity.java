package com.thuannluit.learnsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thuannluit.adapter.ClassroomAdapter;
import com.thuannluit.database.DBManager;
import com.thuannluit.model.Classroom;

import java.util.ArrayList;

public class ClassroomManagerActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private static ImageButton btn_back, btn_next, btn_add, btn_edit, btn_find;
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
        btn_edit = findViewById(R.id.btn_edit_lop);
        btn_find = findViewById(R.id.btn_find_lop);

        lvClassroom = findViewById(R.id.lv_danhsachlop);

        if ((listClassroom = dbManager.loadAllClass(this)) == null) {
        } else {
            listClassroom = dbManager.loadAllClass(ClassroomManagerActivity.this);
            classroomAdapter = new ClassroomAdapter(ClassroomManagerActivity.this,
                    R.layout.item_classroom,
                    listClassroom);
            lvClassroom.setAdapter(classroomAdapter);
        }
    }

    private void addEvents() {
        btn_back.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_edit.setOnClickListener(this);

        lvClassroom.setOnItemClickListener(this);

        lvClassroom.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_back:
                txt_malop.setEnabled(true);
                Intent intent = new Intent(ClassroomManagerActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_next:
                txt_malop.setEnabled(true);
                Intent intent2 = new Intent(ClassroomManagerActivity.this, ManagerStudentActivity.class);
                startActivity(intent2);
                break;

            case R.id.btn_ins_lop:
                if (checkNull() == false) {
                    dbManager.ins_class(this,
                            txt_malop.getText().toString().toLowerCase().trim(),
                            txt_tenlop.getText().toString().toLowerCase().trim(),
                            Integer.valueOf(txt_siso.getText().toString().trim()));
                    if ((listClassroom = dbManager.loadAllClass(this)) == null) {
                    } else {
                        clear();
                        listClassroom = dbManager.loadAllClass(this);
                        classroomAdapter = new ClassroomAdapter(ClassroomManagerActivity.this,
                                R.layout.item_classroom,
                                listClassroom);
                        lvClassroom.setAdapter(classroomAdapter);
                    }
                } else {
                    Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_edit_lop:
                if (checkNull() == false) {
                    dbManager.updateClass(ClassroomManagerActivity.this, txt_malop.getText().toString().toLowerCase().trim(),
                            txt_tenlop.getText().toString().toLowerCase().trim(),
                            Integer.parseInt(txt_siso.getText().toString().trim()));
                    if ((listClassroom = dbManager.loadAllClass(this)) == null) {
                    } else {
                        clear();
                        txt_malop.setEnabled(true);
                        listClassroom = dbManager.loadAllClass(this);
                        classroomAdapter = new ClassroomAdapter(ClassroomManagerActivity.this,
                                R.layout.item_classroom,
                                listClassroom);
                        lvClassroom.setAdapter(classroomAdapter);
                    }
                } else {
                    Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Classroom classroom = listClassroom.get(position);
        txt_malop.setEnabled(false);
        txt_malop.setText(classroom.getMalop());
        txt_tenlop.setText(classroom.getTenlop());
        txt_siso.setText(String.valueOf(classroom.getSiso()));
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Classroom classroom = listClassroom.get(position);
        dbManager.del_class(ClassroomManagerActivity.this, classroom.getMalop());
        listClassroom = dbManager.loadAllClass(ClassroomManagerActivity.this);
        classroomAdapter = new ClassroomAdapter(ClassroomManagerActivity.this,
                R.layout.item_classroom,
                listClassroom);
        lvClassroom.setAdapter(classroomAdapter);
        return true;
    }

    private boolean checkNull() {
        if (txt_malop.getText().toString().toLowerCase().trim().equals("")) {
            return true;
        } else if (txt_tenlop.getText().toString().toLowerCase().trim().equals("")) {
            return true;
        } else if (txt_siso.getText().toString().toLowerCase().trim().equals("")) {
            return true;
        }
        return false;
    }

    private void clear() {
        txt_malop.setText("");
        txt_tenlop.setText("");
        txt_siso.setText("");
        txt_malop.requestFocus();
    }

}
