package com.lwglobatechsa.gestosdegastosfinal.ui.historialgastos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistorialGastosViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public HistorialGastosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Acá va todo lo de Historial de gastos");
    }

    public LiveData<String> getText() {
        return mText;
    }
    // TODO: Implement the ViewModel
}