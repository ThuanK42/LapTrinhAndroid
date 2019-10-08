package com.thuannluit.hocassetsandshareprefence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView txtFont;
    ListView lvFontChu;
    ArrayList<String> dsFont;
    ArrayAdapter<String> fontAdapter;

    String tenLuuTru = "TrangThaiFont";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvFontChu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                xuLyFontChu(position);
            }
        });
    }

    private void xuLyFontChu(int position) {
        // tao font chu tu asset
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/" + dsFont.get(position));
        txtFont.setTypeface(typeface);
        //Luu font chu
        SharedPreferences sharedPreferences = getSharedPreferences(tenLuuTru, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit(); // tra ve editor -- luu du lieu xuong file
        editor.putString("FONTCHU", dsFont.get(position));
        editor.commit();//xac nhan luu xuong file xml

    }

    private void addControls() {
        txtFont = findViewById(R.id.txtFont);
        lvFontChu = findViewById(R.id.lvFont);
        dsFont = new ArrayList<>();
        fontAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, dsFont);
        lvFontChu.setAdapter(fontAdapter);
        try {// lay het du lieu tu folder asset
            AssetManager assetManager = getAssets();
            // goi du lieu tu folder minh can dung vd font
            String[] arrFontName = assetManager.list("font");
            // add het du lieu vao array
            dsFont.addAll(Arrays.asList(arrFontName));
            // thay doi adapter khi du lieu thay doi
            fontAdapter.notifyDataSetChanged();

            SharedPreferences sharedPreferences = getSharedPreferences(tenLuuTru, MODE_PRIVATE);
            String font = sharedPreferences.getString("FONTCHU", "");
            if (font.length() > 0) {
                Typeface typeface = Typeface.createFromAsset(getAssets(), font);
                txtFont.setTypeface(typeface);
            }

        } catch (Exception ex) {
            Log.e("LOI_FONT", ex.toString());
        }
    }
}
