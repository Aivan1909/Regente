package com.full.aivan.regente;

/**
 * Created by usuario on 01/12/2017.
 */

public class Comunicados {

    private int idcom;
    private String descripcion;
    private  String fecha;

    public Comunicados() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdcom() {
        return idcom;
    }

    public void setIdcom(int idcomu) {
        this.idcom = idcomu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}