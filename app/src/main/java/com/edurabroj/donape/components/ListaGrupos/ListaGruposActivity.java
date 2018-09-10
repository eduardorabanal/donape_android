package com.edurabroj.donape.components.ListaGrupos;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;

import com.edurabroj.donape.R;
import com.edurabroj.donape.shared.entidades.Necesidad;
import com.edurabroj.donape.shared.preferences.IPreferences;
import com.edurabroj.donape.shared.preferences.Preferences;

import java.util.List;

import static com.edurabroj.donape.shared.utils.GuiUtils.showMsg;

public class ListaGruposActivity extends AppCompatActivity implements ListaGruposContract.View{
    SwipeRefreshLayout refreshLayout;
    RecyclerView rvList;
    ListaGruposAdapter adapter;

    ListaGruposContract.Presenter presenter;
    IPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        preferences = new Preferences(this);
        presenter = new ListaGruposPresenter(this, new ListaGruposInteractor(preferences));

        refreshLayout = findViewById(R.id.refresh);
        rvList = findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.layout_fall_down));
        adapter = new ListaGruposAdapter(this);
        rvList.setAdapter(adapter);

        presenter.onCreate();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               presenter.onRefreshLista();
            }
        });
    }

    @Override
    public void mostrarProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void ocultarProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void llenarLista(List<Necesidad> list) {
        adapter.setDataset(list);
        rvList.scheduleLayoutAnimation();
    }

    @Override
    public void mostrarErrorRed() {
        showMsg(this,"Error de red");
    }

    @Override
    public void mostrarErrorServidor() {
        showMsg(this,"Error de servidor");
    }
}
