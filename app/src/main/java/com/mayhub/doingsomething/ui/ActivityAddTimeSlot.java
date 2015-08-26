package com.mayhub.doingsomething.ui;

import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocalWeatherForecast;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocalWeatherLive;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.mayhub.doingsomething.R;
import com.mayhub.doingsomething.db.DaoMaster;
import com.mayhub.doingsomething.db.DaoSession;
import com.mayhub.doingsomething.db.TimeSlotDao;
import com.mayhub.doingsomething.entity.MessageEvent;
import com.mayhub.doingsomething.entity.TimeSlot;
import com.mayhub.doingsomething.ui.base.ActivityBaseNoTitle;
import com.mayhub.doingsomething.util.InputMethodTool;
import com.mayhub.doingsomething.util.ToastUtils;


import de.greenrobot.event.EventBus;

/**
 * Created by daihai on 2015/8/20.
 */
public class ActivityAddTimeSlot extends ActivityBaseNoTitle implements View.OnClickListener,AMapLocationListener,AMapLocalWeatherListener {

    LocationManagerProxy mLocationManagerProxy;

    private Toolbar toolbar;

    private EditText editText_content;

    private RecyclerView recyclerView_image;

    private TextView textView_location;

    private TimeSlot timeSlot = new TimeSlot();

    private Button btn_submit;

    SQLiteDatabase db;

    DaoMaster daoMaster;

    DaoSession daoSession;

    TimeSlotDao timeSlotDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timeslot_add);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        initToolbar();

        initContentView();

        initDao();

        initLocation();

    }

    /**
     * 初始化定位
     */
	private void initLocation() {
		// 初始化定位，只采用网络定位
		mLocationManagerProxy = LocationManagerProxy.getInstance(this);
		mLocationManagerProxy.setGpsEnable(false);
		// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
		// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用removeUpdates()方法来取消定位请求
		// 在定位结束后，在合适的生命周期调用destroy()方法
		// 其中如果间隔时间为-1，则定位只定一次,
		//在单次定位情况下，定位无论成功与否，都无需调用removeUpdates()方法移除请求，定位sdk内部会移除
		mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, -1, 15, this);
        mLocationManagerProxy.requestWeatherUpdates(
                LocationManagerProxy.WEATHER_TYPE_LIVE, this);
	}

    private void initContentView() {
        InputMethodTool.setupUI(findViewById(R.id.rootView), this);

        editText_content = (EditText) findViewById(R.id.timeslot_add_et_content);

        recyclerView_image = (RecyclerView) findViewById(R.id.timeslot_add_recyclerview_image);

        textView_location = (TextView) findViewById(R.id.timeslot_add_tv_select_location);

        btn_submit = (Button) findViewById(R.id.timeslot_add_btn_submit);

        btn_submit.setOnClickListener(this);
    }

    private void initDao() {
        DaoMaster.TimeSlotOpenHelper helper = new DaoMaster.TimeSlotOpenHelper(this,"timeslot-db",null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db,1001);
        daoSession = daoMaster.newSession();
        timeSlotDao = daoSession.getTimeSlotDao();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.timeslot_add_btn_submit:
                submitTimeSlot();
                break;
        }
    }

    private void submitTimeSlot() {
        timeSlot.setUserId("userid" + System.currentTimeMillis());
        timeSlot.setDate(System.currentTimeMillis());
        timeSlot.setSlotType(0);
        timeSlot.setText(editText_content.getText().toString().trim());
        timeSlot.setLastModifyTime(System.currentTimeMillis());
        timeSlot.setLevel(5);
        timeSlot.setLocationString("未知地址");
        timeSlot.setLocationLatLng("未知地址");
        timeSlot.setWeather("天气");
        timeSlot.setReferenceObject("无对象");
        if(timeSlot.isTimeSlotCompleted()) {
            timeSlotDao.insert(timeSlot);
            EventBus.getDefault().post(new MessageEvent(MessageEvent.EventType.TYPE_TIME_SLOT_ADD));
        }else{
            ToastUtils.showShortToast(getApplicationContext(),"请将信息填写完整");
        }

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation!=null&&amapLocation.getAMapException().getErrorCode() == 0) {
            timeSlot.setLocationLatLng(amapLocation.getLatitude() + "," + amapLocation.getLongitude());
            timeSlot.setLocationString(amapLocation.getCountry() + "," + amapLocation.getProvince() + "," + amapLocation.getCity() + "," + amapLocation.getDistrict() + "," + amapLocation.getStreet());
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onWeatherLiveSearched(AMapLocalWeatherLive aMapLocalWeatherLive) {
        timeSlot.setWeather(aMapLocalWeatherLive.getWeather() + "," + aMapLocalWeatherLive.getTemperature());
    }

    @Override
    public void onWeatherForecaseSearched(AMapLocalWeatherForecast aMapLocalWeatherForecast) {

    }
}
