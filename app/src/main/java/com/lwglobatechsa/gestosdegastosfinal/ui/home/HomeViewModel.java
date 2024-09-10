package com.lwglobatechsa.gestosdegastosfinal.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Bienvenido a la aplicación para la gestión de gastos. " + "\n" +
                ":D");
    }

    public LiveData<String> getText() {
        return mText;
    }
}