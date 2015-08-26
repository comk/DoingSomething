package com.mayhub.doingsomething.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.mayhub.doingsomething.R;
import com.mayhub.doingsomething.db.DaoMaster;
import com.mayhub.doingsomething.db.DaoSession;
import com.mayhub.doingsomething.db.TimeSlotDao;
import com.mayhub.doingsomething.entity.MessageEvent;
import com.mayhub.doingsomething.ui.adapter.TimeSlotListAdapter;
import com.mayhub.doingsomething.ui.base.ActivityBaseNoTitle;

import de.greenrobot.event.EventBus;

public class ActivityTimeSlotList extends ActivityBaseNoTitle implements View.OnClickListener{

    ListView listView;

    Toolbar toolbar;

    View floatAction;

    Cursor cursor;

    SQLiteDatabase db;

    DaoMaster daoMaster;

    DaoSession daoSession;

    TimeSlotDao timeSlotDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        setContentView(R.layout.activity_timeslot_list);

        listView = (ListView) findViewById(R.id.timeslot_list_iv_listView);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        floatAction = findViewById(R.id.timeslot_list_iv_add_timeslot);

        initToolbar();

        initContentView();

        initDao();

    }

    private void initDao() {
        DaoMaster.TimeSlotOpenHelper helper = new DaoMaster.TimeSlotOpenHelper(this,"timeslot-db",null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db,1001);
        daoSession = daoMaster.newSession();
        timeSlotDao = daoSession.getTimeSlotDao();

        String textColumn = TimeSlotDao.Properties.Text.columnName;
        String orderBy = textColumn + " COLLATE LOCALIZED ASC";
        cursor = db.query(timeSlotDao.getTablename(), timeSlotDao.getAllColumns(), null, null, null, null, orderBy);
        String[] from = { textColumn, TimeSlotDao.Properties.Text.columnName };
        int[] to = { android.R.id.text1, android.R.id.text2 };
        listView.setAdapter(new TimeSlotListAdapter(getApplicationContext(),cursor,false));
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if(cursor != null) {
            cursor.close();
        }
        super.onDestroy();
    }

    private void initToolbar(){
        toolbar.setTitle("碎片列表");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

    }

    public void onEvent(MessageEvent messageEvent){
        if(messageEvent.getEventType() == MessageEvent.EventType.TYPE_TIME_SLOT_ADD) {
            Log.e("onEvent","receiver new message");
            cursor.requery();
        }
    }

    private void initContentView(){
        floatAction.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.timeslot_list_iv_add_timeslot:
                startActivity(new Intent(getApplicationContext(),ActivityAddTimeSlot.class));
                break;
        }
    }
}
