package com.lwglobatechsa.gestosdegastosfinal.ui.listaempresas;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lwglobatechsa.gestosdegastosfinal.ClasesDAO;
import com.lwglobatechsa.gestosdegastosfinal.ui.clases.Empresa;
import com.lwglobatechsa.gestosdegastosfinal.R;
import com.lwglobatechsa.gestosdegastosfinal.databinding.FragmentListaEmpresasBinding;

import java.util.List;

public class ListaEmpresas extends Fragment {
    private LinearLayout linearLayoutHorizontal;
    private FragmentListaEmpresasBinding binding;
    ClasesDAO  clasesDAO;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListaEmpresasViewModel homeViewModel =
                new ViewModelProvider(this).get(ListaEmpresasViewModel.class);

        binding = FragmentListaEmpresasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        clasesDAO = new ClasesDAO();

        linearLayoutHorizontal = binding.LLvListaEmpresa;

        cargarEmpresas();

        return root;
    }
    private void cargarEmpresas() {

        clasesDAO.obtenerEmpresas(new ClasesDAO.FirestoreCallback<List<Empresa>>() {
            @Override
            public void onComplete(List<Empresa> empresas) {

                linearLayoutHorizontal.removeAllViews();

                if(empresas!=null){

                    for (Empresa empresa : empresas) {
                        View ventaItem = getLayoutInflater().inflate(R.layout.item_detalle_empresa, null);

                        TextView nombre = ventaItem.findViewById(R.id.txvNombreDeEmpresa);
                        TextView categoria = ventaItem.findViewById(R.id.txvCategoria);
                        TextView direccion = ventaItem.findViewById(R.id.txvDireccion);
                        TextView telefono = ventaItem.findViewById(R.id.txvTelefono);
                        Button editar = ventaItem.findViewById(R.id.btnEditarEmpresa);
                        Button eliminar = ventaItem.findViewById(R.id.btnEliminarEmpresa);

                        categoria.setText("Categoría: " + empresa.getCategoria());
                        nombre.setText(empresa.getNombre().toString());
                        direccion.setText("Dirección: " + empresa.getDireccion());
                        telefono.setText("Teléfono: " + empresa.getTelefono());

                        editar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                aparecerAlertEmpresa(empresa);
                            }
                        });

                        eliminar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                eliminarDeLaLista(empresa);
                            }
                        });

                        linearLayoutHorizontal.addView(ventaItem);
                    }
                }else{

                }
            }
        });
    }

    private void aparecerAlertEmpresa(final Empresa empresa) {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_alert_empresa, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext())
                .setView(dialogView)
                .setCancelable(false);

        AlertDialog dialog = dialogBuilder.create();

        Spinner categoria = dialogView.findViewById(R.id.spnNuevaCategoria);
        EditText direccion = dialogView.findViewById(R.id.edtNuevaDireccion);
        EditText telefono = dialogView.findViewById(R.id.edtNuevoTelefono);

        direccion.setText(empresa.getDireccion());
        telefono.setText(empresa.getTelefono());

        Button cancelar = dialogView.findViewById(R.id.btnCancelarEdicionEmpresa);
        Button guardar = dialogView.findViewById(R.id.btnGuardarEmpresa);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén los valores actualizados
                String nuevaCategoria = categoria.getSelectedItem().toString();
                String nuevaDireccion = direccion.getText().toString();
                String nuevoTelefono = telefono.getText().toString();

                if (categoria.getSelectedItemPosition() == 0){
                    Toast.makeText(getContext(), "Selecciona otra opción que no sea 'Selecciona una categoría'", Toast.LENGTH_SHORT).show();
                }else {
                    empresa.setCategoria(nuevaCategoria);
                    empresa.setDireccion(nuevaDireccion);
                    empresa.setTelefono(nuevoTelefono);


                    // Actualiza la empresa en la base de datos
                    clasesDAO.actualizarEmpresa(empresa, new ClasesDAO.FirestoreCallback<Boolean>() {
                        @Override
                        public void onComplete(Boolean result) {
                            if (result) {
                                Toast.makeText(getContext(), "Empresa actualizada con éxito", Toast.LENGTH_SHORT).show();
                                cargarEmpresas(); // Refresca la lista de empresas
                            } else {
                                Toast.makeText(getContext(), "Error al actualizar la empresa", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    // Cierra el dialog
                    dialog.dismiss();
                }
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

    private void eliminarDeLaLista(Empresa empresa){

        ClasesDAO clasesDAO = new ClasesDAO();
        String nombreEmpresaAEliminar = empresa.getNombre();

        clasesDAO.eliminarEmpresaPorNombre(nombreEmpresaAEliminar, new ClasesDAO.FirestoreCallback<Boolean>() {
            @Override
            public void onComplete(Boolean result) {
                if (result) {
                    Toast.makeText(getContext(), "Empresa eliminada con éxito", Toast.LENGTH_SHORT).show();

                    cargarEmpresas();
                } else {
                    Toast.makeText(getContext(), "Error al eliminar la empresa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}