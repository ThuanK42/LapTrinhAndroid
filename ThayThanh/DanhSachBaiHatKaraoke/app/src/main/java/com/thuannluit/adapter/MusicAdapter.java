package com.thuannluit.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thuannluit.listkaraoke.R;
import com.thuannluit.models.Music;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<Music> {
    @NonNull
    Activity contex;
    int resource;
    @NonNull
    List<Music> objects;

    public MusicAdapter(@NonNull Activity context, int resource, @NonNull List<Music> objects) {
        super(context, resource, objects);
        this.contex = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.contex.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        TextView txtMaSoBaiHat = (TextView) row.findViewById(R.id.txtMaSoBaiHat);
        TextView txtTenBaiHat = (TextView) row.findViewById(R.id.txtTenBaiHat);
        TextView txtTenCaSi = (TextView) row.findViewById(R.id.txtTenCaSi);
        ImageButton btnLike = (ImageButton)row.findViewById(R.id.btnLike);
        ImageButton btnDislike = (ImageButton)row.findViewById(R.id.btnDislike);

        final Music music = this.objects.get(position);
        txtMaSoBaiHat.setText(music.getMa());
        txtTenBaiHat.setText(music.getTenBaiHat());
        txtTenCaSi.setText(music.getCaSi());

        if (music.isThich()){
            btnLike.setVisibility(View.INVISIBLE);
            btnDislike.setVisibility(View.VISIBLE);
        }else {
            btnLike.setVisibility(View.VISIBLE);
            btnDislike.setVisibility(View.INVISIBLE);
        }

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThich(music);
            }
        });

        return row;
    }

    private void xuLyThich(Music music) {
        music.setThich(true);
    }
}
