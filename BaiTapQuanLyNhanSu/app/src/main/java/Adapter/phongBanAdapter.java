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

import Contructor.chucVu;
import Contructor.phongBan;

import com.example.baitapquanlynhansu.UserActi;
import com.example.baitapquanlynhansu.chucVuActi;
import com.example.baitapquanlynhansu.phongBanActi;

import java.util.ArrayList;

public class phongBanAdapter extends ArrayAdapter<phongBan> {
    private  ArrayList<phongBan> phongban;
    Activity context =null;
    int resource;

    public phongBanAdapter(Activity context , int resource,   ArrayList<phongBan> phongban ) {
        super(context,   resource,phongban);
        this.phongban = phongban;
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


            if(phongban.size()>0 && position>=0){

                holder.mapb=(TextView)convertView.findViewById(R.id.txtMaPBContruct);
                holder.tenpb=(TextView)convertView.findViewById(R.id.txtTenPBcontruct);
                Button btn = (Button)convertView.findViewById(R.id.btnXoaPB);
                phongBan pb = phongban.get(position);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbhp.deleteRow("delete from PhongBan where Mapb= '"+holder.mapb.getText().toString()+"'");
                        Intent it = new Intent(context, phongBanActi.class);
                        context.startActivity(it);
                    }
                });
                holder.mapb.setText(pb.getMapb()+"");
                holder.tenpb.setText(pb.getTenpb()+"");

            }
        }
        return convertView;
    }

    public static  class ViewHolder{
        TextView mapb;
        TextView tenpb;
    }
}
