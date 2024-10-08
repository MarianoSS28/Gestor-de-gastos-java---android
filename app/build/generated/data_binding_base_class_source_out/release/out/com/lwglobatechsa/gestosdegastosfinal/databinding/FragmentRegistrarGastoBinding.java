// Generated by view binder compiler. Do not edit!
package com.lwglobatechsa.gestosdegastosfinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lwglobatechsa.gestosdegastosfinal.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentRegistrarGastoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnRegistrarGasto;

  @NonNull
  public final EditText edtDescripcionGasto;

  @NonNull
  public final EditText edtMontoGasto;

  @NonNull
  public final ConstraintLayout frameLayout3;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final Spinner spnListaEmpresas;

  private FragmentRegistrarGastoBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnRegistrarGasto, @NonNull EditText edtDescripcionGasto,
      @NonNull EditText edtMontoGasto, @NonNull ConstraintLayout frameLayout3,
      @NonNull ImageView imageView3, @NonNull Spinner spnListaEmpresas) {
    this.rootView = rootView;
    this.btnRegistrarGasto = btnRegistrarGasto;
    this.edtDescripcionGasto = edtDescripcionGasto;
    this.edtMontoGasto = edtMontoGasto;
    this.frameLayout3 = frameLayout3;
    this.imageView3 = imageView3;
    this.spnListaEmpresas = spnListaEmpresas;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRegistrarGastoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRegistrarGastoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_registrar_gasto, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRegistrarGastoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnRegistrarGasto;
      Button btnRegistrarGasto = ViewBindings.findChildViewById(rootView, id);
      if (btnRegistrarGasto == null) {
        break missingId;
      }

      id = R.id.edtDescripcionGasto;
      EditText edtDescripcionGasto = ViewBindings.findChildViewById(rootView, id);
      if (edtDescripcionGasto == null) {
        break missingId;
      }

      id = R.id.edtMontoGasto;
      EditText edtMontoGasto = ViewBindings.findChildViewById(rootView, id);
      if (edtMontoGasto == null) {
        break missingId;
      }

      ConstraintLayout frameLayout3 = (ConstraintLayout) rootView;

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.spnListaEmpresas;
      Spinner spnListaEmpresas = ViewBindings.findChildViewById(rootView, id);
      if (spnListaEmpresas == null) {
        break missingId;
      }

      return new FragmentRegistrarGastoBinding((ConstraintLayout) rootView, btnRegistrarGasto,
          edtDescripcionGasto, edtMontoGasto, frameLayout3, imageView3, spnListaEmpresas);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
