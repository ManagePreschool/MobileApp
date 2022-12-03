package com.hufi.truongmamnon.ui.Thongtincuabe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ThongtincuabeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ThongtincuabeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}