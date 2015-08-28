package com.mayhub.doingsomething.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mayhub.doingsomething.R;
import com.mayhub.doingsomething.ui.base.ActivityBaseNoTitle;

/**
 * Created by daihai on 2015/8/28.
 */
public class ActivityImageViewer extends ActivityBaseNoTitle {
    private ViewPager viewPager;

    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_image_viewer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        initToolbar();

        initView();

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initToolbar(){
        toolbar.setTitle("添加碎片");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

    }

}
