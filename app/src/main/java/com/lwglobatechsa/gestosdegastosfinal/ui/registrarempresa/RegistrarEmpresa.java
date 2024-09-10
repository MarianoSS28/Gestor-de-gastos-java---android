package com.lwglobatechsa.gestosdegastosfinal.ui.registrarempresa;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lwglobatechsa.gestosdegastosfinal.ClasesDAO;
import com.lwglobatechsa.gestosdegastosfinal.ui.clases.Empresa;
import com.lwglobatechsa.gestosdegastosfinal.databinding.FragmentRegistrarEmpresaBinding;

public class RegistrarEmpresa extends Fragment {

    private EditText nombre_empresa, direccion, telefono;
    private Spinner listacategoria;
    private Button registrar_empresa;
    ClasesDAO clasesDAO;

    private FragmentRegistrarEmpresaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        RegistrarEmpresaViewModel homeViewModel =
                new ViewModelProvider(this).get(RegistrarEmpresaViewModel.class);

        binding = FragmentRegistrarEmpresaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        clasesDAO = new ClasesDAO();

        nombre_empresa    = binding.edtIngresarNombreEmpresa;
        direccion         = binding.edtDireccionEmpresa;
        telefono          = binding.edtTelefonoEmpresa;
        listacategoria    = binding.spnCategoriaEmpresa;
        registrar_empresa = binding.btnRegistrarEmpresa;

        registrar_empresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = nombre_empresa.getText().toString();
                String cat = listacategoria.getSelectedItem().toString();
                String dir = direccion.getText().toString();
                String tel = telefono.getText().toString();

                if (listacategoria.getSelectedItemPosition() == 0){
                    Toast.makeText(getContext(), "Selecciona otra opción que no sea 'Selecciona una categoría'", Toast.LENGTH_SHORT).show();
                }else {

                    Empresa objEmpresa = new Empresa(nom, cat, dir,tel);

                    clasesDAO.agregarEmpresa(objEmpresa,nom);

                    limpiarEntradas();

                    Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                }

            }
        });

        return root;
    }

    private void limpiarEntradas(){
        nombre_empresa.setText("");
        direccion.setText("");
        telefono.setText("");
        listacategoria.setSelection(0);

        nombre_empresa.requestFocus();
    }

}