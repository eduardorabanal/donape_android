package com.edurabroj.donape.components.donacion.mis_donaciones;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edurabroj.donape.R;
import com.edurabroj.donape.shared.entidades.Donacion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MisDonacionesAdapter extends RecyclerView.Adapter<MisDonacionesAdapter.VH> {
    private List<Donacion> dataset;

    MisDonacionesAdapter(List<Donacion> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donacion,parent,false);
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
        @BindView(R.id.tvTitulo) TextView tvTitulo;

        private VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(final Donacion donacion) {
            tvTitulo.setText(donacion.getCantidad() + " " + donacion.getArticulo() + " para " + donacion.getTitulo());
        }
    }
}