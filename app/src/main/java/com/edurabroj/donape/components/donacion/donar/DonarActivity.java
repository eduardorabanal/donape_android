package com.edurabroj.donape.components.donacion.donar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.edurabroj.donape.DonacionCreateMutation;
import com.edurabroj.donape.R;
import com.edurabroj.donape.shared.graphql.ClienteApolloProvider;

import javax.annotation.Nonnull;

import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_NECESIDAD_ARTICULO;
import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_NECESIDAD_ID;
import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_PUBLICACION_ID;
import static com.edurabroj.donape.shared.utils.GuiUtils.showMsg;

public class DonarActivity extends AppCompatActivity {
    Bundle extras;
    String necesidadId;
    String necesidadArticulo;

    EditText etCantidad;
    TextView tvArticulo;
    Button btnDonar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);

        etCantidad = findViewById(R.id.etCantidad);
        btnDonar = findViewById(R.id.btnDonar);
        tvArticulo = findViewById(R.id.tvArticulo);

        extras = getIntent().getExtras();
        if(extras!=null && extras.getString(EXTRA_NECESIDAD_ID)!=null){
            necesidadId = extras.getString(EXTRA_NECESIDAD_ID);
            necesidadArticulo = extras.getString(EXTRA_NECESIDAD_ARTICULO);

            tvArticulo.setText(necesidadArticulo);

            Log.i(EXTRA_NECESIDAD_ID,necesidadId);
        }

        btnDonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApolloClient cliente = ClienteApolloProvider.getClient();
                cliente.mutate(DonacionCreateMutation.builder()
                        .cantidad(Float.parseFloat(etCantidad.getText().toString()))
                        .necesidad(Integer.parseInt(necesidadId))
                        .usuario(1)
                        .fecha("2018-05-04")
                    .build()).enqueue(new ApolloCall.Callback<DonacionCreateMutation.Data>() {
                    @Override
                    public void onResponse(@Nonnull Response<DonacionCreateMutation.Data> response) {
                        startActivity(new Intent(DonarActivity.this,GraciasActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(@Nonnull ApolloException e) {
                        showMsg(DonarActivity.this,"Error al guardar los datos");
                    }
                });
            }
        });
    }
}
