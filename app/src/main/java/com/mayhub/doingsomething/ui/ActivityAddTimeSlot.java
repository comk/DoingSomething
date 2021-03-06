package com.mayhub.doingsomething.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.mayhub.doingsomething.ui.adapter.SlotImageAdapter;
import com.mayhub.doingsomething.ui.adapter.SlotLevelAdapter;
import com.mayhub.doingsomething.ui.adapter.SlotTypeAdapter;
import com.mayhub.doingsomething.ui.base.ActivityBaseNoTitle;
import com.mayhub.doingsomething.util.ImageUtils;
import com.mayhub.doingsomething.util.InputMethodTool;
import com.mayhub.doingsomething.util.ToastUtils;


import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by daihai on 2015/8/20.
 */
public class ActivityAddTimeSlot extends ActivityBaseNoTitle implements View.OnClickListener,AMapLocationListener,AMapLocalWeatherListener,SlotImageAdapter.onTtemClickListener {

    private String imagePath;

    LocationManagerProxy mLocationManagerProxy;

    private Toolbar toolbar;

    private EditText editText_content;

    private RecyclerView recyclerView_image;

    private TextView textView_location;

    private TimeSlot timeSlot = new TimeSlot();

    private Button btn_submit;

    private TextView textView_weather;

    private Spinner spinnerType;

    private Spinner spinnerLevel;

    SQLiteDatabase db;

    DaoMaster daoMaster;

    DaoSession daoSession;

    TimeSlotDao timeSlotDao;

    private ArrayList<Integer> slotType = new ArrayList<>();
    private SlotTypeAdapter slotTypeAdapter;

    private SlotLevelAdapter slotLevelAdapter;

    private SlotImageAdapter slotImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timeslot_add);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        initToolbar();

        initSlotType();

        initContentView();

        initDao();

        initLocation();

    }

    private void initSlotType() {
        slotType.add(R.drawable.slot_type_normal);
        slotType.add(R.drawable.slot_type_enjoy);
        slotType.add(R.drawable.slot_type_happy);
        slotType.add(R.drawable.slot_type_sad);
        slotType.add(R.drawable.slot_type_angry);
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
        recyclerView_image.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        DefaultItemAnimator dia = new DefaultItemAnimator();
        dia.setAddDuration(400);
        dia.setMoveDuration(400);
        dia.setRemoveDuration(400);
        dia.setChangeDuration(400);
        recyclerView_image.setItemAnimator(dia);

        recyclerView_image.setAdapter(slotImageAdapter = new SlotImageAdapter());

        slotImageAdapter.setOnTtemClickListener(this);

        textView_location = (TextView) findViewById(R.id.timeslot_add_tv_select_location);

        textView_weather = (TextView) findViewById(R.id.timeslot_add_tv_weather);

        btn_submit = (Button) findViewById(R.id.timeslot_add_btn_submit);

        spinnerLevel = (Spinner) findViewById(R.id.timeslot_sp_level);

        spinnerType = (Spinner) findViewById(R.id.timeslot_sp_type);

        btn_submit.setOnClickListener(this);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                slotLevelAdapter.resetSlotLevel(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerType.setAdapter(slotTypeAdapter = new SlotTypeAdapter(slotType));

        spinnerLevel.setAdapter(slotLevelAdapter = new SlotLevelAdapter());


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
        timeSlot.setSlotType(spinnerType.getSelectedItemPosition());
        timeSlot.setText(editText_content.getText().toString().trim());
        timeSlot.setLastModifyTime(System.currentTimeMillis());
        timeSlot.setLevel(spinnerLevel.getSelectedItemPosition());
        timeSlot.setLocationString("未知地址");
        timeSlot.setLocationLatLng("未知地址");
        timeSlot.setWeather("天气");
        timeSlot.setReferenceObject("无对象");
        timeSlot.setImageUrl(slotImageAdapter.getListString());
        if(timeSlot.isTimeSlotCompleted()) {
            timeSlotDao.insert(timeSlot);
            EventBus.getDefault().post(new MessageEvent(MessageEvent.EventType.TYPE_TIME_SLOT_ADD));
            finish();
        }else{
            ToastUtils.showShortToast(getApplicationContext(),"请将信息填写完整");
        }

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation!=null&&amapLocation.getAMapException().getErrorCode() == 0) {
            timeSlot.setLocationLatLng(amapLocation.getLatitude() + "," + amapLocation.getLongitude());
            timeSlot.setLocationString(amapLocation.getCountry() + "," + amapLocation.getProvince() + "," + amapLocation.getCity() + "," + amapLocation.getDistrict() + "," + amapLocation.getStreet());
            textView_location.setText(timeSlot.getLocationString());
        }else{
            textView_location.setText("Failed");
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
        textView_weather.setText(timeSlot.getWeather());
    }

    @Override
    public void onWeatherForecaseSearched(AMapLocalWeatherForecast aMapLocalWeatherForecast) {

    }

    @Override
    public void onImageClick(ImageView imageView, int position, String url) {
        Intent intent = new Intent(getApplicationContext(),ActivityImageViewer.class);
        intent.putStringArrayListExtra("ImageList",slotImageAdapter.getData());
        intent.putExtra("showIndex",position);
        startActivity(intent);
        Log.e("onImageClick ","----------------------------");
    }

    @Override
    public void onDeleteClick(ImageView imageView, int position, String url) {
        slotImageAdapter.deleteImage(position);
    }

    @Override
    public void onAddClick(int position) {
//        try {
//            ImageUtils.chooseImageFromPhoto(this);
////            imagePath = ImageUtils.chooseImageFromCamera(this);
//        }catch (Exception ex){
//        }

        Intent intent = new Intent(getApplicationContext(), ActivityImageChooser.class);
        intent.putStringArrayListExtra("selectedItems",slotImageAdapter.getData());
        startActivityForResult(intent, ActivityImageChooser.REQUEST_GET_IMAGES);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK
                && (requestCode == ImageUtils.ChooserType.REQUEST_PICK_PICTURE || requestCode == ImageUtils.ChooserType.REQUEST_CAPTURE_PICTURE)) {
                if(ImageUtils.ChooserType.REQUEST_PICK_PICTURE == requestCode) {
                    imagePath = data.getData().toString();
                }
            slotImageAdapter.addImage(imagePath);
        }

        if(resultCode == RESULT_OK && requestCode == ActivityImageChooser.REQUEST_GET_IMAGES){
            slotImageAdapter.addImageList(data.getStringArrayListExtra("ImageList"));
        }

    }

}
