package com.lwglobatechsa.gestosdegastosfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText correo,contra;
    Button login;
    private TextView textViewRegister;
    private ClasesDAO clasesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = findViewById(R.id.edtCorreo);
        contra = findViewById(R.id.edtContrasena);
        login = findViewById(R.id.btnLogin);
        textViewRegister = findViewById(R.id.textViewRegister);

        // Inicializa la instancia de ClasesDAO
        clasesDAO = new ClasesDAO();

        login.setOnClickListener(v -> loginUser());
        textViewRegister.setOnClickListener(v -> startActivity(new Intent(Login.this, CrearCuenta.class)));
    }


    private void loginUser() {
        String email = correo.getText().toString().trim();
        String password = contra.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica que clasesDAO no sea nulo
        if (clasesDAO == null) {
            Toast.makeText(this, "Error en la inicialización de DAO", Toast.LENGTH_SHORT).show();
            return;
        }

        clasesDAO.iniciarSesion(email, password, new ClasesDAO.FirestoreCallback<Boolean>() {
            @Override
            public void onComplete(Boolean success) {
                if (success) {
                    Toast.makeText(Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(Login.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}