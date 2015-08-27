package com.mayhub.doingsomething.ui.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mayhub.doingsomething.entity.TimeSlot;
import com.mayhub.doingsomething.util.DensityUtils;

import java.util.ArrayList;

/**
 * Created by daihai on 2015/8/27.
 */
public class SlotLevelAdapter extends BaseAdapter {

    private ArrayList<Integer> data = new ArrayList<>();

    public SlotLevelAdapter(){
        resetSlotLevel(0);
    }

    public void resetSlotLevel(int slotType){
        data.clear();
        for (int i = 0;i <= TimeSlot.MAX_LEVEL;i++){
            data.add( Color.rgb(slotType > 2 ? (int) (((float) i / TimeSlot.MAX_LEVEL) * 255) : 0,
                    slotType <= 2 ? (int) (((float) i / TimeSlot.MAX_LEVEL) * 255) : 0, 255));
            Log.e("slotLevel ",i + ", rgb = " + data.get(i));
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = new TextView(parent.getContext());
            int padding = DensityUtils.dp2px(parent.getContext(), 10);
            convertView.setPadding(padding*4,padding,padding*4,padding);
        }
        convertView.setBackgroundColor(data.get(position));
        return convertView;
    }
}
