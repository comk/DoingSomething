package com.mayhub.doingsomething.entity;


import android.graphics.Color;
import android.text.TextUtils;

import com.mayhub.doingsomething.R;


/**
 * Created by daihai on 2015/8/20.
 */
public class TimeSlot {

    public static final int MAX_LEVEL = 12;

    public static final int[][] SLOT_TYPE = new int[][]{
            {//喜
                    0,
                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8,
                    9,
                    10,
                    11,
                    12,
                    13,
                    14,
                    15,
                    16,
                    17,
                    18,
                    19
            },
            {//怒
                    20,
                    21,
                    22,
                    23,
                    24,
                    25,
                    26,
                    27,
                    28,
                    29,
                    30,
                    31,
                    32,
                    33,
                    34,
                    35,
                    36,
                    37,
                    38,
                    39
            },
            {//哀
                    40,
                    41,
                    42,
                    43,
                    44,
                    45,
                    46,
                    47,
                    48,
                    49,
                    50,
                    51,
                    52,
                    53,
                    54,
                    55,
                    56,
                    57,
                    58,
                    59
            },
            {//乐
                    60,
                    61,
                    62,
                    63,
                    64,
                    65,
                    66,
                    67,
                    68,
                    69,
                    70,
                    71,
                    72,
                    73,
                    74,
                    75,
                    76,
                    77,
                    78,
                    79
            },
            {//无状态
                    80,
                    81,
                    82,
                    83,
                    84,
                    85,
                    86,
                    87,
                    88,
                    89,
                    80,
                    91,
                    92,
                    93,
                    94,
                    95,
                    96,
                    97,
                    98,
                    99
            }
    };

    public TimeSlot(){

    }

    public TimeSlot(Long id, long date, String userId, int slotType, String text,
                    long lastModifyTime, String referenceObject, String locationLatLng,String locationString,
                    String weather, String imageUrl, String audioUrl, String videoUrl, int level) {
        this.date = date;
        this.userId = userId;
        this.slotType = slotType;
        this.text = text;
        this.lastModifyTime = lastModifyTime;
        this.referenceObject = referenceObject;
        this.locationLatLng = locationLatLng;
        this.locationString = locationString;
        this.weather = weather;
        this.imageUrl = imageUrl;
        this.audioUrl = audioUrl;
        this.videoUrl = videoUrl;
        this.level = level;
    }

    private Long id;
    /**
     * 发生的时间
     */
    private long date;
    /**
     *
     */
    private String userId;
    /**
     * 类型
     */
    private int slotType = -1;
    /**
     * 级别 共12级
     */
    private int level = -1;
    /**
     * 文本内容
     */
    private String text;
    /**
     * 最后修改时间
     */
    private long lastModifyTime;
    /**
     * 相关的对象（人或者物）
     */
    private String referenceObject;
    /**
     * 发生地点(经纬度)
     */
    private String locationLatLng;
    /**
     * 发生的地点
     */
    private String locationString;
    /**
     * 发生的天气情况
     */
    private String weather;

    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 音频地址
     */
    private String audioUrl;
    /**
     * 视频地址
     */
    private String videoUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSlotType() {
        return slotType;
    }

    public void setSlotType(int slotType) {
        this.slotType = slotType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getReferenceObject() {
        return referenceObject;
    }

    public void setReferenceObject(String referenceObject) {
        this.referenceObject = referenceObject;
    }

    public String getLocationString() {
        return locationString;
    }

    public void setLocationString(String locationString) {
        this.locationString = locationString;
    }

    public String getLocationLatLng() {
        return locationLatLng;
    }

    public void setLocationLatLng(String locationLatLng) {
        this.locationLatLng = locationLatLng;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 验证信息是否完整
     * @return
     */
    public boolean isTimeSlotCompleted(){
        if(date == 0 || TextUtils.isEmpty(userId) || TextUtils.isEmpty(text) || slotType == -1 ||
                TextUtils.isEmpty(referenceObject) || TextUtils.isEmpty(locationString) ||
                TextUtils.isEmpty(locationLatLng) || TextUtils.isEmpty(weather) || level == -1){
            return false;
        }
        return true;
    }

    /**
     * 好的用绿色表示，坏的用红色表示
     * @return
     */
    public int getLevelColorBySlotType(){
        switch (slotType){
            case 0:
            case 1:
            case 2:
                return getLevelColor(true);
            case 3:
            case 4:
                return getLevelColor(false);
            default:
                return Color.rgb(0,0,255);
        }
    }

    /**
     *
     * @param isNiceThing true 代表好的事物
     * @return
     */
    public int getLevelColor(boolean isNiceThing){
        if(isNiceThing){
            return Color.rgb(0,(int)(((float)level/MAX_LEVEL)*255),255);
        }else{
            return Color.rgb((int)(((float)level/MAX_LEVEL)*255),0,255);
        }
    }

    /**
     * 获取对应的图片资源ID
     * @return
     */
    public int getImageResIDBySlotType(){
        switch (slotType){
            case 0://无状态
                return R.drawable.slot_type_normal;
            case 1://喜
                return R.drawable.slot_type_enjoy;
            case 2://乐
                return R.drawable.slot_type_happy;
            case 3://哀
                return R.drawable.slot_type_sad;
            case 4://怒
                return R.drawable.slot_type_angry;
            default:
                return R.drawable.slot_type_normal;
        }
    }

}
