package com.lwglobatechsa.gestosdegastosfinal.ui.registrarempresa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrarEmpresaViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public RegistrarEmpresaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Acá va todo lo de Registrar Empresa");
    }

    public LiveData<String> getText() {
        return mText;
    }
    // TODO: Implement the ViewModel
}