package Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baitapquanlynhansu.DatabaseHelper;
import com.example.baitapquanlynhansu.R;
import com.example.baitapquanlynhansu.LoaiHopDongActi;

import java.util.ArrayList;

import Contructor.LoaiHopDong;

public class LoaiHopDongAdapter extends ArrayAdapter<LoaiHopDong> {
    private  ArrayList<LoaiHopDong> loaihopdong;
    Activity context =null;
    int resource;

    public LoaiHopDongAdapter(Activity context , int resource,   ArrayList<LoaiHopDong> loaihopdong ) {
        super(context,   resource,loaihopdong);
        this.loaihopdong = loaihopdong;
        this.context = context;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final DatabaseHelper lhd = new DatabaseHelper(context);
        if(convertView  ==null){
            LayoutInflater inflater  =context.getLayoutInflater();
            final ViewHolder holder = new ViewHolder();
            convertView=inflater.inflate(resource,null, false);


            if(loaihopdong.size()>0 && position>=0){

                holder.maloaihd=(TextView)convertView.findViewById(R.id.txtMaLoaiHDContruct);
                holder.tenloaihd=(TextView)convertView.findViewById(R.id.txtTenLoaiHDcontruct);
                Button btn = (Button)convertView.findViewById(R.id.btnXoaLoaiHD);
                LoaiHopDong loaihd = loaihopdong.get(position);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lhd.deleteRow("delete from LoaiHDLD where MaloaiHDLD= '"+holder.maloaihd.getText().toString()+"'");
                        Intent it = new Intent(context, LoaiHopDongActi.class);
                        context.startActivity(it);
                    }
                });
                holder.maloaihd.setText(loaihd.getMaLoaiHD()+"");
                holder.tenloaihd.setText(loaihd.getTenLoaiHD()+"");
            }
        }
        return convertView;
    }

    public static  class ViewHolder{
        TextView maloaihd;
        TextView tenloaihd;
    }
}

