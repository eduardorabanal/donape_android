package com.edurabroj.donape.components.donacion.donar;

import com.edurabroj.donape.shared.entidades.Necesidad;

import io.reactivex.Observable;

public interface RepoNecesidad {
    Observable<Necesidad> getNecesidadData(int necesidadId);
}
