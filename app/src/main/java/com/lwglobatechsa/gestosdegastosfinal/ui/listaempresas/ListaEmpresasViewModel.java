package com.lwglobatechsa.gestosdegastosfinal.ui.listaempresas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListaEmpresasViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ListaEmpresasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ListaEmpresa");
    }

    public LiveData<String> getText() {
        return mText;
    }
    // TODO: Implement the ViewModel
}