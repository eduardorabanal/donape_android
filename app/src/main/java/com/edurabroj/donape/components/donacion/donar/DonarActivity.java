package com.edurabroj.donape.components.donacion.donar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.edurabroj.donape.R;
import com.edurabroj.donape.components.donacion.mis_donaciones.MisDonacionesActivity;
import com.edurabroj.donape.root.DonapeApplication;
import com.edurabroj.donape.shared.entidades.Necesidad;

import javax.inject.Inject;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_NECESIDAD_ID;
import static com.edurabroj.donape.shared.utils.GuiUtils.getBitmap;
import static com.edurabroj.donape.shared.utils.GuiUtils.showMsg;

public class DonarActivity extends AppCompatActivity implements DonarMVP.View {
    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.tvTitulo)
    TextView tvTitulo;

    @BindView(R.id.etCantidad)
    EditText etCantidad;

    @BindView(R.id.tvArticulo)
    TextView tvArticulo;

    @BindView(R.id.btnDonar)
    CircularProgressButton btnDonar;

    @Inject
    DonarMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);
        ButterKnife.bind(this);

        ((DonapeApplication) getApplication()).getComponent().inject(this);

        btnDonar.setOnClickListener(v -> presenter.onGuardarDonacionClicked());
        refreshLayout.setOnRefreshListener(() -> presenter.onRefreshDetalleDonacion());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach(this);
        presenter.solicitarDetalleNecesidad();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detach();
    }

    @Override
    public int getNecesidadId() {
        Bundle extras = getIntent().getExtras();
        int necesidadId=0;
        if(extras!=null){
            necesidadId = extras.getInt(EXTRA_NECESIDAD_ID);
        }
        return necesidadId;
    }

    @Override
    public float getCantidadADonar() {
        return Float.parseFloat(etCantidad.getText().toString());
    }

    @Override
    public void mostrarDetalleNecesidad(Necesidad necesidad) {
        //todo: poner familia al donar
//        tvTitulo.setText(necesidad.);
        tvArticulo.setText(necesidad.getArticulo());
    }

    @Override
    public void mostrarMensajeAlGuardarDonacionCorrectamente() {
        showMsg(this,"Donación guardada");
        btnDonar.doneLoadingAnimation(R.color.colorAccent, getBitmap(this, R.drawable.ic_check_24dp));
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MisDonacionesActivity.class));
        },2000);
    }

    @Override
    public void mostrarErrorAlObtenerDetalleNecesidad() {
        showMsg(this,"Error al obtener información de la necesidad");
    }

    @Override
    public void mostrarErrorAlGuardarDonacion() {
        btnDonar.revertAnimation();
        showMsg(this,"Error al guardar tu donación");
    }

    @Override
    public void mostrarLoadingDetalleNecesidad() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void ocultarLoadingDetalleNecesidad() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void mostrarLoadingGuardarDonacion() {
        btnDonar.startAnimation();
    }

    @Override
    public void ocultarLoadingGuardarDonacion() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        btnDonar.dispose();
    }
}
