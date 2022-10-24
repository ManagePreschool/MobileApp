package com.hufi.truongmamnon.ui.Tinhhinhsuckhoe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TinhhinhsuckhoeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TinhhinhsuckhoeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}