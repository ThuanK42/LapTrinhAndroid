package Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baitapquanlynhansu.DatabaseHelper;
import com.example.baitapquanlynhansu.R;
import com.example.baitapquanlynhansu.trinhdohocvanActi;

import java.util.ArrayList;

import Contructor.trinhdohocvan;

public class trinhdohocvanAdapter extends ArrayAdapter<trinhdohocvan> {
    private ArrayList<trinhdohocvan> trinhdohv;
    Activity context =null;
    int resource;

    public trinhdohocvanAdapter(Activity context , int resource,   ArrayList<trinhdohocvan> trinhdohv ) {
        super(context,   resource,trinhdohv);
        this.trinhdohv = trinhdohv;
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


            if(trinhdohv.size()>0 && position>=0){

                holder.bangcaphv=(TextView)convertView.findViewById(R.id.txtBCHVContruct);
                holder.mall=(TextView)convertView.findViewById(R.id.txtMaLLcontruct);
                holder.diemhv=(TextView)convertView.findViewById(R.id.txtDiemHV);
                Button btn = (Button)convertView.findViewById(R.id.btnXoaTDHV);
                final    trinhdohocvan tdhv = trinhdohv.get(position);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbhp.deleteRow("delete from TrinhDoHocVan where BangCap= '"+holder.bangcaphv.getText().toString()+"'");
                        Intent it = new Intent(context, trinhdohocvanActi.class);
                        context.startActivity(it);
                    }
                });

                holder.bangcaphv.setText(tdhv.getBangcaphv());
                holder.mall.setText(tdhv.getMall());
                holder.diemhv.setText(tdhv.getDiemhv()+"");
            }
        }
        return convertView;
    }

    public static  class ViewHolder{
        TextView bangcaphv;
        TextView mall;
        TextView diemhv;

    }
}

