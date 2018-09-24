package com.edurabroj.donape.components.publicacion.lista;

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
import com.edurabroj.donape.components.publicacion.detalle.DetallePublicacionActivity;
import com.edurabroj.donape.shared.entidades.Publicacion;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_PUBLICACION_ID;
import static com.edurabroj.donape.shared.utils.GuiUtils.loadImage;

public class AdapterListaPublicacion extends RecyclerView.Adapter<AdapterListaPublicacion.VH> {
    private List<Publicacion> dataset;
    private Context context;

    AdapterListaPublicacion(Context context) {
        this.dataset = new ArrayList<>();
        this.context = context;
    }

    public void addItem(Publicacion publicacion){
        this.dataset.add(publicacion);
        notifyItemInserted(dataset.size()-1);
    }

    public void clear(){
        this.dataset.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_publicacion,parent,false);
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
        @BindView(R.id.ivImg) ImageView ivImg;
        @BindView(R.id.tvTitulo) TextView tvTitulo;
        @BindView(R.id.tvDescripcion) TextView tvDescripcion;
        @BindView(R.id.btnDetalle) Button btnDetalle;

        private VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(final Publicacion publicacion) {
            if(publicacion.getImagenes().size()>0){
                loadImage(context,publicacion.getImagenes().get(0).getUrl(),ivImg);
            }
            tvTitulo.setText(publicacion.getTitulo());
            tvDescripcion.setText(publicacion.getDescripcion());
            btnDetalle.setOnClickListener(v ->
                context.startActivity(
                    new Intent(context, DetallePublicacionActivity.class)
                        .putExtra(EXTRA_PUBLICACION_ID, publicacion.getId()+"")
                )
            );
        }
    }
}