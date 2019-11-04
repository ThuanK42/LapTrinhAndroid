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
import Contructor.phongBan;

import com.example.baitapquanlynhansu.UserActi;
import com.example.baitapquanlynhansu.baoHiemActi;
import com.example.baitapquanlynhansu.chucVuActi;
import com.example.baitapquanlynhansu.phongBanActi;

import java.text.ParsePosition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class baoHiemAdapter extends ArrayAdapter<baoHiem> {
    private  ArrayList<baoHiem> baohiem;
    Activity context =null;
    int resource;

    public baoHiemAdapter(Activity context , int resource,   ArrayList<baoHiem> baohiem ) {
        super(context,   resource,baohiem);
        this.baohiem = baohiem;
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


            if(baohiem.size()>0 && position>=0){

                holder.mabh=(TextView)convertView.findViewById(R.id.txtMaBHContruct);
                holder.tenbh=(TextView)convertView.findViewById(R.id.txtTenBHcontruct);
                holder.tungaybh=(TextView)convertView.findViewById(R.id.txtTuNgayBH);
                Button btn = (Button)convertView.findViewById(R.id.btnXoaBH);
                Button btnctiet = (Button)convertView.findViewById(R.id.btnChiTietBH);
             final    baoHiem bh = baohiem.get(position);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbhp.deleteRow("delete from BaoHiem where MaBH= '"+holder.mabh.getText().toString()+"' and tungaybh = '"+bh.getTungaybh()+"' ");
                        Intent it = new Intent(context, baoHiemActi.class);
                        context.startActivity(it);
                    }
                });
                btnctiet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      EditText mabh=  context.findViewById(R.id.txtThemMaBH);
                      mabh.setText(bh.getMabh());
                      EditText tenbh=  context.findViewById(R.id.txtThemTenBH);
                      tenbh.setText(bh.getTenbh());
                      EditText tungay=  context.findViewById(R.id.txtThemBHTuNgay);
                      tungay.setText(bh.getTungaybh());
                      EditText denngay=  context.findViewById(R.id.txtThemBHDenNgay);
                        denngay.setText(bh.getDenngaybh());
                    }
                });

                holder.mabh.setText(bh.getMabh());
                holder.tenbh.setText(bh.getTenbh());
                holder.tungaybh.setText(bh.getTungaybh()+"");
            }
        }
        return convertView;
    }

    public static  class ViewHolder{
        TextView mabh;
        TextView tenbh;
        TextView tungaybh;

    }
}
