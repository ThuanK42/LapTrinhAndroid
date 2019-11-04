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

import com.example.baitapquanlynhansu.UserActi;
import com.example.baitapquanlynhansu.chucVuActi;

import java.util.ArrayList;

public class chucVuAdapter extends ArrayAdapter<chucVu> {
    private  ArrayList<chucVu> chucvu;
    Activity context =null;
    int resource;

    public chucVuAdapter(Activity context , int resource,   ArrayList<chucVu> chucvu ) {
        super(context,   resource,chucvu);
        this.chucvu = chucvu;
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
            if(chucvu.size()>0 && position>=0){

                holder.macv=(TextView)convertView.findViewById(R.id.txtMaCVContruct);
                holder.tencv=(TextView)convertView.findViewById(R.id.txtTenCVcontruct);
                Button btn = (Button)convertView.findViewById(R.id.btnXoaChucVu);
                chucVu cv = chucvu.get(position);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbhp.deleteRow("delete from chucvu where macv= '"+holder.macv.getText().toString()+"'");
                        Intent it = new Intent(context, chucVuActi.class);
                        context.startActivity(it);

                    }
                });
                holder.macv.setText(cv.getMacv()+"");
                holder.tencv.setText(cv.getTencv()+"");

            }
        }
        return convertView;
    }

    public static  class ViewHolder{
        TextView macv;
        TextView tencv;
    }
}
