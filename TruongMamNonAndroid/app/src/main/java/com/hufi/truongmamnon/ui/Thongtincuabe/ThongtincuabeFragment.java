package com.hufi.truongmamnon.ui.Thongtincuabe;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hufi.truongmamnon.Class.HocSinh;
import com.hufi.truongmamnon.Class.LopHoc;
import com.hufi.truongmamnon.DrawerActivity;
import com.hufi.truongmamnon.Email.ConfigMail;
import com.hufi.truongmamnon.MainActivity;
import com.hufi.truongmamnon.R;
import com.hufi.truongmamnon.SQL.SQL;
import com.hufi.truongmamnon.databinding.FragmentThongtincuabeBinding;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ThongtincuabeFragment extends Fragment {

    private FragmentThongtincuabeBinding binding;

    TextView lbMa, lbHoTen, lbGioiTinh, lbNgaySinh, lbNoiSinh, lbLopHoc, lbNgayNhapHoc, lbDiaChi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //ThongtincuabeViewModel homeViewModel =
        //        new ViewModelProvider(this).get(ThongtincuabeViewModel.class);

        binding = FragmentThongtincuabeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        lbMa = root.findViewById(R.id.lbMa);
        lbHoTen = root.findViewById(R.id.lbHoTen);
        lbGioiTinh = root.findViewById(R.id.lbGioiTinh);
        lbNgaySinh = root.findViewById(R.id.lbNgaySinh);
        lbNoiSinh = root.findViewById(R.id.lbNoiSinh);
        lbLopHoc = root.findViewById(R.id.lbLopHoc);
        lbNgayNhapHoc = root.findViewById(R.id.lbNgayNhapHoc);
        lbDiaChi = root.findViewById(R.id.lbDiaChi);

        SQL db = new SQL();

        if (db.isConnected() == false) {
            Toast.makeText(getActivity(), "Kh??ng th??? k???t n???i ?????n server.", Toast.LENGTH_SHORT).show();
            return root;
        }

        /*ListView list = (ListView) root.findViewById(R.id.list);
        ArrayList<HocSinh> arrayList = new ArrayList<>();
        AdapterHocSinh adapter = new AdapterHocSinh(getContext(), R.layout.list_hocsinh, arrayList);
        list.setAdapter(adapter);
        adapter.clear();
        try {
            arrayList.addAll(db.getHocSinhList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        adapter.notifyDataSetChanged();

        try {
            db.Close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

        HocSinh hs = db.getHocSinh(ConfigMail.EMAIL_RECIEVED);

        String ma = hs.getMa();
        String ho = hs.getHo();
        String ten = hs.getTen();
        String gioiTinh = hs.getGioiTinh();
        String diaChi = hs.getDiaChi();

        int maLop = hs.getMaLop();
        LopHoc lh = db.getLopHoc(maLop);
        String tenLop = lh.getTenLop();

        /*String gioiTinhs = "";
        if (gioiTinh == true)
            gioiTinhs = "Nam";
        else gioiTinhs = "N???";*/

        Date ngaySinh = hs.getNgaySinh();
        String noiSinh = hs.getNoiSinh();
        Date ngayNhapHoc = hs.getNgayNhapHoc();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String ngaySinhs = df.format(ngaySinh);
        String ngayNhapHocs = df.format(ngayNhapHoc);

        lbMa.setText(lbMa.getText().toString() + ma);
        lbHoTen.setText(lbHoTen.getText().toString() + ho + " " + ten);
        lbGioiTinh.setText(lbGioiTinh.getText().toString() + gioiTinh);
        lbNgaySinh.setText(lbNgaySinh.getText().toString() + ngaySinhs);
        lbNoiSinh.setText(lbNoiSinh.getText().toString() + noiSinh);
        lbLopHoc.setText(lbLopHoc.getText().toString() + tenLop);
        lbNgayNhapHoc.setText(lbNgayNhapHoc.getText().toString() + ngayNhapHocs);
        lbDiaChi.setText(lbDiaChi.getText().toString() + diaChi);

        try {
            db.Close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}