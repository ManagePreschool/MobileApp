package com.hufi.truongmamnon.ui.Tinhhinhsuckhoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hufi.truongmamnon.databinding.FragmentTinhhinhsuckhoeBinding;

public class Tinhhinhsuckhoe extends Fragment {

    private FragmentTinhhinhsuckhoeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //TinhhinhsuckhoeViewModel galleryViewModel =
        //        new ViewModelProvider(this).get(TinhhinhsuckhoeViewModel.class);

        binding = FragmentTinhhinhsuckhoeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textGallery;
        //galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}