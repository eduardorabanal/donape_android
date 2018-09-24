package com.edurabroj.donape.shared.entidades;

import java.util.ArrayList;
import java.util.List;

public class Publicacion {
    private int id;
    private String titulo;
    private String descripcion;
    private List<Imagen> imagenes;

    public Publicacion() {
        imagenes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }
}
