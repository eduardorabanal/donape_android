package com.edurabroj.donape.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.edurabroj.donape.R;

public class NecesidadDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_necesidad_details);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
