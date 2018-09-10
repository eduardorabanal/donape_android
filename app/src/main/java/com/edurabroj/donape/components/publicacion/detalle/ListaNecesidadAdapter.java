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

import com.edurabroj.donape.PublicacionQuery;
import com.edurabroj.donape.R;

import java.util.ArrayList;
import java.util.List;

import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_NECESIDAD_ID;

public class ListaNecesidadAdapter extends RecyclerView.Adapter<ListaNecesidadAdapter.VH> {
    private List<PublicacionQuery.Necesidade> dataset;
    private View.OnClickListener clickListener;
    private Context context;

    public ListaNecesidadAdapter(Context context) {
        this.dataset = new ArrayList<>();
        this.context = context;
    }

    public void setDataset(List<PublicacionQuery.Necesidade> dataset) {
        this.dataset = dataset;
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

        public void setData(final PublicacionQuery.Necesidade necesidad) {
            tvDescripcion.setText(necesidad.cantidad()+" " +necesidad.articulo());
            btnDonar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(
                        new Intent(context, DetallePublicacionActivity.class)
                            .putExtra(EXTRA_NECESIDAD_ID, necesidad.id())
                    );
                }
            });
        }
    }

    public void setOnClickListener(View.OnClickListener clickListener){
        this.clickListener = clickListener;
    }
}