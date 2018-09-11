package com.edurabroj.donape.components.donacion.donar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edurabroj.donape.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_NECESIDAD_ARTICULO;
import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_NECESIDAD_ID;
import static com.edurabroj.donape.shared.utils.GuiUtils.showMsg;

public class DonarActivity extends AppCompatActivity implements DonarContract.View {
    Bundle extras;
    String necesidadIdString;
    String necesidadArticulo;

    @BindView(R.id.etCantidad) EditText etCantidad;
    @BindView(R.id.tvArticulo) TextView tvArticulo;
    @BindView(R.id.btnDonar) Button btnDonar;

    DonarContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);
        ButterKnife.bind(this);

        presenter = new DonarPresenter(this);

        extras = getIntent().getExtras();
        if(extras!=null && extras.getString(EXTRA_NECESIDAD_ID)!=null){
            necesidadIdString = extras.getString(EXTRA_NECESIDAD_ID);
            necesidadArticulo = extras.getString(EXTRA_NECESIDAD_ARTICULO);

            tvArticulo.setText(necesidadArticulo);
        }
    }

    @OnClick(R.id.btnDonar) void btnDonarClick() {
        float cantidad = Float.parseFloat(etCantidad.getText().toString());
        int necesidadId = Integer.parseInt(necesidadIdString);
        presenter.guardarDonacion(cantidad,necesidadId);
    }

    @Override
    public void mostrarDonacionCorrecta() {
        showMsg(DonarActivity.this,"Guardado correctamente");
        startActivity(new Intent(DonarActivity.this,GraciasActivity.class));
        finish();
    }

    @Override
    public void mostrarErrorDonacion() {
        showMsg(DonarActivity.this,"Error al guardar los datos");
    }
}
