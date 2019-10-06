package com.thuannluit.projectcontactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.thuannluit.adapter.ContactAdapter;
import com.thuannluit.models.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imgAnhDaiDien;
    EditText txtTen, txtSoDienThoai;
    ImageButton btnLuu;

    ListView lvDanhBa;
    ArrayList<Contact> dsDanhBa;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuuDanhBa();
            }
        });
    }

    private void xuLyLuuDanhBa() {
        Contact contact = new Contact(txtTen.getText().toString(), txtSoDienThoai.getText().toString(), R.drawable.crush);
        dsDanhBa.add(contact);
        contactAdapter.notifyDataSetChanged();
    }

    private void addControls() {
        txtTen = (EditText) findViewById(R.id.txtTen);
        txtSoDienThoai = (EditText) findViewById(R.id.txtSoDienThoai);
        btnLuu = findViewById(R.id.btnLuu);

        lvDanhBa = findViewById(R.id.lvDanhBa);
        dsDanhBa = new ArrayList<>();

        dsDanhBa.add(new Contact("Bui Thi Thanh Tam", "123456789", R.drawable.crush));
        dsDanhBa.add(new Contact("Tran Viet Son", "123456789", R.drawable.tranvietson));
        dsDanhBa.add(new Contact("To Thanh Sang", "123456789", R.drawable.sangto));
        dsDanhBa.add(new Contact("Ho Thi My Trang", "123456789", R.drawable.hothimytrang));
        dsDanhBa.add(new Contact("Le Thi Le Huyen", "123456789", R.drawable.lehuyen));
        dsDanhBa.add(new Contact("Nguyen Van Quang", "123456789", R.drawable.quang));
        dsDanhBa.add(new Contact("Tran Thanh Dien", "123456789", R.drawable.tranthanhdien));
        dsDanhBa.add(new Contact("Phuong Thao", "123456789", R.drawable.phuongthao));
        dsDanhBa.add(new Contact("Ha Tran", "123456789", R.drawable.hatran));
        dsDanhBa.add(new Contact("Nguyen Hieu", "123456789", R.drawable.hieu));
        dsDanhBa.add(new Contact("Le Hoang", "123456789", R.drawable.hoang));
        dsDanhBa.add(new Contact("Kieu Dung", "123456789", R.drawable.dungkieu));

        contactAdapter = new ContactAdapter(MainActivity.this, R.layout.activity_items, dsDanhBa);
        lvDanhBa.setAdapter(contactAdapter);

    }
}
