package com.mayhub.doingsomething.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mayhub.doingsomething.R;
import com.mayhub.doingsomething.ui.adapter.ImageViewerAdapter;
import com.mayhub.doingsomething.ui.base.ActivityBaseNoTitle;

import java.util.ArrayList;

/**
 * Created by daihai on 2015/8/28.
 */
public class ActivityImageViewer extends ActivityBaseNoTitle {
    private ViewPager viewPager;

    private Toolbar toolbar;

    private ImageViewerAdapter imageViewerAdapter;

    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        initToolbar();

        initView();

        initValue();
    }

    private void initValue() {
        data = getIntent().getStringArrayListExtra("ImageList");
        Log.e("ImageList size = " ,""+data.size());
        viewPager.setAdapter(imageViewerAdapter = new ImageViewerAdapter(data));

        viewPager.setCurrentItem(getIntent().getIntExtra("showIndex",0));

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initToolbar(){
        toolbar.setTitle("查看图片");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

    }



}
