package com.example.baitapquanlynhansu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class nhanVien extends AppCompatActivity {
    ImageView img;
    TextView  usntxt,passtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        img =(ImageView)findViewById(R.id.imageView);


        Bundle extras = getIntent().getExtras();
        String usn = extras.getString("usn");
        String pass = getIntent().getStringExtra("pass");
        passtxt.setText(pass);
        usntxt.setText(usn);

        //Hình ảnh
        AssetManager assetManager = getAssets();
        try (
                InputStream inputStream = assetManager.open("image/3.jpg")
        ) {
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            img.setImageBitmap(bitmap);
        } catch (IOException ex) {
            Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_LONG).show();
        }


    }
}
