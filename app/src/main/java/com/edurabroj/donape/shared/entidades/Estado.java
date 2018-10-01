package com.edurabroj.donape.shared.entidades;

import java.util.ArrayList;
import java.util.List;

public

class Estado{
    private String nombre;
    private String fecha;

    private List<Imagen> imagenes;

    public Estado() {
        imagenes = new ArrayList<>();
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }
}