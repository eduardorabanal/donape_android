package com.edurabroj.donape.utils;

import android.content.Context;
import android.widget.Toast;

public class GuiUtils {
    public static void showMsg(Context context, String message) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
