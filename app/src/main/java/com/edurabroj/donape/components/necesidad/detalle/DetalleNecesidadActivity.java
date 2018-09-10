package com.edurabroj.donape.components.necesidad.detalle;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.edurabroj.donape.R;
import com.edurabroj.donape.shared.adaptadores.SliderAdapter;
import com.edurabroj.donape.shared.entidades.Necesidad;
import com.edurabroj.donape.shared.preferences.IPreferences;
import com.edurabroj.donape.shared.preferences.Preferences;

import java.util.ArrayList;

import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_NECESIDAD_ID;
import static com.edurabroj.donape.shared.utils.GuiUtils.showMsg;

public class DetalleNecesidadActivity extends AppCompatActivity implements DetalleNecesidadContract.View {
    Bundle extras;
    SliderAdapter sliderAdapter;

    SwipeRefreshLayout refresh;
    ViewPager slider;
    FloatingActionButton btnShare;

    TextView tvDescripcion;

    DetalleNecesidadContract.Presenter presenter;
    IPreferences preferences;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_necesidad_details);

        preferences = new Preferences(this);
        presenter = new DetalleNecesidadPresenter(this, new DetalleNecesidadInteractor(preferences));

        refresh = findViewById(R.id.refresh);
        btnShare = findViewById(R.id.btnShare);
        tvDescripcion = findViewById(R.id.tvDescripcion);

        slider = findViewById(R.id.viewPager);
        sliderAdapter = new SliderAdapter(this,new ArrayList<String>());
        slider.setAdapter(sliderAdapter);

        extras = getIntent().getExtras();
        if(extras!=null && extras.getString(EXTRA_NECESIDAD_ID)!=null){
            id = extras.getString(EXTRA_NECESIDAD_ID);
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
        btnShare.setVisibility(View.GONE);
    }

    @Override
    public void ocultarProgress() {
        refresh.setRefreshing(false);
        btnShare.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrarDetalle(Necesidad necesidad) {
        setTitle(necesidad.getArticulo());
        tvDescripcion.setText(necesidad.getCantidad()+"");
        sliderAdapter.setImages(new ArrayList<String>(){{
            add("https://cdn.shopify.com/s/files/1/2394/4001/products/811595_811596-1_1024x.png?v=1523295436");
        }});
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
