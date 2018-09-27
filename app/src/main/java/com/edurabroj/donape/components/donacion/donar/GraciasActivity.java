package com.edurabroj.donape.components.donacion.donar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.edurabroj.donape.R;
import com.edurabroj.donape.components.publicacion.lista.ListaPublicacionActivity;

import butterknife.BindView;

public class GraciasActivity extends AppCompatActivity {
    @BindView(R.id.btnVolver)
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gracias);

        btnVolver.setOnClickListener(v -> {
            startActivity(new Intent(GraciasActivity.this, ListaPublicacionActivity.class));
            finish();
        });
    }
}
