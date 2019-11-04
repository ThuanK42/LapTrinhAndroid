package Adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baitapquanlynhansu.DatabaseHelper;
import com.example.baitapquanlynhansu.R;
import Contructor.nhanVien;
import com.example.baitapquanlynhansu.nhanVienActi;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class nhanVienAdapter extends ArrayAdapter<nhanVien> {
    private  ArrayList<nhanVien> user;
    Activity context =null;
    int resource;

    public nhanVienAdapter(Activity context , int resource,   ArrayList<nhanVien> user ) {
        super(context,   resource,user);
        this.user = user;
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
            if(user.size()>0 && position>=0){

                holder.Manv=(TextView)convertView.findViewById(R.id.txtnhannvienMaNV);
                holder.TenNV=(TextView)convertView.findViewById(R.id.txtnhannvienTenNV);
                Button btn = (Button)convertView.findViewById(R.id.btnXoaNhanVien);
                Button btn2 = (Button)convertView.findViewById(R.id.btnChitietNhanVien);
              final  nhanVien nv = user.get(position);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbhp.deleteRow("delete from nhanvien where manv= '"+holder.Manv.getText().toString()+"'");
                        Intent it = new Intent(context, nhanVienActi.class);
                        context.startActivity(it);

                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText manv,tennv,mall,macv,mabh,mapb,email,sdt,hinhanh;
                        ImageView img;
                        img = (ImageView)context.findViewById(R.id.imageView);
                        manv= (EditText)context.findViewById(R.id.txtNhanVienmanv);
                        tennv= (EditText)context.findViewById(R.id.txtNhanVientennv);
                        mall= (EditText)context.findViewById(R.id.txtNhanVienlylich);
                        macv= (EditText)context.findViewById(R.id.txtNhanVienMacv);
                        mabh= (EditText)context.findViewById(R.id.txtNhanVienMabh);
                        mapb= (EditText)context.findViewById(R.id.txtNhanVienmapb);
                        email= (EditText)context.findViewById(R.id.txtNhanVienemail);
                        sdt= (EditText)context.findViewById(R.id.txtNhanViensdt);
                        hinhanh= (EditText)context.findViewById(R.id.txtNhanVienduongdan);
                        manv.setText(nv.getManv().toString());
                        tennv.setText(nv.getTennv().toString());
                        mall.setText(nv.getMall().toString());
                        macv.setText(nv.getMacv().toString());
                        mabh.setText(nv.getMabh().toString());
                        mapb.setText(nv.getMapb().toString());
                        email.setText(nv.getEmail().toString());
                        sdt.setText(nv.getSdt().toString());
                        hinhanh.setText(nv.getHinhanh().toString());

                        AssetManager assetManager = context.getAssets();
                        try {
                            InputStream inputStream = assetManager.open(nv.getHinhanh());
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            img.setImageBitmap(bitmap);
                        }
                        catch (IOException ex) {
//                         Toast.makeText(context,hinhanh.getText().toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                });

                holder.Manv.setText(nv.getManv()+"");
                holder.TenNV.setText(nv.getTennv()+"");

            }
        }
        return convertView;
    }

    public static  class ViewHolder{
        TextView Manv;
        TextView TenNV;
    }
}
