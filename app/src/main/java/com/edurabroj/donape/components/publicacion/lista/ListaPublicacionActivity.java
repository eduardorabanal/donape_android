package com.edurabroj.donape.components.publicacion.lista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;

import com.edurabroj.donape.PublicacionesQuery;
import com.edurabroj.donape.R;
import com.edurabroj.donape.components.donacion.mis_donaciones.MisDonacionesActivity;
import com.edurabroj.donape.shared.preferences.IPreferences;
import com.edurabroj.donape.shared.preferences.Preferences;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.edurabroj.donape.shared.utils.GuiUtils.showMsg;

public class ListaPublicacionActivity extends AppCompatActivity implements ListaPublicacionContract.View{
    ListaPublicacionContract.Presenter presenter;

    @BindView(R.id.refresh) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rvList) RecyclerView rvList;

    ListaPublicacionAdapter adapter;
    IPreferences preferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lista_publicaciones_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnMisDonaciones:
                startActivity(new Intent(this, MisDonacionesActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_publicacion);
        ButterKnife.bind(this);

        preferences = new Preferences(this);
        presenter = new ListaPublicacionPresenter(this, new ListaPublicacionInteractor(preferences));

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.layout_fall_down));
        adapter = new ListaPublicacionAdapter(this);
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
    public void llenarLista(final List<PublicacionesQuery.Publicacione> list) {
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
