package com.edurabroj.donape.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edurabroj.donape.R;
import com.edurabroj.donape.entidades.Solicitud;

import java.util.ArrayList;
import java.util.List;

public class SolicitudesAdapter  extends RecyclerView.Adapter<SolicitudesAdapter.VH> {
    private List<Solicitud> dataset;
    private View.OnClickListener clickListener;
    private Context context;

    public SolicitudesAdapter(Context context) {
        this.dataset = new ArrayList<>();
        this.context = context;
    }

    public void setDataset(List<Solicitud> dataset) {
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

        private VH(View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.ivImg);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }

        public void setData(final Solicitud solicitud) {
            if(solicitud.getImages().size()>0){
                Glide.with(context).load(solicitud.getImages().get(0)).into(ivImg);
            }
            tvTitulo.setText(solicitud.getTitle());
            tvDescripcion.setText(solicitud.getDescription());
        }
    }

    public void setOnClickListener(View.OnClickListener clickListener){
        this.clickListener = clickListener;
    }
}