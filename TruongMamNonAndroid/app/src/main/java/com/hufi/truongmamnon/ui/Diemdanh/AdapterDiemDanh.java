package com.hufi.truongmamnon.ui.Diemdanh;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.hufi.truongmamnon.Class.DiemDanh;
import com.hufi.truongmamnon.Class.HocSinh;
import com.hufi.truongmamnon.R;
import com.hufi.truongmamnon.SQL.SQL;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterDiemDanh extends ArrayAdapter<DiemDanh> {
    Context context;
    int layoutResource;
    ArrayList<DiemDanh> data;

    public AdapterDiemDanh(@NonNull Context context, int resource, @NonNull ArrayList<DiemDanh> objects) {
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
        convertView = layoutInflater.inflate(layoutResource, parent, false);

        DiemDanh dd = data.get(position);

        Date ngayDiemDanh = dd.getNgay();
        String trangThai = dd.getTrangThai();
        String coPhep = dd.getCoPhep();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String ngayDiemDanhs = df.format(ngayDiemDanh);

        TextView lbNgayDiemDanh = convertView.findViewById(R.id.lbNgayDiemDanh);
        lbNgayDiemDanh.setText(ngayDiemDanhs);

        TextView lbTrangThai = convertView.findViewById(R.id.lbTrangThai);
        lbTrangThai.setText(trangThai);

        TextView lbCoPhep = convertView.findViewById(R.id.lbCoPhep);
        lbCoPhep.setText(coPhep);

        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.parseColor("#E6E6E6"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#F0F0F0"));
        }

        return convertView;
    }
}
