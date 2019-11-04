package com.example.baitapquanlynhansu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    private  ArrayList<User> user;
    Activity context =null;
    int resource;

    public UserAdapter(Activity context , int resource,   ArrayList<User> user ) {
        super(context,   resource,user);
        this.user = user;
        this.context = context;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView  ==null){
            LayoutInflater inflater  =context.getLayoutInflater();

            final ViewHolder holder = new ViewHolder();
            convertView=inflater.inflate(resource,null);
            if(user.size()>0 && position>=0){

                holder.username=(TextView)convertView.findViewById(R.id.txtUsername);
                holder.password=(TextView)convertView.findViewById(R.id.txtPassword);

                User us = user.get(position);

                holder.username.setText(us.getUsername()+"");
                holder.password.setText(us.getPassword()+"");

            }
        }
        return convertView;
    }

    public static  class ViewHolder{
        TextView username;
        TextView password;
    }
}
