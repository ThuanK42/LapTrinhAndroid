package com.thuannluit.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thuannluit.listviewnangcao_listviewdanhba.R;
import com.thuannluit.model.DanhBa;

import java.util.List;

public class DanhBaAdapter extends ArrayAdapter<DanhBa> {
    //đối số 1: màn hình sử dụng layout này (giao diện này)
    @NonNull
    Activity context;
    //layout cho từng dòng muốn hiển thị (làm theo khách hàng)
    int resource;
    // danh sách nguồn dữ liệu muốn hiển thị lên giao diện
    @NonNull
    List<DanhBa> objects;

    public DanhBaAdapter(@NonNull Activity context, int resource, @NonNull List<DanhBa> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null); //no chi den item trong layout

        TextView txtTen = (TextView) row.findViewById(R.id.txtTen);
        TextView txtPhone = (TextView) row.findViewById(R.id.txtPhone);
        ImageButton btnGoi = (ImageButton) row.findViewById(R.id.btnGoi);
        ImageButton btnNhanTin = (ImageButton) row.findViewById(R.id.btnNhanTin);
        ImageButton btnChiTiet = (ImageButton) row.findViewById(R.id.btnChiTiet);

        //Trả về danh bạ điện thoại muốn vẽ
        DanhBa danhBa=this.objects.get(position);
        txtTen.setText(danhBa.getTen());
        txtPhone.setText(danhBa.getPhone());
        return row;
    }
}
