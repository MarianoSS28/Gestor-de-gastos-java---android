// Generated by view binder compiler. Do not edit!
package com.lwglobatechsa.gestosdegastosfinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lwglobatechsa.gestosdegastosfinal.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogAlertGastoBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnCancelarEdicionGasto;

  @NonNull
  public final Button btnGuarfarGasto;

  @NonNull
  public final EditText edtNuevaDescripcion;

  @NonNull
  public final EditText edtNuevoMonto;

  @NonNull
  public final Spinner spnListaEmpresaEditar;

  private DialogAlertGastoBinding(@NonNull LinearLayout rootView,
      @NonNull Button btnCancelarEdicionGasto, @NonNull Button btnGuarfarGasto,
      @NonNull EditText edtNuevaDescripcion, @NonNull EditText edtNuevoMonto,
      @NonNull Spinner spnListaEmpresaEditar) {
    this.rootView = rootView;
    this.btnCancelarEdicionGasto = btnCancelarEdicionGasto;
    this.btnGuarfarGasto = btnGuarfarGasto;
    this.edtNuevaDescripcion = edtNuevaDescripcion;
    this.edtNuevoMonto = edtNuevoMonto;
    this.spnListaEmpresaEditar = spnListaEmpresaEditar;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogAlertGastoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogAlertGastoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_alert_gasto, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogAlertGastoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnCancelarEdicionGasto;
      Button btnCancelarEdicionGasto = ViewBindings.findChildViewById(rootView, id);
      if (btnCancelarEdicionGasto == null) {
        break missingId;
      }

      id = R.id.btnGuarfarGasto;
      Button btnGuarfarGasto = ViewBindings.findChildViewById(rootView, id);
      if (btnGuarfarGasto == null) {
        break missingId;
      }

      id = R.id.edtNuevaDescripcion;
      EditText edtNuevaDescripcion = ViewBindings.findChildViewById(rootView, id);
      if (edtNuevaDescripcion == null) {
        break missingId;
      }

      id = R.id.edtNuevoMonto;
      EditText edtNuevoMonto = ViewBindings.findChildViewById(rootView, id);
      if (edtNuevoMonto == null) {
        break missingId;
      }

      id = R.id.spnListaEmpresaEditar;
      Spinner spnListaEmpresaEditar = ViewBindings.findChildViewById(rootView, id);
      if (spnListaEmpresaEditar == null) {
        break missingId;
      }

      return new DialogAlertGastoBinding((LinearLayout) rootView, btnCancelarEdicionGasto,
          btnGuarfarGasto, edtNuevaDescripcion, edtNuevoMonto, spnListaEmpresaEditar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
