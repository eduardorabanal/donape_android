package com.edurabroj.donape.utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class GuiUtils {
    public static void showMsg(Context context, String message) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void loadImage(Context context, String imageUrl, ImageView imageView){
        Glide.with(context)
                .load(imageUrl)
                .thumbnail(0.1f)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static Animation getAnimation(Context context, int animId, int duration){
        Animation animacion = AnimationUtils.loadAnimation(context,animId);
        animacion.setDuration(duration);
        return animacion;
    }
}
