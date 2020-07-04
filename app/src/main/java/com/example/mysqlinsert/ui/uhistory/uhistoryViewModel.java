package com.example.mysqlinsert.ui.uhistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class uhistoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public uhistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}