package com.lwglobatechsa.gestosdegastosfinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CrearCuenta extends AppCompatActivity {

    EditText correo,contra,contra2,nombre;
    Button crearCuenta;
    private TextView textViewLogin;
    private ClasesDAO clasesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        clasesDAO = new ClasesDAO();

        nombre = findViewById(R.id.edtNombreUsuario);
        correo = findViewById(R.id.edtCorreo);
        contra = findViewById(R.id.edtContrasena);
        contra2 = findViewById(R.id.edtConfirmarContra);

        crearCuenta = findViewById(R.id.btnCrearCuenta);
        textViewLogin = findViewById(R.id.textViewLogin);

        crearCuenta.setOnClickListener(v -> createAccount());
        textViewLogin.setOnClickListener(v -> finish()); // Vuelve a la pantalla de login
    }

    private void createAccount() {
        String name = nombre.getText().toString().trim();
        String email = correo.getText().toString().trim();
        String password = contra.getText().toString().trim();
        String confirmPassword = contra2.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        clasesDAO.registrarUsuario(email, password, new ClasesDAO.FirestoreCallback<Boolean>() {
            @Override
            public void onComplete(Boolean success) {
                if (success) {
                    Toast.makeText(CrearCuenta.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CrearCuenta.this, "Error al crear la cuenta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}