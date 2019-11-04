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
import com.example.baitapquanlynhansu.danTocActi;

import java.util.ArrayList;

import Contructor.danToc;

public class danTocAdapter extends ArrayAdapter<danToc> {
    private ArrayList<danToc> dantoc;
    Activity context =null;
    int resource;

    public danTocAdapter(Activity context , int resource,   ArrayList<danToc> dantoc ) {
        super(context,   resource,dantoc);
        this.dantoc = dantoc;
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


            if(dantoc.size()>0 && position>=0){

                holder.madt=(TextView)convertView.findViewById(R.id.txtMaDTcontruct);
                holder.tendt=(TextView)convertView.findViewById(R.id.txtTenDTcontruct);
                Button btn = (Button)convertView.findViewById(R.id.btnXoaDT);
                danToc dt = dantoc.get(position);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbhp.deleteRow("delete from DanToc where Madt= '"+holder.madt.getText().toString()+"'");
                        Intent it = new Intent(context, danTocActi.class);
                        context.startActivity(it);
                    }
                });
                holder.madt.setText(dt.getMadt()+"");
                holder.tendt.setText(dt.getTendt()+"");

            }
        }
        return convertView;
    }

    public static  class ViewHolder{
        TextView madt;
        TextView tendt;
    }
}

