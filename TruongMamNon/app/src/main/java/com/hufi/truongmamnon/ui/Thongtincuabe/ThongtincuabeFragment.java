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
import com.hufi.truongmamnon.MainActivity;
import com.hufi.truongmamnon.R;
import com.hufi.truongmamnon.SQL.SQL;
import com.hufi.truongmamnon.databinding.FragmentThongtincuabeBinding;

import java.sql.SQLException;
import java.util.ArrayList;


public class ThongtincuabeFragment extends Fragment {

    private FragmentThongtincuabeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //ThongtincuabeViewModel homeViewModel =
        //        new ViewModelProvider(this).get(ThongtincuabeViewModel.class);

        binding = FragmentThongtincuabeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        SQL db = new SQL();

        if (db.isConnected() == false) {
            Toast.makeText(getActivity(), "Không thể kết nối đến server.", Toast.LENGTH_SHORT).show();
            return root;
        }

        ListView list = (ListView) root.findViewById(R.id.list);
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
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}