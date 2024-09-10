package com.lwglobatechsa.gestosdegastosfinal.ui.registrargastos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrarGastoViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public RegistrarGastoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Acá va todo lo de Registrar Gasto");
    }

    public LiveData<String> getText() {
        return mText;
    }
    // TODO: Implement the ViewModel
}