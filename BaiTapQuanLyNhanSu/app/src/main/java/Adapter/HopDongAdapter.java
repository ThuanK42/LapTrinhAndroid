package Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baitapquanlynhansu.DatabaseHelper;
import com.example.baitapquanlynhansu.HopDongActi;
import com.example.baitapquanlynhansu.R;
import com.example.baitapquanlynhansu.baoHiemActi;

import java.util.ArrayList;

import Contructor.HopDong;
import Contructor.baoHiem;

public class HopDongAdapter extends ArrayAdapter<HopDong> {
    private ArrayList<HopDong> hopdong;
    Activity context =null;
    int resource;

    public HopDongAdapter(Activity context , int resource,   ArrayList<HopDong> hopdong ) {
        super(context,   resource,hopdong);
        this.hopdong = hopdong;
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


            if(hopdong.size()>0 && position>=0){

                holder.mahd=(TextView)convertView.findViewById(R.id.txtMaHDContruct);
                holder.mall=(TextView)convertView.findViewById(R.id.txtMaLLcontruct);
                holder.maloaihd=(TextView)convertView.findViewById(R.id.txtMaLoaiHDContruct);
                Button btn = (Button)convertView.findViewById(R.id.btnXoaHD);
                Button btnctiet = (Button)convertView.findViewById(R.id.btnChiTietHD);
                final    HopDong hd = hopdong.get(position);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbhp.deleteRow("delete from HopDongLaoDong where MaHD= '"+holder.mahd.getText().toString()+"' and mall = '"+hd.getMaLL()+"' ");
                        Intent it = new Intent(context, HopDongActi.class);
                        context.startActivity(it);
                    }
                });
                btnctiet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText mahd=  context.findViewById(R.id.txtHopDongMaHD);
                        mahd.setText(hd.getMaHD());
                        EditText mall=  context.findViewById(R.id.txtHopDongMaLL);
                        mall.setText(hd.getMaLL());
                        EditText maloaihd=  context.findViewById(R.id.txtHopDongMaLoaiHD);
                        maloaihd.setText(hd.getMaLoaiHD());
                        EditText ngaycapluong=  context.findViewById(R.id.txtThemHDNgayCapLuong);
                        ngaycapluong.setText(hd.getNgayCapLuong());
                        EditText ngayki=  context.findViewById(R.id.txtThemHDNgayKi);
                        ngayki.setText(hd.getNgayKi());
                        EditText nguoiki=  context.findViewById(R.id.txtHopDongNguoiKi);
                        nguoiki.setText(hd.getNguoiKi());
                        EditText apdungtungay=  context.findViewById(R.id.txtThemHDApDungTN);
                        apdungtungay.setText(hd.getApDungTuNgay());
                        EditText dieukhoan=  context.findViewById(R.id.txtHopDongDieuKhoan);
                        dieukhoan.setText(hd.getDieuKhoan());
                        EditText ghichu=  context.findViewById(R.id.txtHopDongGhiChu);
                        ghichu.setText(hd.getGhiChu());
                    }
                });

                holder.mahd.setText(hd.getMaHD());
                holder.mall.setText(hd.getMaLL());
                holder.maloaihd.setText(hd.getMaLoaiHD());

            }
        }
        return convertView;
    }

    public static  class ViewHolder{
        TextView mahd;
        TextView mall;
        TextView maloaihd;
        TextView ngaycapluong;
        TextView ngayki;
        TextView nguoiki;
        TextView apdungtungay;
        TextView dieukhoan;
        TextView ghichu;

    }
}
