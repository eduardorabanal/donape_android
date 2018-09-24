package com.edurabroj.donape.components.publicacion.lista;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;

import com.edurabroj.donape.R;
import com.edurabroj.donape.components.donacion.mis_donaciones.MisDonacionesActivity;
import com.edurabroj.donape.components.login.LoginActivity;
import com.edurabroj.donape.root.DonapeApplication;
import com.edurabroj.donape.shared.entidades.Publicacion;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.edurabroj.donape.shared.utils.GuiUtils.showMsg;

public class ListaPublicacionActivity extends AppCompatActivity implements ListaPublicacion.View{
    @Inject
    ListaPublicacion.Presenter presenter;

    @BindView(R.id.refresh) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rvList) RecyclerView rvList;

    AdapterListaPublicacion adapter;

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
                presenter.onMisDonacionesClick();
                return true;
            case R.id.btnCerrarSesion:
                presenter.onLogoutClick();
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

        ((DonapeApplication) getApplication()).getComponent().inject(this);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.layout_fall_down));
        adapter = new AdapterListaPublicacion(this);
        rvList.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> presenter.onRefrescarLista());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.solicitarPublicaciones();
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
    public void limpiarLista() {
        adapter.clear();
    }

    @Override
    public void adjuntarPublicacion(Publicacion publicacion) {
        rvList.scheduleLayoutAnimation();
        adapter.addItem(publicacion);
    }

    @Override
    public void mostrarErrorRed() {
        Snackbar.make(rvList,getString(R.string.error_load_data), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.action_refresh_after_load_error), v -> presenter.onRetryLoadClick())
                .show();
    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void goToMisDonaciones() {
        startActivity(new Intent(this, MisDonacionesActivity.class));
    }
}
