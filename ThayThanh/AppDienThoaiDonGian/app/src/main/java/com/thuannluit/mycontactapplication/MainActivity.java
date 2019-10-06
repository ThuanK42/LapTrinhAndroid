package com.thuannluit.mycontactapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtSoDienThoai, txtTinNhan;
    ImageButton btnQuaySo, btnGoi, btnNhanTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnQuaySo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyQuaySo();
            }
        });
        btnGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyGoiLuon();
            }
        });
        btnNhanTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyNhanTinvaQuanLyKetQua();
            }
        });
    }

    private void xuLyNhanTinvaQuanLyKetQua() {
        //lấy mặc định SmsManager
        final SmsManager sms = SmsManager.getDefault();
        // Yeu cau gui tin nhan ACTION_MSG_SENT
        Intent msgSent = new Intent("ACTION_MSG_SENT");
        /* Hoac lenh duoi day
        Intent msgSent = new Intent(Intent.ACTION_SEND);*/
        //Khai báo pendingintent để kiểm tra kết quả
        final PendingIntent pendingMsgSent =
                PendingIntent.getBroadcast(this, 0, msgSent, 0);
        registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg = "Đã gửi tin nhắn thành công";
                if (result != Activity.RESULT_OK) {
                    msg = "Gửi tin nhắn thất bại";
                }
                Toast.makeText(MainActivity.this, msg,
                        Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter("ACTION_MSG_SENT"));
        //Gọi hàm gửi tin nhắn đi
        sms.sendTextMessage(txtSoDienThoai.getText().toString(), null, txtTinNhan.getText() + "",
                pendingMsgSent, null);
        finish();

    }

    private void xuLyGoiLuon() {
        Uri uri = Uri.parse("tel:" + txtSoDienThoai.getText().toString());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        startActivity(intent);
    }

    private void xuLyQuaySo() {
        Uri uri = Uri.parse("tel:" + txtSoDienThoai.getText().toString());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }

    private void addControls() {
        txtTinNhan = findViewById(R.id.txtNoiDungTinNhan);
        txtSoDienThoai = findViewById(R.id.txtSoDienThoai);
        btnQuaySo = findViewById(R.id.btnQuaySo);
        btnGoi = findViewById(R.id.btnGoi);
        btnNhanTin = findViewById(R.id.btnTinNhan);
    }
}
