package com.edurabroj.donape.components.publicacion.detalle;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.edurabroj.donape.R;
import com.edurabroj.donape.components.donacion.donar.DonarActivity;
import com.edurabroj.donape.shared.entidades.Necesidad;

import java.util.ArrayList;
import java.util.List;

import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_NECESIDAD_ARTICULO;
import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_NECESIDAD_ID;

public class AdapterListaNecesidad extends RecyclerView.Adapter<AdapterListaNecesidad.VH> {
    private List<Necesidad> dataset;
    private View.OnClickListener clickListener;
    private Context context;

    public AdapterListaNecesidad(Context context) {
        this.dataset = new ArrayList<>();
        this.context = context;
    }

    public void setDataset(List<Necesidad> dataset) {
        this.dataset = dataset;
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.dataset.clear();
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_necesidad,parent,false);
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
        TextView tvDescripcion;
        Button btnDonar;

        private VH(View itemView) {
            super(itemView);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            btnDonar = itemView.findViewById(R.id.btnDonar);
        }

        public void setData(final Necesidad necesidad) {
            tvDescripcion.setText(necesidad.getCantidad()+" " +necesidad.getArticulo());
            btnDonar.setOnClickListener(v ->
                context.startActivity(
                    new Intent(context, DonarActivity.class)
                        .putExtra(EXTRA_NECESIDAD_ID, necesidad.getId())
                        .putExtra(EXTRA_NECESIDAD_ARTICULO, necesidad.getArticulo())
                    )
            );
        }
    }

    public void setOnClickListener(View.OnClickListener clickListener){
        this.clickListener = clickListener;
    }
}