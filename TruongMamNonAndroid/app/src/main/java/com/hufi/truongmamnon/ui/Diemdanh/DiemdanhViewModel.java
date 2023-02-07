package com.hufi.truongmamnon.ui.Diemdanh;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DiemdanhViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DiemdanhViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}