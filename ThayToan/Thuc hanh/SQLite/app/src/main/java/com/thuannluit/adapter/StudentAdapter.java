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
import com.thuannluit.model.Student;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    Activity context;
    int resource;
    List<Student> objects;

    public StudentAdapter(@NonNull Activity context, int resource, @NonNull List<Student> objects) {
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

        //anh xa view -> item_student
        TextView textView = (TextView) row.findViewById(R.id.tv_sinhvien);

        // gan gia tri
        Student student = this.objects.get(position);

        textView.setText(student.toString());

        return row;
    }
}
