package com.example.comp4521_project.ui.balance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BalanceViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BalanceViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is balance fragment");
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}