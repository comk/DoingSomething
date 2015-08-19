package com.mayhub.doingsomething.entity;

import java.util.ArrayList;

/**
 * Created by daihai on 2015/8/19.
 */
public class Result {
    private String resultMessage;
    private int resultCode;
    private ArrayList<Object> listData;
    private Object data;

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public ArrayList<Object> getListData() {
        return listData;
    }

    public void setListData(ArrayList<Object> listData) {
        this.listData = listData;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
