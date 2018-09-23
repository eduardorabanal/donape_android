package com.edurabroj.donape.components.donacion.mis_donaciones;

import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;

import com.edurabroj.donape.R;
import com.edurabroj.donape.root.DonapeApplication;
import com.edurabroj.donape.shared.entidades.Donacion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MisDonacionesActivity extends AppCompatActivity implements MisDonaciones.View{
//    private final String TAG = MisDonacionesActivity.class.getName();

    @BindString(R.string.error_load_data)
    String errorLoadData;
    @BindString(R.string.action_refresh_after_load_error)
    String retryLoad;

    @Inject
    MisDonaciones.Presenter presenter;

    @BindView(R.id.refresh) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rvList) RecyclerView rvList;

    MisDonacionesAdapter adapter;

    List<Donacion> dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_donaciones);
        ButterKnife.bind(this);

        ((DonapeApplication) getApplication()).getComponent().inject(this);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.layout_fall_down));
        adapter = new MisDonacionesAdapter(dataset);
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
        dataset.clear();
        adapter.notifyDataSetChanged();
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
        dataset.add(donacion);
        adapter.notifyItemInserted(dataset.size()-1);
    }

    @Override
    public void limpiarLista() {
        dataset.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void mostrarErrorServidor() {
        Snackbar.make(rvList,errorLoadData, BaseTransientBottomBar.LENGTH_INDEFINITE)
                .setAction(retryLoad, v -> presenter.refrescarLista())
                .show();
    }
}
