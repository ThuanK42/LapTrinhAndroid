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
import com.example.baitapquanlynhansu.tinhThanhphoActi;

import java.util.ArrayList;

import Contructor.tinhThanhpho;

public class tinhThanhphoAdapter extends ArrayAdapter<tinhThanhpho> {
    private ArrayList<tinhThanhpho> tinhtp;
    Activity context =null;
    int resource;

    public tinhThanhphoAdapter(Activity context , int resource,   ArrayList<tinhThanhpho> tinhtp ) {
        super(context,   resource,tinhtp);
        this.tinhtp = tinhtp;
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
            if(tinhtp.size()>0 && position>=0){

                holder.matinhtp=(TextView)convertView.findViewById(R.id.txtMaTinhTPcontruct);
                holder.tentinhtp=(TextView)convertView.findViewById(R.id.txtTenTinhTPcontruct);
                Button btn = (Button)convertView.findViewById(R.id.btnXoaTinhTP);
                tinhThanhpho tinhthp = tinhtp.get(position);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbhp.deleteRow("delete from TinhThanhPho where matp= '"+holder.matinhtp.getText().toString()+"'");
                        Intent it = new Intent(context, tinhThanhphoActi.class);
                        context.startActivity(it);

                    }
                });
                holder.matinhtp.setText(tinhthp.getMatinhtp()+"");
                holder.tentinhtp.setText(tinhthp.getTentinhtp()+"");

            }
        }
        return convertView;
    }

    public static  class ViewHolder{
        TextView matinhtp;
        TextView tentinhtp;
    }
}
