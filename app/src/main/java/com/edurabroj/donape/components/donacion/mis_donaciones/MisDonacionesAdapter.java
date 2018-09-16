package com.edurabroj.donape.components.donacion.mis_donaciones;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edurabroj.donape.R;
import com.edurabroj.donape.shared.entidades.Donacion;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MisDonacionesAdapter extends RecyclerView.Adapter<MisDonacionesAdapter.VH> {
    private List<Donacion> dataset;
    private View.OnClickListener clickListener;

    public MisDonacionesAdapter() {
        this.dataset = new ArrayList<>();
    }

    public void setDataset(List<Donacion> dataset) {
        this.dataset = dataset;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donacion,parent,false);
        view.setOnClickListener(clickListener);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setData(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class VH extends RecyclerView.ViewHolder {
//        @BindView(R.id.tvCantidad) TextView tvCantidad;
//        @BindView(R.id.tvArticulo) TextView tvArticulo;
        @BindView(R.id.tvTitulo) TextView tvTitulo;
//        @BindView(R.id.tvFecha)TextView tvFecha;
//        @BindView(R.id.tvEstado) TextView tvEstado;

        private VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(final Donacion donacion) {
//            tvCantidad.setText(String.valueOf(donacion.getCantidad()));
//            tvArticulo.setText(donacion.getArticulo());
            tvTitulo.setText(donacion.getCantidad() + " " + donacion.getArticulo() + " para " + donacion.getTitulo());
//            tvFecha.setText(donacion.getFecha());
//            tvEstado.setText(donacion.getEstado());
        }
    }

    public void setOnClickListener(View.OnClickListener clickListener){
        this.clickListener = clickListener;
    }
}