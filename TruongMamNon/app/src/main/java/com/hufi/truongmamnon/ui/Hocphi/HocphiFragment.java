package com.hufi.truongmamnon.ui.Hocphi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hufi.truongmamnon.databinding.FragmentHocphiBinding;

public class HocphiFragment extends Fragment {

    private FragmentHocphiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //HocphiViewModel slideshowViewModel =
        //        new ViewModelProvider(this).get(HocphiViewModel.class);

        binding = FragmentHocphiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textSlideshow;
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}