package com.edurabroj.donape.components.donacion.donar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.edurabroj.donape.R;
import com.edurabroj.donape.components.publicacion.lista.ListaPublicacionActivity;

public class GraciasActivity extends AppCompatActivity {
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gracias);

        btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GraciasActivity.this, ListaPublicacionActivity.class));
                finish();
            }
        });
    }
}
