package com.edurabroj.donape.components.publicacion.detalle;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.edurabroj.donape.PublicacionQuery;
import com.edurabroj.donape.R;
import com.edurabroj.donape.shared.adaptadores.SliderAdapter;
import com.edurabroj.donape.shared.preferences.IPreferences;
import com.edurabroj.donape.shared.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_PUBLICACION_ID;
import static com.edurabroj.donape.shared.utils.GuiUtils.showMsg;

public class DetallePublicacionActivity extends AppCompatActivity implements DetallePublicacionContract.View {
    Bundle extras;
    SliderAdapter sliderAdapter;

    SwipeRefreshLayout refresh;
    ViewPager slider;
    FloatingActionButton btnShare;

    TextView tvDescripcion;

    DetallePublicacionContract.Presenter presenter;
    IPreferences preferences;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion_details);

        preferences = new Preferences(this);
        presenter = new DetallePublicacionPresenter(this, new DetallePublicacionInteractor(preferences));

        refresh = findViewById(R.id.refresh);
        btnShare = findViewById(R.id.btnShare);
        tvDescripcion = findViewById(R.id.tvDescripcion);

        slider = findViewById(R.id.viewPager);
        sliderAdapter = new SliderAdapter(this,new ArrayList<String>());
        slider.setAdapter(sliderAdapter);

        extras = getIntent().getExtras();
        if(extras!=null && extras.getString(EXTRA_PUBLICACION_ID)!=null){
            id = extras.getString(EXTRA_PUBLICACION_ID);
            Log.i("PUBLICACION_ID",id);
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(false);
                btnShare.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void mostrarDetalle(final PublicacionQuery.Publicacion publicacion) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setTitle(publicacion.titulo());
                tvDescripcion.setText(publicacion.descripcion());
                List<String> imgUrls = new ArrayList<>();
                for (PublicacionQuery.Imagene imagen : publicacion.imagenes()){
                    imgUrls.add(imagen.url());
                }
                sliderAdapter.setImages(imgUrls);
            }
        });
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
