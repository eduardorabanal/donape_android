package com.edurabroj.donape.shared.entidades;

import java.util.ArrayList;
import java.util.List;

public class Donacion {
    private int id;
    private double cantidad;
    private String articulo;
    private String fecha;
    private Estado estado;
    private List<Estado> estados;
    private String titulo;

    public Donacion() {
        estados = new ArrayList<>();
    }

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

    public Estado getEstado() {
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

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
}
