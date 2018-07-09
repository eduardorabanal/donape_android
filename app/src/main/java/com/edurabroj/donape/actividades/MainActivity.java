package com.edurabroj.donape.actividades;

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

public class MainActivity extends AppCompatActivity {
    RecyclerView rvList;
    SolicitudesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvList = findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SolicitudesAdapter(this);
        rvList.setAdapter(adapter);

        cargarLista();
    }

    private void cargarLista() {
        ServiceProvider.getService().ObtenerPartidos().enqueue(new Callback<List<Solicitud>>() {
            @Override
            public void onResponse(Call<List<Solicitud>> call, Response<List<Solicitud>> response) {
                if(response.isSuccessful()){
                    adapter.setDataset(response.body());
                }else {
                    showMsg(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Solicitud>> call, Throwable t) {
                showMsg(t.getMessage());
            }
        });
    }

    private void showMsg(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }


}
