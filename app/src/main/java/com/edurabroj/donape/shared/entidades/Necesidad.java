package com.edurabroj.donape.shared.entidades;

import java.util.Date;

public class Necesidad {
    private String id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private int usuarioId;
    private float cantidad;
    private String articulo;
    private String tipoCod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getTipoCod() {
        return tipoCod;
    }

    public void setTipoCod(String tipoCod) {
        this.tipoCod = tipoCod;
    }
}
