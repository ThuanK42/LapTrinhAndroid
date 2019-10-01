package com.thuannluit.thuchanhfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Top extends Fragment {
    private EditText txttenXe, txtNamSX;
    private ImageButton btnXacNhaN;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        txttenXe = (EditText) view.findViewById(R.id.txtTenXe);
        txtNamSX = (EditText) view.findViewById(R.id.txtNamSX);
        btnXacNhaN = (ImageButton) view.findViewById(R.id.btnXacNhan);

        btnXacNhaN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtTenXeB=(TextView) getActivity().findViewById(R.id.txtTenXeB);
                TextView txtNamSXB=(TextView) getActivity().findViewById(R.id.txtNamSXB);
                txtTenXeB.setText(txttenXe.getText().toString());
                txtNamSXB.setText(txtNamSX.getText().toString());
            }
        });

        return view;
    }
}
