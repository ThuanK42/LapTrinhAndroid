package com.thuannluit.projectcontactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.thuannluit.models.Contact;

public class NhanTinActivity extends AppCompatActivity {

    TextView txtNguoiNhan;
    EditText txtNoiDung;
    ImageButton btnGui;

    Contact selectedContact = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_tin);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTinNhan();
            }
        });
    }

    private void xuLyTinNhan() {
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
                Toast.makeText(NhanTinActivity.this, msg,
                        Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter("ACTION_MSG_SENT"));
        //Gọi hàm gửi tin nhắn đi
        sms.sendTextMessage(selectedContact.getSoDienThoai(), null, txtNoiDung.getText() + "",
                pendingMsgSent, null);
        finish();
    }

    private void addControls() {
        txtNguoiNhan = findViewById(R.id.txtNguoiNhan);
        txtNoiDung = findViewById(R.id.txtNoiDungTinNhan);
        btnGui = findViewById(R.id.btnGuiTinNhan);

        Intent intent = getIntent();
        selectedContact = (Contact) intent.getSerializableExtra("CONTACT");
        txtNguoiNhan.setText(selectedContact.getTen() + "-" + selectedContact.getSoDienThoai());
    }
}
