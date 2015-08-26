package com.mayhub.doingsomething.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by daihai on 2015/8/21.
 */
public class ActivityBaseNoTitle extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }
}
