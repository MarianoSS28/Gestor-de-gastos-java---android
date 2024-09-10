package com.lwglobatechsa.gestosdegastosfinal.ui.clases;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class Empresa {
    private String ID;
    private String Nombre;
    private String Categoria;
    private String Direccion;
    private String Telefono;

    public Empresa() {}

    public Empresa(String nombre, String categoria, String direccion, String telefono) {
        this.Nombre = nombre;
        this.Categoria = categoria;
        this.Direccion = direccion;
        this.Telefono = telefono;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> empresaData = new HashMap<>();

        empresaData.put("Nombre", this.Nombre);
        empresaData.put("Categoria", this.Categoria);
        empresaData.put("Direccion", this.Direccion);
        empresaData.put("Telefono", this.Telefono);

        return empresaData;
    }
    public static Empresa toObject(DocumentSnapshot document){
        String id = document.getId();
        String nombre = document.getString("Nombre");
        String categoria = document.getString("Categoria");
        String direccion = document.getString("Direccion");
        String telefono = document.getString("Telefono");

        return new Empresa(nombre,categoria,direccion,telefono);
    }
    public String getID() {return ID;}
    public void setID(String ID) {this.ID = ID;}
    public String getNombre() {return Nombre;}
    public void setNombre(String nombre) {Nombre = nombre;}
    public String getCategoria() {return Categoria;}
    public void setCategoria(String categoria) {Categoria = categoria;}
    public String getDireccion() {return Direccion;}
    public void setDireccion(String direccion) {Direccion = direccion;}
    public String getTelefono() {return Telefono;}
    public void setTelefono(String telefono) {Telefono = telefono;}
}
