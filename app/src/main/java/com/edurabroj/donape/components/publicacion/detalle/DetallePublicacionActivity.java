package com.edurabroj.donape.components.publicacion.detalle;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.edurabroj.donape.PublicacionQuery;
import com.edurabroj.donape.R;
import com.edurabroj.donape.shared.adaptadores.SliderAdapter;
import com.edurabroj.donape.shared.preferences.IPreferences;
import com.edurabroj.donape.shared.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_PUBLICACION_ID;
import static com.edurabroj.donape.shared.utils.GuiUtils.showMsg;

public class DetallePublicacionActivity extends AppCompatActivity implements DetallePublicacionContract.View {
    DetallePublicacionContract.Presenter presenter;

    IPreferences preferences;
    String id;

    Bundle extras;
    SliderAdapter sliderAdapter;
    ListaNecesidadAdapter necesidadAdapter;

    @BindView(R.id.refresh) SwipeRefreshLayout refresh;
    @BindView(R.id.tvDescripcion) TextView tvDescripcion;
    @BindView(R.id.viewPager) ViewPager slider;
    @BindView(R.id.rvNecesidades) RecyclerView rvNecesidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion_details);
        ButterKnife.bind(this);

        presenter = new DetallePublicacionPresenter(this, new DetallePublicacionInteractor(preferences));
        preferences = new Preferences(this);

        sliderAdapter = new SliderAdapter(this,new ArrayList<String>());
        slider.setAdapter(sliderAdapter);

        rvNecesidades.setLayoutManager(new LinearLayoutManager(this));
        rvNecesidades.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.layout_fall_down));
        necesidadAdapter = new ListaNecesidadAdapter(this);
        rvNecesidades.setAdapter(necesidadAdapter);

        extras = getIntent().getExtras();
        if(extras!=null && extras.getString(EXTRA_PUBLICACION_ID)!=null){
            id = extras.getString(EXTRA_PUBLICACION_ID);
        }

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh(id);
            }
        });

        presenter.onCreate(id);
    }

    @Override
    public void mostrarProgress() {
        refresh.setRefreshing(true);
    }

    @Override
    public void ocultarProgress() {
        refresh.setRefreshing(false);
    }

    @Override
    public void mostrarDetalle(final PublicacionQuery.Publicacion publicacion) {
        setTitle(publicacion.titulo());
        tvDescripcion.setText(publicacion.descripcion());
        List<String> imgUrls = new ArrayList<>();
            for (PublicacionQuery.Imagene imagen : publicacion.imagenes()){
                imgUrls.add(imagen.url());
            }
        sliderAdapter.setImages(imgUrls);
        necesidadAdapter.setDataset(publicacion.necesidades());
    }

    @Override
    public void mostrarErrorServidor() {
        showMsg(this,"Error de servidor");
    }

    @Override
    public void mostrarErrorRed() {
        showMsg(this,"Error de red");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
