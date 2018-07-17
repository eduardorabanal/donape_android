package com.edurabroj.donape.actividades;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.edurabroj.donape.R;
import com.edurabroj.donape.adaptadores.SolicitudesAdapter;
import com.edurabroj.donape.entidades.Solicitud;
import com.edurabroj.donape.servicio.ServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.edurabroj.donape.data.PreferencesData.TOKEN_KEY;
import static com.edurabroj.donape.data.PreferencesData.TOKEN_PREV;
import static com.edurabroj.donape.utils.GuiUtils.showMsg;
import static com.edurabroj.donape.utils.PreferencesUtils.getStringPreference;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout refreshLayout;
    RecyclerView rvList;
    SolicitudesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = findViewById(R.id.refresh);
        rvList = findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SolicitudesAdapter(this);
        rvList.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarLista();
            }
        });

        cargarLista();
    }

    private void cargarLista() {
        refreshLayout.setRefreshing(true);
        String token = getStringPreference(this,TOKEN_KEY);
        ServiceProvider.getService().ObtenerSolicitudes(TOKEN_PREV +token).enqueue(new Callback<List<Solicitud>>() {
            @Override
            public void onResponse(Call<List<Solicitud>> call, Response<List<Solicitud>> response) {
                refreshLayout.setRefreshing(false);
                if(response.isSuccessful()){
                    adapter.setDataset(response.body());
                }else {
                    showMsg(MainActivity.this,response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Solicitud>> call, Throwable t) {
                refreshLayout.setRefreshing(false);
                showMsg(MainActivity.this,t.getMessage());
            }
        });
    }
}
