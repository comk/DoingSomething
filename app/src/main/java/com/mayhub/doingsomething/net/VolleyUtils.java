package com.mayhub.doingsomething.net;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by daihai on 2015/8/20.
 */
public class VolleyUtils {

    private static RequestQueue requestQueue;

    private VolleyUtils(){

    }

    public static RequestQueue getRequestQueueInstance(Context context) {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }
}
