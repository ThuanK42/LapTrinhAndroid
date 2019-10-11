package com.thuannluit.demotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnDOM, btnSAX;
    private TextView txtdsEmployee;
    private EditText txtdemo;

    private Spinner spnTitle;
    private ArrayList<String> listSpinnerTitle = null;
    private ArrayAdapter<String> listArrayAdapter = null;
    private final String fileName = "file.txt";
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        checkAndRequestPermissions();
    }

    //quyền đọc ghi file
    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }

    //Kiểm tra thiết bị có bộ nhớ ngoài và có thể ghi file được không?
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    private void addControls() {

        //txtdemo = findViewById(R.id.txtdemo);
        btnDOM = findViewById(R.id.btnDOM);
        btnSAX = findViewById(R.id.btnSAX);
        txtdsEmployee = findViewById(R.id.txtEmployee);

       // spnTitle = findViewById(R.id.dsTittle);

        listSpinnerTitle = new ArrayList<String>();
        listSpinnerTitle.add("Quan Su");
        listSpinnerTitle.add("Minh Chu");
        listSpinnerTitle.add("Tuong Quan");
        listSpinnerTitle.add("Dich");

//        listArrayAdapter = new ArrayAdapter<>(MainActivity.this,
//                android.R.layout.simple_spinner_item,
//                listSpinnerTitle);
//        listArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
//        spnTitle.setAdapter(listArrayAdapter);
//
//        spnTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, spnTitle.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        btnDOM.setOnClickListener(this);
        btnSAX.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDOM:
                saveData();
                break;
            case R.id.btnSAX:
                readData();
                break;
            default:
                break;
        }
    }

    public void saveData() {
        if (isExternalStorageReadable()) {
            String content = "1 - Quan Su - Gia Cat Luong\n" +
                    "2 - Minh Chu - Luu Bi\n" +
                    "3 - Tuong Quan - Quan Van Truong\n" +
                    "4 - Tuong Quan - Truong Phi\n" +
                    "5 - Tuong Quan - Trieu Quan\n" +
                    "6 - Tuong Quan - Ma Sieu\n" +
                    "7 - Tuong Quan - Hoang Trung\n" +
                    "8 - Quan Su - Bang Si Nguyen\n" +
                    "9 - Quan Su - Tu Thu\n" +
                    "10 - Dich - Ton Quyen\n" +
                    "11 - Dich - Tao Thao\n" +
                    "12 - Dich - Lu Bo\n" +
                    "13 - Dich - Hoang Can\n" +
                    "14 - Dich - Vien Thieu\n" +
                    "15 - Dich - Tieu Viem";
            File file;
            FileOutputStream outputStream;
            try {
                file = new File(Environment.getExternalStorageDirectory(), fileName);
                Log.d("MainActivity", Environment.getExternalStorageDirectory().getAbsolutePath());
                outputStream = new FileOutputStream(file);
                outputStream.write(content.getBytes());
                outputStream.close();
                Toast.makeText(MainActivity.this, "Luu thanh cong", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Cannot save file", Toast.LENGTH_LONG).show();
        }
    }

    public void readData() {
        BufferedReader input = null;
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory(), fileName);

            input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            String str = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line + "\n");

            }
            txtdsEmployee.setText(buffer.toString());
            Toast.makeText(this, "Read file success", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// Nguon : https://youtu.be/68Vxql5ulkg
}
