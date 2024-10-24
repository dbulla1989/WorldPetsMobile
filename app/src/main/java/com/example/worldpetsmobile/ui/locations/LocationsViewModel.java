package com.example.worldpetsmobile.ui.locations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LocationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LocationsViewModel (){
        mText = new MutableLiveData<>();
        mText.setValue("This is maps fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
