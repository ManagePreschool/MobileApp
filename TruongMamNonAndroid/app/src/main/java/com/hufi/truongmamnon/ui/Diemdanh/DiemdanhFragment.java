package com.hufi.truongmamnon.ui.Diemdanh;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hufi.truongmamnon.Class.DiemDanh;
import com.hufi.truongmamnon.Class.HocSinh;
import com.hufi.truongmamnon.Class.LopHoc;
import com.hufi.truongmamnon.Email.ConfigMail;
import com.hufi.truongmamnon.R;
import com.hufi.truongmamnon.SQL.SQL;
import com.hufi.truongmamnon.databinding.FragmentDiemdanhBinding;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DiemdanhFragment extends Fragment {

    private FragmentDiemdanhBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //ThongtincuabeViewModel homeViewModel =
        //        new ViewModelProvider(this).get(ThongtincuabeViewModel.class);

        binding = FragmentDiemdanhBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        SQL db = new SQL();

        if (db.isConnected() == false) {
            Toast.makeText(getActivity(), "Không thể kết nối đến server.", Toast.LENGTH_SHORT).show();
            return root;
        }

        HocSinh hs = db.getHocSinh(ConfigMail.MA);
        int maHS = hs.getMa();

        int vangSang = 0, vangChieu = 0, vangCaNgay = 0;
        int khongPhep = 0, coPhep = 0;
        try {
            vangCaNgay = db.getDiemDanhVangCaNgay(Calendar.getInstance().getTime(), maHS);
            vangSang = db.getDiemDanhVangSang(Calendar.getInstance().getTime(), maHS);
            vangChieu = db.getDiemDanhVangChieu(Calendar.getInstance().getTime(), maHS);
            khongPhep = db.getDiemDanhVangKhongPhep(Calendar.getInstance().getTime(), maHS);
            coPhep = db.getDiemDanhVangCoPhep(Calendar.getInstance().getTime(), maHS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        TextView lbVangCaNgay = (TextView) root.findViewById(R.id.lbVangCaNgay);
        TextView lbVangSang = (TextView) root.findViewById(R.id.lbVangSang);
        TextView lbVangChieu = (TextView) root.findViewById(R.id.lbVangChieu);
        TextView lbKhongPhep = (TextView) root.findViewById(R.id.lbKhongPhep);
        TextView lbCoPhep = (TextView) root.findViewById(R.id.lbCoPhep);

        lbVangCaNgay.setText(lbVangCaNgay.getText().toString() + vangCaNgay);
        lbVangSang.setText(lbVangSang.getText().toString() + vangSang);
        lbVangChieu.setText(lbVangChieu.getText().toString() + vangChieu);
        lbKhongPhep.setText(lbKhongPhep.getText().toString() + khongPhep);
        lbCoPhep.setText(lbCoPhep.getText().toString() + coPhep);

        ListView list = (ListView) root.findViewById(R.id.listDiemDanh);
        ArrayList<DiemDanh> arrayList = new ArrayList<>();
        AdapterDiemDanh adapter = new AdapterDiemDanh(getContext(), R.layout.list_diemdanh, arrayList);
        list.setAdapter(adapter);
        adapter.clear();

        try {
            arrayList.addAll(db.getDiemDanhList(Calendar.getInstance().getTime(), maHS));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        adapter.notifyDataSetChanged();



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