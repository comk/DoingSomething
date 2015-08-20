package com.mayhub.doingsomething.ui.base;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mayhub.doingsomething.net.VolleyUtils;

/**
 * Created by daihai on 2015/8/19.
 */
public class NetworkBaseActivity extends Activity {

    public static final String TAG = NetworkBaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        VolleyUtils.getRequestQueueInstance(getApplicationContext()).cancelAll(TAG);
    }
}
