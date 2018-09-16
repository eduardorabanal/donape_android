package com.edurabroj.donape.shared.entidades;

import java.util.List;

public class Donacion {
    private int id;
    private double cantidad;
    private String articulo;
    private String fecha;
    private String estado;
    private List<Estado> estados;
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getArticulo() {
        return articulo;
    }


    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public List<Estado> getEstados() {
        return estados;
    }


    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
}
