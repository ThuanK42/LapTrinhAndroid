package Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baitapquanlynhansu.DatabaseHelper;
import com.example.baitapquanlynhansu.R;

import Contructor.baoHiem;
import Contructor.chucVu;
import Contructor.luong;
import Contructor.phongBan;

import com.example.baitapquanlynhansu.UserActi;
import com.example.baitapquanlynhansu.baoHiemActi;
import com.example.baitapquanlynhansu.chucVuActi;
import com.example.baitapquanlynhansu.luongActi;
import com.example.baitapquanlynhansu.phongBanActi;

import java.text.ParsePosition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LuongAdapter extends ArrayAdapter<luong> {
    private  ArrayList<luong> luong;
    Activity context =null;
    int resource;

    public LuongAdapter(Activity context , int resource,   ArrayList<luong> luong ) {
        super(context,   resource,luong);
        this.luong = luong;
        this.context = context;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final DatabaseHelper dbhp = new DatabaseHelper(context);
        if(convertView  ==null){
            LayoutInflater inflater  =context.getLayoutInflater();
            final ViewHolder holder = new ViewHolder();
            convertView=inflater.inflate(resource,null, false);
            if(luong.size()>0 && position>=0){
                holder.ngaycapnhat=(TextView)convertView.findViewById(R.id.txtNgayCapNhatLuongContruct);
                holder.maluong=(TextView)convertView.findViewById(R.id.txtMaLuongContruct);
                holder.mucluong=(TextView)convertView.findViewById(R.id.txtMucLuongcontruct);
                Button btn = (Button)convertView.findViewById(R.id.btnXoaLuong);
                Button btnctiet = (Button)convertView.findViewById(R.id.btnChiTietLuong);
                final    luong luong1 = luong.get(position);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbhp.deleteRow("delete from luong where ngaycapnhatluong= '"+holder.ngaycapnhat.getText().toString()+"' and maluong = '"+luong1.getMaluong()+"' ");
                        Intent it = new Intent(context, luongActi.class);
                        context.startActivity(it);
                    }
                });
                btnctiet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText ngaycapnhat=  context.findViewById(R.id.txtThemNgayCapNhapLuong);
                        ngaycapnhat.setText(luong1.getNgaycapnhatluong());
                        EditText maluong=  context.findViewById(R.id.txtThemMaLuong);
                        maluong.setText(luong1.getMaluong());
                        EditText mucluong=  context.findViewById(R.id.txtThemMucLuong);
                        mucluong.setText(luong1.getMucluong());
                        EditText bacluong=  context.findViewById(R.id.txtThemBacLuong);
                        bacluong.setText(luong1.getBatluong());
                    }
                });

                holder.ngaycapnhat.setText(luong1.getNgaycapnhatluong());
                holder.maluong.setText(luong1.getMaluong());
                holder.mucluong.setText(luong1.getMucluong()+"");
            }
        }
        return convertView;
    }

    public static  class ViewHolder{
        TextView ngaycapnhat;
        TextView maluong;
        TextView mucluong;

    }
}
