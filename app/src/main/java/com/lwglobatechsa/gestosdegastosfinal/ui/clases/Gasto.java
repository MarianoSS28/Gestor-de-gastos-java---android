package com.lwglobatechsa.gestosdegastosfinal.ui.clases;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class Gasto {
    private String ID;
    private String NombreEmpresa;
    private String Descripcion;
    private double Monto;

    public Gasto() {}

    public Gasto(String id, String nombreEmpresa, String nescripcion, double monto) {
        this.ID = id;
        this.NombreEmpresa = nombreEmpresa;
        this.Descripcion = nescripcion;
        this.Monto = monto;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> gastoData = new HashMap<>();

        gastoData.put("ID", this.ID);
        gastoData.put("NombreEmpresa", this.NombreEmpresa);
        gastoData.put("Descripcion", this.Descripcion);
        gastoData.put("Monto", this.Monto);

        return gastoData;
    }
    public static Gasto toObject(DocumentSnapshot document){
        String id = document.getId();
        String nombreEmpresa = document.getString("NombreEmpresa");
        String descripcion = document.getString("Descripcion");
        Double monto = document.getDouble("Monto");

        return new Gasto(id,nombreEmpresa,descripcion,monto);
    }
    public String getID() {return ID;}
    public void setID(String ID) {this.ID = ID;}
    public String getNombreEmpresa() {return NombreEmpresa;}
    public void setNombreEmpresa(String nombreEmpresa) {NombreEmpresa = nombreEmpresa;}
    public String getNescripcion() {return Descripcion;}
    public void setNescripcion(String nescripcion) {Descripcion = nescripcion;}
    public double getMonto() {return Monto;}
    public void setMonto(double monto) {Monto = monto;}
}
