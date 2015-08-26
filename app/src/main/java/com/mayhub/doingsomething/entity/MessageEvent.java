package com.mayhub.doingsomething.entity;

/**
 * Created by daihai on 2015/8/21.
 */
public class MessageEvent {

    public MessageEvent(EventType eventType){
        this.eventType = eventType;
    }

    public static enum EventType{
        TYPE_TIME_SLOT_ADD
    }

    private EventType eventType;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
