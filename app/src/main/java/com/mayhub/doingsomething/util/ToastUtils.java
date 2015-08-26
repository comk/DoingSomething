package com.mayhub.doingsomething.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by daihai on 2015/8/26.
 */
public class ToastUtils {
    public static void showShortToast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }
}
