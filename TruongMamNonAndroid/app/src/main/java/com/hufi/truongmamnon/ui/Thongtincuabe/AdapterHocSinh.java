package com.hufi.truongmamnon.ui.Thongtincuabe;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.hufi.truongmamnon.Class.HocSinh;
import com.hufi.truongmamnon.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        HocSinh hs = data.get(position);

        convertView = layoutInflater.inflate(layoutResource, parent, false);

        String ho = hs.getHo();
        String ten = hs.getTen();
        //boolean gioiTinh = hs.isGioiTinh();
        Date namSinh = hs.getNgaySinh();
        //int maLop = hs.getMaLop();

        /*String gioiTinhs = "";
        if (gioiTinh == true)
            gioiTinhs = "Nam";
        else gioiTinhs = "Nữ";

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String namSinhs = df.format(namSinh);

        TextView lbTen = convertView.findViewById(R.id.lbTen);
        lbTen.setText(ho + " " + ten);

        TextView lbGioiTinh = convertView.findViewById(R.id.lbGioiTinh);
        lbGioiTinh.setText(String.format("Giới tính: %s", gioiTinhs));

        TextView lbNamSinh = convertView.findViewById(R.id.lbNamSinh);
        lbNamSinh.setText(String.format("Năm sinh: %s", namSinhs));*/

        return convertView;
    }
}
