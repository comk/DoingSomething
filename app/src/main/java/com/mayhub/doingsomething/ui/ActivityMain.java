package com.mayhub.doingsomething.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mayhub.doingsomething.R;
import com.mayhub.doingsomething.ui.base.ActivityBaseNoTitle;

public class ActivityMain extends ActivityBaseNoTitle {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        initToolbar();

    }

    private void initToolbar(){
        toolbar.setTitle("时间的碎片");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityTimeSlotList.class));
            }
        });
        toolbar.setBackgroundColor(Color.BLUE);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

    }

}
