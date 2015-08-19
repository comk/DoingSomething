package com.mayhub.doingsomething.ui.base;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.toolbox.Volley;

/**
 * Created by daihai on 2015/8/19.
 */
public class NetworkBaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Volley.newRequestQueue(this);
    }
}
