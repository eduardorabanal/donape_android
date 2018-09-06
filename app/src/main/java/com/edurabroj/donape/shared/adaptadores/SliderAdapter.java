package com.edurabroj.donape.shared.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.edurabroj.donape.R;

import java.util.List;

import static com.edurabroj.donape.shared.utils.GuiUtils.loadImage;

public class SliderAdapter extends PagerAdapter {
    Context context;
    List<String> images;
    LayoutInflater inflater;

    public SliderAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_slider,container,false);

        ImageView img = view.findViewById(R.id.img);
        loadImage(context,images.get(position),img);

        container.addView(view);

        return view;
    }

    public void setImages(List<String> images) {
        this.images = images;
        notifyDataSetChanged();
    }
}
