package com.edurabroj.donape.components.donacion.mis_donaciones;


import com.edurabroj.donape.shared.entidades.Donacion;

import io.reactivex.Observable;

public interface RepoMisDonaciones {
    Observable<Donacion> getMyDonationsData();
}
