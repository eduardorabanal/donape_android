package com.edurabroj.donape.components.donacion.mis_donaciones;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;

import com.edurabroj.donape.R;
import com.edurabroj.donape.shared.entidades.Donacion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.edurabroj.donape.shared.utils.GuiUtils.showMsg;

public class MisDonacionesActivity extends AppCompatActivity implements MisDonaciones.View{
    MisDonaciones.Presenter presenter;

    @BindView(R.id.refresh) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rvList) RecyclerView rvList;

    MisDonacionesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_donaciones);
        ButterKnife.bind(this);

        presenter = new MisDonacionesPresenter(this,this);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.layout_fall_down));
        adapter = new MisDonacionesAdapter();
        rvList.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrescarLista();
            }
        });

        onCreate();
    }

    @Override
    public void onCreate() {
        presenter.solicitarDonaciones();
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
    public void mostrarDonaciones(List<Donacion> donaciones) {
        adapter.setDataset(donaciones);
    }

    @Override
    public void mostrarErrorRed() {
        showMsg(this,"Error de red");
    }

    @Override
    public void mostrarErrorServidor() {
        showMsg(this,"Error de servidor, inténtalo de nuevo.");
    }

    @Override
    public void refrescarLista() {
        presenter.solicitarDonaciones();
    }
}
