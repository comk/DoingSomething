package com.mayhub.doingsomething.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by daihai on 2015/8/21.
 */
public class ActivityBaseNoTitle extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
