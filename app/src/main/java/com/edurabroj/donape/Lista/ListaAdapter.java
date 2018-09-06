package com.edurabroj.donape.Lista;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.edurabroj.donape.R;
import com.edurabroj.donape.Detalle.DetalleActivity;
import com.edurabroj.donape.entidades.Necesidad;

import java.util.ArrayList;
import java.util.List;

import static com.edurabroj.donape.data.ExtrasData.EXTRA_NECESIDAD_ID;
import static com.edurabroj.donape.utils.GuiUtils.loadImage;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.VH> {
    private List<Necesidad> dataset;
    private View.OnClickListener clickListener;
    private Context context;

    public ListaAdapter(Context context) {
        this.dataset = new ArrayList<>();
        this.context = context;
    }

    public void setDataset(List<Necesidad> dataset) {
        this.dataset = dataset;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solicitud,parent,false);
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
        ImageView ivImg;
        TextView tvTitulo, tvDescripcion;
        Button btnDetalle;

        private VH(View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.ivImg);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            btnDetalle = itemView.findViewById(R.id.btnDetalle);
        }

        public void setData(final Necesidad necesidad) {
//            if(necesidad.getImages().size()>0){
                loadImage(context,"https://cdn.shopify.com/s/files/1/2394/4001/products/811595_811596-1_1024x.png?v=1523295436",ivImg);
//            }
            tvTitulo.setText(necesidad.getTitulo());
            tvDescripcion.setText(necesidad.getDescripcion());
            btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(
                        new Intent(context, DetalleActivity.class)
                            .putExtra(EXTRA_NECESIDAD_ID, necesidad.getId())
                    );
                }
            });
        }
    }

    public void setOnClickListener(View.OnClickListener clickListener){
        this.clickListener = clickListener;
    }
}