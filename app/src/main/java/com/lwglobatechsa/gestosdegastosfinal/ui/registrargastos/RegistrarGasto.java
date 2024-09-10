package com.lwglobatechsa.gestosdegastosfinal.ui.registrargastos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lwglobatechsa.gestosdegastosfinal.ClasesDAO;
import com.lwglobatechsa.gestosdegastosfinal.ui.clases.Gasto;
import com.lwglobatechsa.gestosdegastosfinal.databinding.FragmentRegistrarGastoBinding;

import java.util.List;
import java.util.UUID;

public class RegistrarGasto extends Fragment {

    private EditText descripcion,monto;
    private Spinner listaEmpresas;
    private Button registrarGasto;

    ClasesDAO clasesDAO;

    private FragmentRegistrarGastoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegistrarGastoViewModel homeViewModel =
                new ViewModelProvider(this).get(RegistrarGastoViewModel.class);

        binding = FragmentRegistrarGastoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        clasesDAO = new ClasesDAO();

        descripcion = binding.edtDescripcionGasto;
        monto = binding.edtMontoGasto;

        listaEmpresas = binding.spnListaEmpresas;

        llenarSpinnerEmpresas();

        registrarGasto = binding.btnRegistrarGasto;

        registrarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = generarID();
                String desc = descripcion.getText().toString();
                String lisEm = listaEmpresas.getSelectedItem().toString();
                String montoTexto = monto.getText().toString();
                double mon = 0;
                try {
                    mon = Double.parseDouble(montoTexto);
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Ingrese un monto válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                Gasto objGasto = new Gasto(id,lisEm, desc, mon);

                clasesDAO.agregarGasto(objGasto,id);

                limpiarEntradas();

                Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_LONG).show();

            }
        });

        return root;
    }

    private String generarID(){
        return UUID.randomUUID().toString();
    }


    private void llenarSpinnerEmpresas() {
        clasesDAO.obtenerNombresEmpresas(new ClasesDAO.FirestoreCallback<List<String>>() {
            @Override
            public void onComplete(List<String> result) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_spinner_item,
                        result
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                listaEmpresas.setAdapter(adapter);
            }
        });
    }

    private void limpiarEntradas(){
        descripcion.setText("");
        monto.setText("");
        listaEmpresas.setSelection(0);
    }

}