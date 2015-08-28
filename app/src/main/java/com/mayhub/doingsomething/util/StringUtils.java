package com.mayhub.doingsomething.util;

import android.text.TextUtils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daihai on 2015/8/27.
 */
public class StringUtils {
    public static String getStringFromArrayList(ArrayList<String> list){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
        return gson.toJson(list, listType);
    }

    public static ArrayList<String> getArrayListFromString(String listString){
        if(!TextUtils.isEmpty(listString)){
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<String>>(){}.getType();
            try {
                return gson.fromJson(listString, listType);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
}
