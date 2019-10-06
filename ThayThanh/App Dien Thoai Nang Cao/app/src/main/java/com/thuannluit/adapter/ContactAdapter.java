package com.thuannluit.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.thuannluit.models.Contact;
import com.thuannluit.projectcontactmanager.NhanTinActivity;
import com.thuannluit.projectcontactmanager.R;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    @NonNull
    Activity context;
    int resource;
    @NonNull
    List<Contact> objects;

    public ContactAdapter(@NonNull Activity context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        TextView txtTen = row.findViewById(R.id.txtTen);
        TextView txtSoDienThoai = row.findViewById(R.id.txtSoDienThoai);
        ImageView imgAnhDaiDien = row.findViewById(R.id.imgAnhDaiDien);
        ImageButton btnGoi = row.findViewById(R.id.btnGoi);
        ImageButton btnNhanTin = row.findViewById(R.id.btnNhanTin);
        ImageButton btnXoa = row.findViewById(R.id.btnXoa);

        final Contact contact = this.objects.get(position);
        txtTen.setText(contact.getTen());
        txtSoDienThoai.setText(contact.getSoDienThoai());
        imgAnhDaiDien.setImageResource(contact.getAnhDaiDien());

        btnGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyGoi(contact);
            }
        });
        btnNhanTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyNhanTin(contact);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXoa(contact);
            }
        });
        return row;
    }

    private void xuLyXoa(Contact contact) {
        this.remove(contact);
    }

    private void xuLyNhanTin(Contact contact) {
        Intent intent = new Intent(this.context, NhanTinActivity.class);
        intent.putExtra("CONTACT", contact);
        this.context.startActivity(intent);
    }

    private void xuLyGoi(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + contact.getSoDienThoai());
        intent.setData(uri);
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        this.context.startActivity(intent);
    }
}
