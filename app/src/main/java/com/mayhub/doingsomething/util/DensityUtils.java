package com.mayhub.doingsomething.util;

import android.content.Context;

/**
 * Created by daihai on 2015/8/27.
 */
public class DensityUtils {
    public static int dp2px(Context context, int dp){
        return (int)(context.getResources().getDisplayMetrics().density * dp);
    }
}
