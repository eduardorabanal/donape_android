package com.edurabroj.donape.components.donacion.donar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edurabroj.donape.R;

import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_NECESIDAD_ARTICULO;
import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_NECESIDAD_ID;
import static com.edurabroj.donape.shared.utils.GuiUtils.showMsg;

public class DonarActivity extends AppCompatActivity implements DonarContract.View {
    Bundle extras;
    String necesidadIdString;
    String necesidadArticulo;

    EditText etCantidad;
    TextView tvArticulo;
    Button btnDonar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);

        final DonarContract.Presenter presenter = new DonarPresenter(this);

        etCantidad = findViewById(R.id.etCantidad);
        btnDonar = findViewById(R.id.btnDonar);
        tvArticulo = findViewById(R.id.tvArticulo);

        extras = getIntent().getExtras();
        if(extras!=null && extras.getString(EXTRA_NECESIDAD_ID)!=null){
            necesidadIdString = extras.getString(EXTRA_NECESIDAD_ID);
            necesidadArticulo = extras.getString(EXTRA_NECESIDAD_ARTICULO);

            tvArticulo.setText(necesidadArticulo);

            Log.i(EXTRA_NECESIDAD_ID,necesidadIdString);
        }

        btnDonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float cantidad = Float.parseFloat(etCantidad.getText().toString());
                int necesidadId = Integer.parseInt(necesidadIdString);
                presenter.guardarDonacion(cantidad,necesidadId);
            }
        });
    }

    @Override
    public void mostrarDonacionCorrecta() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(DonarActivity.this,GraciasActivity.class));
                finish();
            }
        });
    }

    @Override
    public void mostrarErrorDonacion() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showMsg(DonarActivity.this,"Error al guardar los datos");
            }
        });
    }
}
