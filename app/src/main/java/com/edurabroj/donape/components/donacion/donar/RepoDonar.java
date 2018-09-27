package com.edurabroj.donape.components.donacion.donar;

import com.edurabroj.donape.shared.entidades.Donacion;

import io.reactivex.Observable;

public interface RepoDonar {
    Observable<Donacion> saveDonacionData(int necesidadId, float cantidad);
}
