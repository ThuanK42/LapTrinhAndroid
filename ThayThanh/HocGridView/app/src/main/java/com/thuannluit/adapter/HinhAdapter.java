package com.thuannluit.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thuannluit.hocgridview.R;

import java.util.List;

public class HinhAdapter extends ArrayAdapter<Integer> {
    @NonNull Activity context; int resource; @NonNull List<Integer> objects;
    public HinhAdapter(@NonNull Activity context, int resource, @NonNull List<Integer> objects) {
        super(context, resource, objects);
        this.context=context;
        this.objects=objects;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        ImageView img=(ImageView) row.findViewById(R.id.imgHinh);
        img.setImageResource(this.objects.get(position));
        return row;
    }
}
