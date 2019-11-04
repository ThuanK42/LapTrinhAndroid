package com.example.baitapquanlynhansu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import Contructor.LoaiHopDong;

public class Master extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener  {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Button btn1, btn2, btn3, btn4, btn5  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout= findViewById(R.id.master);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 =(Button) findViewById(R.id.btn2);
        btn3 =(Button) findViewById(R.id.btn3);
        btn4 =(Button) findViewById(R.id.btn4);
        btn5 =(Button) findViewById(R.id.btn5);

        btn1.setVisibility(View.GONE);
        btn2.setVisibility(View.GONE);
        btn3.setVisibility(View.GONE);
        btn4.setVisibility(View.GONE);
        btn5.setVisibility(View.GONE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (btn1.getText().toString()) {
                    case "Nhân viên":
                        startActivity(new Intent(getApplicationContext(),nhanVienActi.class));
                        break;
                    case "Lý Lịch":

                        break;
                    case "Hợp đồng":
                        startActivity(new Intent(getApplicationContext(),HopDongActi.class));
                        break;
                    case "Công tác":
                        break;

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (btn2.getText().toString()) {
                    case "Chức vụ":
                        startActivity(new Intent(getApplicationContext(),chucVuActi.class));
                        break;
                    case "Dân tộc":
                        startActivity(new Intent(getApplicationContext(),danTocActi.class));
                        break;

                    case "Loại hợp dồng":
                        startActivity(new Intent(getApplicationContext(), LoaiHopDongActi.class));
                        break;
                    case "Quá trình công tác":

                        break;
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (btn3.getText().toString()) {
                    case "Phòng Ban":
                        startActivity(new Intent(getApplicationContext(),phongBanActi.class));
                        break;
                    case "Quốc Tịch":
                        break;
                    case "Lương":
                        startActivity(new Intent(getApplicationContext(),luongActi.class));
                        break;
                    case "Quyết định bổ nhiệm":
                        break;

                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (btn4.getText().toString()) {
                    case "Bảo hiểm":
                        startActivity(new Intent(getApplicationContext(),baoHiemActi.class));
                        break;
                    case "Thành phố":
                        startActivity(new Intent(getApplicationContext(),tinhThanhphoActi.class));
                        break;
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (btn5.getText().toString()) {

                    case "Tài khoản người dùng":
                        startActivity(new Intent(getApplicationContext(),UserActi.class));
                        break;
                    case "Trình độ học vấn":
                        startActivity(new Intent(getApplicationContext(),trinhdohocvanActi.class));
                        break;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
                    return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_nhanVien:
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);
                btn5.setVisibility(View.VISIBLE);

                btn1.setText("Nhân viên");
                btn2.setText("Chức vụ");
                btn3.setText("Phòng Ban");
                btn4.setText("Bảo hiểm");
                btn5.setText("Tài khoản người dùng");
                return false;
            case R.id.nav_LyLich:
//                btn1.setVisibility(View.VISIBLE);
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);
                btn5.setVisibility(View.VISIBLE);
                btn1.setText("Lý Lịch");
                btn2.setText("Dân tộc");
                btn3.setText("Quốc Tịch");
                btn4.setText("Thành phố");
                btn5.setText("Trình độ học vấn");
                return false;
            case R.id.nav_hopdong:
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn1.setText("Hợp đồng");
                btn2.setText("Loại hợp dồng");
                btn3.setText("Lương");
                btn4.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
                return false;

            case R.id.nav_logout:

                return false;
        }
        return false;
    }
}
