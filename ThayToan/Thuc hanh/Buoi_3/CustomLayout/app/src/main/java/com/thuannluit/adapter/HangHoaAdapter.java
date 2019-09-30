package com.thuannluit.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thuannluit.customlayout.R;
import com.thuannluit.model.HangHoa;

import java.util.List;

public class HangHoaAdapter extends ArrayAdapter<HangHoa> {
    Activity context; // Man hinh hien thi
    int resource; //layout tung dong hien thi
    List<HangHoa> objects; // danh sach nguon du lieu

    public HangHoaAdapter(@NonNull Activity context, int resource, @NonNull List<HangHoa> objects) {
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

        //anh xa view
        ImageView imageView = (ImageView) row.findViewById(R.id.img);
        TextView textView = (TextView) row.findViewById(R.id.txtTen);
        CheckBox checkBox = (CheckBox) row.findViewById(R.id.btnChon);

        // gan gia tri
        HangHoa hangHoa = this.objects.get(position);

        imageView.setImageResource(hangHoa.getAnh());
        textView.setText("-" + hangHoa.getMaHang() + "-" + hangHoa.getTenHang() + "-" + hangHoa.getLoaiHang());

        return row;
    }
}
