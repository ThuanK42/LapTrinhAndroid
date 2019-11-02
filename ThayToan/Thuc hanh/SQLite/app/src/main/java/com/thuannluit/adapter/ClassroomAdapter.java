package com.thuannluit.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thuannluit.learnsqlite.R;
import com.thuannluit.model.Classroom;

import java.util.List;

public class ClassroomAdapter extends ArrayAdapter<Classroom> {
    Activity context;
    int resource;
    List<Classroom> objects;

    public ClassroomAdapter(@NonNull Activity context, int resource, @NonNull List<Classroom> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        //anh xa view -> item_classroom
        TextView textView = (TextView) row.findViewById(R.id.tv_lop);

        // gan gia tri
        Classroom classroom = this.objects.get(position);
        textView.setText(classroom.toString());
        return row;
    }
}
