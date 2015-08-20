package com.mayhub.doingsomething.entity;

import java.util.Date;

/**
 * Created by daihai on 2015/8/20.
 */
public class TimeSlot {


    public TimeSlot(Long id, Date date, String userId, int slotType, String text,
                    Date lastModifyTime, String referenceObject, String locationLatLng,
                    String weather, String imageUrl, String audioUrl, String videoUrl) {
        this.date = date;
        this.userId = userId;
        this.slotType = slotType;
        this.text = text;
        this.lastModifyTime = lastModifyTime;
        this.referenceObject = referenceObject;
        this.locationLatLng = locationLatLng;
        this.weather = weather;
        this.imageUrl = imageUrl;
        this.audioUrl = audioUrl;
        this.videoUrl = videoUrl;
    }

    private Long id;
    /**
     * 发生的时间
     */
    private java.util.Date date;
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
    private java.util.Date lastModifyTime;
    /**
     * 相关的对象（人或者物）
     */
    private String referenceObject;
    /**
     * 发生地点(经纬度)
     */
    private String locationLatLng;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getReferenceObject() {
        return referenceObject;
    }

    public void setReferenceObject(String referenceObject) {
        this.referenceObject = referenceObject;
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
