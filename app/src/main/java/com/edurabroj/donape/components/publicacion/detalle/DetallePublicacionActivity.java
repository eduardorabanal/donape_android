package com.edurabroj.donape.components.publicacion.detalle;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.edurabroj.donape.R;
import com.edurabroj.donape.root.DonapeApplication;
import com.edurabroj.donape.shared.adaptadores.SliderAdapter;
import com.edurabroj.donape.shared.entidades.Imagen;
import com.edurabroj.donape.shared.entidades.Publicacion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_PUBLICACION_ID;
import static com.edurabroj.donape.shared.utils.GuiUtils.showMsg;

public class DetallePublicacionActivity extends AppCompatActivity implements DetallePublicacion.View {
    @Inject
    DetallePublicacion.Presenter presenter;

    SliderAdapter sliderAdapter;
    AdapterListaNecesidad necesidadAdapter;

    @BindView(R.id.refresh) SwipeRefreshLayout refresh;
    @BindView(R.id.tvDescripcion) TextView tvDescripcion;
    @BindView(R.id.viewPager) ViewPager slider;
    @BindView(R.id.rvNecesidades) RecyclerView rvNecesidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion_details);
        ButterKnife.bind(this);

        ((DonapeApplication) getApplication()).getComponent().inject(this);

        sliderAdapter = new SliderAdapter(this);
        slider.setAdapter(sliderAdapter);

        rvNecesidades.setLayoutManager(new LinearLayoutManager(this));
        rvNecesidades.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.layout_fall_down));
        necesidadAdapter = new AdapterListaNecesidad(this);
        rvNecesidades.setAdapter(necesidadAdapter);

        refresh.setOnRefreshListener(() -> presenter.onRefresh());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.solicitarDetalle();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();
        sliderAdapter.clear();
        necesidadAdapter.clear();
    }

    @Override
    public void mostrarLoading() {
        refresh.setRefreshing(true);
    }

    @Override
    public void ocultarLoading() {
        refresh.setRefreshing(false);
    }

    @Override
    public void mostrarDetalle(final Publicacion publicacion) {
        setTitle(publicacion.getTitulo());
        tvDescripcion.setText(publicacion.getDescripcion());
        List<String> imgUrls = new ArrayList<>();
            for (Imagen imagen : publicacion.getImagenes()){
                imgUrls.add(imagen.getUrl());
            }
        sliderAdapter.setImages(imgUrls);
        necesidadAdapter.setDataset(publicacion.getNecesidades());
    }

    @Override
    public void mostrarError() {
        Snackbar.make(refresh,getString(R.string.error_load_data), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.action_refresh_after_load_error), v -> presenter.onRetryLoadClick())
                .show();
    }

    @Override
    public String getPublicacionId() {
        Bundle extras = getIntent().getExtras();
        String id=null;
        if(extras!=null){
            id = extras.getString(EXTRA_PUBLICACION_ID);
        }
        return id;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
