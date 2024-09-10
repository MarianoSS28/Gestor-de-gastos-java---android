package com.lwglobatechsa.gestosdegastosfinal.ui.historialgastos;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lwglobatechsa.gestosdegastosfinal.ClasesDAO;
import com.lwglobatechsa.gestosdegastosfinal.R;

import java.util.List;
import com.lwglobatechsa.gestosdegastosfinal.databinding.FragmentHistorialGastosBinding;
import com.lwglobatechsa.gestosdegastosfinal.ui.clases.Gasto;

public class HistorialGastos extends Fragment {
    private LinearLayout linearLayoutHorizontal;
    private FragmentHistorialGastosBinding binding;
    ClasesDAO clasesDAO;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistorialGastosViewModel homeViewModel =
                new ViewModelProvider(this).get(HistorialGastosViewModel.class);

        binding = FragmentHistorialGastosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        clasesDAO = new ClasesDAO();

        linearLayoutHorizontal = binding.LLvHistorialGasto;

        cargarGastos();

        return root;
    }

    private void cargarGastos() {

        clasesDAO.obtenerGastos(new ClasesDAO.FirestoreCallback<List<Gasto>>() {
            @Override
            public void onComplete(List<Gasto> gastos) {

                linearLayoutHorizontal.removeAllViews();

                if(gastos!=null){

                    for (Gasto gasto : gastos) {
                        View ventaItem = getLayoutInflater().inflate(R.layout.item_detalle_gasto, null);

                        TextView id = ventaItem.findViewById(R.id.txvID);
                        TextView nombreEmpresa = ventaItem.findViewById(R.id.txvNombreEmpresa);
                        TextView descripcion = ventaItem.findViewById(R.id.txvDescripcion);
                        TextView monto = ventaItem.findViewById(R.id.txvMonto);

                        Button editar = ventaItem.findViewById(R.id.btnEditarGasto);
                        Button eliminar = ventaItem.findViewById(R.id.btnEliminarGasto);

                        descripcion.setText("Descripción: " + gasto.getNescripcion());
                        nombreEmpresa.setText("Empresa: " + gasto.getNombreEmpresa());
                        monto.setText("Monto: " + gasto.getMonto());
                        id.setText(gasto.getID().toString());


                        editar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                aparecerAlertGasto(gasto);
                            }
                        });

                        eliminar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                eliminarDeLaLista(gasto);
                            }
                        });

                        linearLayoutHorizontal.addView(ventaItem);
                    }
                }else{

                }
            }
        });
    }

    private void aparecerAlertGasto(final Gasto gasto) {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_alert_gasto, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext())
                .setView(dialogView)
                .setCancelable(false);

        AlertDialog dialog = dialogBuilder.create();

        // Configura los elementos del layout del dialog
        Spinner nombreEmpresa = dialogView.findViewById(R.id.spnListaEmpresaEditar);
        EditText descripcion = dialogView.findViewById(R.id.edtNuevaDescripcion);
        EditText monto = dialogView.findViewById(R.id.edtNuevoMonto);


        clasesDAO.obtenerNombresEmpresas(new ClasesDAO.FirestoreCallback<List<String>>() {
            @Override
            public void onComplete(List<String> result) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_spinner_item,
                        result
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                nombreEmpresa.setAdapter(adapter);
            }
        });

        descripcion.setText(gasto.getNescripcion());
        monto.setText(""+gasto.getMonto());

        Button cancelar = dialogView.findViewById(R.id.btnCancelarEdicionGasto);
        Button guardar = dialogView.findViewById(R.id.btnGuarfarGasto);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevaNombreEmpresa = nombreEmpresa.getSelectedItem().toString();
                String nuevaDescripcion = descripcion.getText().toString();
                double nuevoMonto = Double.parseDouble(monto.getText().toString());

                    gasto.setNombreEmpresa(nuevaNombreEmpresa);
                    gasto.setNescripcion(nuevaDescripcion);
                    gasto.setMonto(nuevoMonto);

                    clasesDAO.actualizarGasto(gasto, new ClasesDAO.FirestoreCallback<Boolean>() {
                        @Override
                        public void onComplete(Boolean result) {
                            if (result) {
                                Toast.makeText(getContext(), "Gasto actualizado con éxito", Toast.LENGTH_SHORT).show();
                                cargarGastos(); // Refresca la lista de empresas
                            } else {
                                Toast.makeText(getContext(), "Error al actualizar el gasto", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    // Cierra el dialog
                    dialog.dismiss();
                }

        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void eliminarDeLaLista(Gasto gasto){

        ClasesDAO clasesDAO = new ClasesDAO();
        String IdParaEliminar = gasto.getID();

        clasesDAO.eliminarGastoPorID(IdParaEliminar, new ClasesDAO.FirestoreCallback<Boolean>() {
            @Override
            public void onComplete(Boolean result) {
                if (result) {
                    Toast.makeText(getContext(), "Gasto eliminada con éxito", Toast.LENGTH_SHORT).show();

                    cargarGastos();
                } else {
                    Toast.makeText(getContext(), "Error al eliminar el gasto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}