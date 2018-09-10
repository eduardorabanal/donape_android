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

import com.edurabroj.donape.PublicacionesQuery;
import com.edurabroj.donape.R;
import com.edurabroj.donape.components.publicacion.detalle.DetallePublicacionActivity;

import java.util.ArrayList;
import java.util.List;

import static com.edurabroj.donape.shared.data.ExtrasData.EXTRA_PUBLICACION_ID;
import static com.edurabroj.donape.shared.utils.GuiUtils.loadImage;

public class ListaPublicacionAdapter extends RecyclerView.Adapter<ListaPublicacionAdapter.VH> {
    private List<PublicacionesQuery.Publicacione> dataset;
    private View.OnClickListener clickListener;
    private Context context;

    public ListaPublicacionAdapter(Context context) {
        this.dataset = new ArrayList<>();
        this.context = context;
    }

    public void setDataset(List<PublicacionesQuery.Publicacione> dataset) {
        this.dataset = dataset;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_publicacion,parent,false);
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

        public void setData(final PublicacionesQuery.Publicacione publicacion) {
            if(publicacion.imagenes().size()>0){
                loadImage(context,publicacion.imagenes().get(0).url(),ivImg);
            }
            tvTitulo.setText(publicacion.titulo());
            tvDescripcion.setText(publicacion.descripcion()+"");
            btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(
                        new Intent(context, DetallePublicacionActivity.class)
                            .putExtra(EXTRA_PUBLICACION_ID, publicacion.id()+"")
                    );
                }
            });
        }
    }

    public void setOnClickListener(View.OnClickListener clickListener){
        this.clickListener = clickListener;
    }
}