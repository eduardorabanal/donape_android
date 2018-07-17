package com.edurabroj.donape.actividades;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edurabroj.donape.R;
import com.edurabroj.donape.adaptadores.SliderAdapter;
import com.edurabroj.donape.entidades.Solicitud;
import com.edurabroj.donape.servicio.IService;
import com.edurabroj.donape.servicio.ServiceProvider;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.edurabroj.donape.data.ExtrasData.EXTRA_NECESIDAD_ID;
import static com.edurabroj.donape.data.PreferencesData.TOKEN_KEY;
import static com.edurabroj.donape.data.PreferencesData.TOKEN_PREV;
import static com.edurabroj.donape.utils.GuiUtils.showMsg;
import static com.edurabroj.donape.utils.PreferencesUtils.getStringPreference;

public class NecesidadDetailsActivity extends AppCompatActivity {
    IService service;
    Bundle extras;
    SliderAdapter sliderAdapter;

    SwipeRefreshLayout refresh;
    ViewGroup dataLayout;
    ViewPager slider;
    FloatingActionButton btnShare;

    TextView tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_necesidad_details);

        refresh = findViewById(R.id.refresh);
        dataLayout=findViewById(R.id.dataLayout);
        slider = findViewById(R.id.viewPager);
        sliderAdapter = new SliderAdapter(this,new ArrayList<String>());
        slider.setAdapter(sliderAdapter);

        btnShare = findViewById(R.id.btnShare);

        tvDescripcion = findViewById(R.id.tvDescripcion);

        service = ServiceProvider.getService();

        extras = getIntent().getExtras();
        if(extras!=null && extras.getString(EXTRA_NECESIDAD_ID)!=null){
            cargarDatos();
        }

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarDatos();
            }
        });
    }

    private void cargarDatos() {
        setIsLoading(true);

        String id = extras.getString(EXTRA_NECESIDAD_ID);
        String token = getStringPreference(this,TOKEN_KEY);

        service.ObtenerSolicitudById(TOKEN_PREV+token,id).enqueue(new Callback<Solicitud>() {
            @Override
            public void onResponse(Call<Solicitud> call, Response<Solicitud> response) {
                setIsLoading(false);
                if(response.isSuccessful()){
                    Solicitud solicitud = response.body();
                    assert solicitud != null;

                    setTitle(solicitud.getTitle());
                    tvDescripcion.setText(solicitud.getDescription());
                    sliderAdapter.setImages(solicitud.getImages());
                }else {
                    showMsg(NecesidadDetailsActivity.this,response.message());
                }
            }

            @Override
            public void onFailure(Call<Solicitud> call, Throwable t) {
                setIsLoading(false);
                showMsg(NecesidadDetailsActivity.this,t.getMessage());
            }
        });
    }

    private void setIsLoading(boolean isLoading) {
        refresh.setRefreshing(isLoading);
        if(isLoading){
            dataLayout.setVisibility(View.GONE);
            btnShare.setVisibility(View.GONE);
        }else{
            dataLayout.setVisibility(View.VISIBLE);
            btnShare.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
