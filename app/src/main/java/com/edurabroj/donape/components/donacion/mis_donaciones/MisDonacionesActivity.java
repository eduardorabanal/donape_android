package com.edurabroj.donape.components.donacion.mis_donaciones;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;

import com.edurabroj.donape.R;
import com.edurabroj.donape.root.DonapeApplication;
import com.edurabroj.donape.shared.entidades.Donacion;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MisDonacionesActivity extends AppCompatActivity implements MisDonaciones.View{
//    private final String TAG = MisDonacionesActivity.class.getName();

    @Inject
    MisDonaciones.Presenter presenter;

    @BindView(R.id.refresh) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rvList) RecyclerView rvList;

    AdapterMisDonaciones adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_donaciones);
        ButterKnife.bind(this);

        ((DonapeApplication) getApplication()).getComponent().inject(this);

        setTitle(R.string.mis_donaciones_title);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.layout_fall_down));
        adapter = new AdapterMisDonaciones();
        rvList.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> presenter.refrescarLista());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.solicitarMisDonaciones();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();
        adapter.clear();
    }

    @Override
    public void mostrarLoading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void ocultarLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void adjuntarDonacion(Donacion donacion) {
        rvList.scheduleLayoutAnimation();
        adapter.addItem(donacion);
    }

    @Override
    public void limpiarLista() {
        adapter.clear();
    }

    @Override
    public void mostrarErrorServidor() {
        Snackbar.make(rvList,getString(R.string.error_load_data), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.action_refresh_after_load_error), v -> presenter.refrescarLista())
                .show();
    }
}
