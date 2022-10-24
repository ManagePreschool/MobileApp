package com.hufi.truongmamnon.ui.Thongtincuabe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hufi.truongmamnon.Class.HocSinh;
import com.hufi.truongmamnon.R;

import java.util.ArrayList;

public class AdapterHocSinh extends ArrayAdapter<HocSinh> {
    Context context;
    int layoutResource;
    ArrayList<HocSinh> data;

    public AdapterHocSinh(@NonNull Context context, int resource, @NonNull ArrayList<HocSinh> objects) {
        super(context, resource, objects);
        this.data=objects;
        this.layoutResource=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        HocSinh hs = data.get(position);

        convertView = layoutInflater.inflate(layoutResource, parent, false);

        String ten = hs.getTen();
        String gioiTinh = hs.getGioiTinh();
        String namSinh = hs.getNamSinh();
        String maLop = hs.getMaLop();


        TextView lbTen = convertView.findViewById(R.id.lbTen);
        lbTen.setText(ten);

        TextView lbGioiTinh = convertView.findViewById(R.id.lbGioiTinh);
        lbGioiTinh.setText(String.format("Giới tính: %s", gioiTinh));

        TextView lbNamSinh = convertView.findViewById(R.id.lbNamSinh);
        lbNamSinh.setText(String.format("Năm sinh: %s", namSinh));

        return convertView;
    }
}
