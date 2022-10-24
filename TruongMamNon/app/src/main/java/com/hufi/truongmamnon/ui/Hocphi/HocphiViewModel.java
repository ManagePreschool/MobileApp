package com.hufi.truongmamnon.ui.Hocphi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HocphiViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HocphiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}