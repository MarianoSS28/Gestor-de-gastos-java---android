package com.lwglobatechsa.gestosdegastosfinal;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.lwglobatechsa.gestosdegastosfinal.ui.clases.Empresa;
import com.lwglobatechsa.gestosdegastosfinal.ui.clases.Gasto;

import java.util.ArrayList;
import java.util.List;

public class ClasesDAO {

    private FirebaseFirestore db;
    CollectionReference ref;
    private FirebaseAuth mAuth;

    public ClasesDAO() {
        this.db = FirebaseFirestore.getInstance();
        this.mAuth = FirebaseAuth.getInstance();
    }

    public interface FirestoreCallback<T> {
        void onComplete(T result);
    }

    //MÉTODOS PARA AGREGAR LOS DATOS
    //Método para agregar los datos de "Gastos"
    public void agregarGasto(Gasto gasto, String documentoId) {

        ref = db.collection("Gasto");

        // Si no se proporciona un ID de documento, generamos uno automáticamente
        if (documentoId == null || documentoId.isEmpty()) {
            documentoId = ref.document().getId();
        }

        ref.document(documentoId).set(gasto.toMap())
                .addOnSuccessListener(aVoid -> {
                    System.out.println("Empresa añadida con ID: " );
                })
                .addOnFailureListener(e -> {
                    System.err.println("Error añadiendo empresa: " + e.getMessage());
                });
    }
    //Método para agregar los datos de "Empresas"
    public void agregarEmpresa(Empresa empresa, String documentoId) {
        ref = db.collection("Empresa");

        // Si no se proporciona un ID de documento, generamos uno automáticamente
        if (documentoId == null || documentoId.isEmpty()) {
            documentoId = ref.document().getId();
        }

        ref.document(documentoId).set(empresa.toMap())
                .addOnSuccessListener(aVoid -> {
                    System.out.println("Empresa añadida con ID: " );
                })
                .addOnFailureListener(e -> {
                    System.err.println("Error añadiendo empresa: " + e.getMessage());
                });
    }

    //MÉTODOS PARA OBTENER LOS DATOS
    //Método para obtener los datos de "Gastos"
    public void obtenerGastos(final FirestoreCallback<List<Gasto>> callback) {

        CollectionReference gastoRef = db.collection("Gasto");

        gastoRef.get().addOnCompleteListener(task -> {

            List<Gasto> gastos = new ArrayList<>();

            if (task.isSuccessful()) {

                for (QueryDocumentSnapshot document : task.getResult()) {

                    String id = document.getString("ID");
                    String nombreempresa = document.getString("NombreEmpresa");
                    String descripcion = document.getString("Descripcion");
                    Double monto = document.getDouble("Monto");

                    Gasto objGasto = new Gasto(id,nombreempresa,descripcion,monto);

                    gastos.add(objGasto);

                }

            }
            callback.onComplete(gastos);

        });
    }
    //Método para obtener los datos de "Empresa"
    public void obtenerEmpresas(final FirestoreCallback<List<Empresa>> callback) {

        CollectionReference empresaRef = db.collection("Empresa");

        empresaRef.get().addOnCompleteListener(task -> {

            List<Empresa> empresas = new ArrayList<>();

            if (task.isSuccessful()) {

                for (QueryDocumentSnapshot document : task.getResult()) {

                    String nombre = document.getString("Nombre");
                    String categoria = document.getString("Categoria");
                    String direccion = document.getString("Direccion");
                    String telefono = document.getString("Telefono");

                    Empresa objEmpresa = new Empresa(nombre,categoria,direccion,telefono);

                    empresas.add(objEmpresa);

                }

            }
            callback.onComplete(empresas);

        });
    }

    //Obtener nombres para el spinner
    public void obtenerNombresEmpresas(final FirestoreCallback<List<String>> callback) {
        CollectionReference empresaRef = db.collection("Empresa");

        empresaRef.get().addOnCompleteListener(task -> {
            List<String> nombresEmpresas = new ArrayList<>();

            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String nombre = document.getString("Nombre");
                    if (nombre != null && !nombre.isEmpty()) {
                        nombresEmpresas.add(nombre);
                    }
                }
            }
            callback.onComplete(nombresEmpresas);
        });
    }

    //ELIMINAR
    //empresa
    public void eliminarEmpresaPorNombre(String nombreEmpresa, final FirestoreCallback<Boolean> callback) {
        CollectionReference empresaRef = db.collection("Empresa");

        // Primero, buscamos el documento con el nombre especificado
        empresaRef.whereEqualTo("Nombre", nombreEmpresa).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        // Si encontramos el documento, lo eliminamos
                        DocumentSnapshot document = task.getResult().getDocuments().get(0);
                        document.getReference().delete()
                                .addOnSuccessListener(aVoid -> {
                                    System.out.println("Empresa eliminada con éxito: " + nombreEmpresa);
                                    callback.onComplete(true);
                                })
                                .addOnFailureListener(e -> {
                                    System.err.println("Error eliminando empresa: " + e.getMessage());
                                    callback.onComplete(false);
                                });
                    } else {
                        System.err.println("No se encontró la empresa con nombre: " + nombreEmpresa);
                        callback.onComplete(false);
                    }
                })
                .addOnFailureListener(e -> {
                    System.err.println("Error buscando la empresa: " + e.getMessage());
                    callback.onComplete(false);
                });
    }
    //gasto
    public void eliminarGastoPorID(String IdGasto, final FirestoreCallback<Boolean> callback) {
        CollectionReference empresaRef = db.collection("Gasto");

        // Primero, buscamos el documento con el nombre especificado
        empresaRef.whereEqualTo("ID", IdGasto).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        // Si encontramos el documento, lo eliminamos
                        DocumentSnapshot document = task.getResult().getDocuments().get(0);
                        document.getReference().delete()
                                .addOnSuccessListener(aVoid -> {
                                    System.out.println("Gasto eliminada con éxito: " + IdGasto);
                                    callback.onComplete(true);
                                })
                                .addOnFailureListener(e -> {
                                    System.err.println("Error eliminando gasto: " + e.getMessage());
                                    callback.onComplete(false);
                                });
                    } else {
                        System.err.println("No se encontró el gasto con id: " + IdGasto);
                        callback.onComplete(false);
                    }
                })
                .addOnFailureListener(e -> {
                    System.err.println("Error buscando el gasto: " + e.getMessage());
                    callback.onComplete(false);
                });
    }

    //ACTUALIZAR
    //empresa
    public void actualizarEmpresa(Empresa empresa, final FirestoreCallback<Boolean> callback) {
        ref = db.collection("Empresa");

        // Busca el documento que corresponde al nombre original de la empresa
        ref.whereEqualTo("Nombre", empresa.getNombre()).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        // Si encontramos el documento, actualizamos sus datos
                        DocumentSnapshot document = task.getResult().getDocuments().get(0);
                        document.getReference().set(empresa.toMap())
                                .addOnSuccessListener(aVoid -> {
                                    System.out.println("Empresa actualizada con éxito: " + empresa.getNombre());
                                    callback.onComplete(true);
                                })
                                .addOnFailureListener(e -> {
                                    System.err.println("Error actualizando empresa: " + e.getMessage());
                                    callback.onComplete(false);
                                });
                    } else {
                        System.err.println("No se encontró la empresa para actualizar: " + empresa.getNombre());
                        callback.onComplete(false);
                    }
                })
                .addOnFailureListener(e -> {
                    System.err.println("Error buscando la empresa: " + e.getMessage());
                    callback.onComplete(false);
                });
    }
    //gasto
    public void actualizarGasto(Gasto gasto, final FirestoreCallback<Boolean> callback) {
        ref = db.collection("Gasto");

        // Busca el documento que corresponde al nombre original de la empresa
        ref.whereEqualTo("ID", gasto.getID()).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        // Si encontramos el documento, actualizamos sus datos
                        DocumentSnapshot document = task.getResult().getDocuments().get(0);
                        document.getReference().set(gasto.toMap())
                                .addOnSuccessListener(aVoid -> {
                                    System.out.println("Gasto actualizada con éxito: " + gasto.getID());
                                    callback.onComplete(true);
                                })
                                .addOnFailureListener(e -> {
                                    System.err.println("Error actualizando gasto: " + e.getMessage());
                                    callback.onComplete(false);
                                });
                    } else {
                        System.err.println("No se encontró la gasto para actualizar: " + gasto.getID());
                        callback.onComplete(false);
                    }
                })
                .addOnFailureListener(e -> {
                    System.err.println("Error buscando el gasto: " + e.getMessage());
                    callback.onComplete(false);
                });
    }

    //Crear cuenta
    public void registrarUsuario(String email, String password, final FirestoreCallback<Boolean> callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Registro exitoso
                        callback.onComplete(true);
                    } else {
                        // Si falla el registro, maneja el error específico
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthWeakPasswordException e) {
                            System.err.println("Error: Contraseña débil. " + e.getReason());
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            System.err.println("Error: Correo electrónico inválido. " + e.getMessage());
                        } catch (FirebaseAuthUserCollisionException e) {
                            System.err.println("Error: El correo ya está en uso. " + e.getMessage());
                        } catch (Exception e) {
                            System.err.println("Error al registrar usuario: " + e.getMessage());
                        }
                        callback.onComplete(false);
                    }
                });
    }


    // Método para iniciar sesión
    public void iniciarSesion(String email, String password, final FirestoreCallback<Boolean> callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso
                        callback.onComplete(true);
                    } else {
                        // Si falla el inicio de sesión
                        callback.onComplete(false);
                    }
                })
                .addOnFailureListener(e -> {
                    // Maneja la excepción y llama al callback con 'false'
                    System.err.println("Error al iniciar sesión: " + e.getMessage());
                    callback.onComplete(false);
                });
    }


}
