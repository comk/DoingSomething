package com.mayhub.doingsomething.entity;


import com.mayhub.doingsomething.util.DateUtils;

/**
 * Created by daihai on 2015/8/20.
 */
public class TimeSlot {


    public TimeSlot(){

    }

    public TimeSlot(Long id, long date, String userId, int slotType, String text,
                    long lastModifyTime, String referenceObject, String locationLatLng,String locationString,
                    String weather, String imageUrl, String audioUrl, String videoUrl) {
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
    private int slotType;
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

}
