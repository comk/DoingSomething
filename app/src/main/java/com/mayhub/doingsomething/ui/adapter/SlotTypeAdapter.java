package com.mayhub.doingsomething.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by daihai on 2015/8/27.
 */
public class SlotTypeAdapter extends BaseAdapter {

    private ArrayList<Integer> data = new ArrayList<Integer>();

    public SlotTypeAdapter(ArrayList<Integer> datas){
        if(datas != null){
            data.addAll(datas);
        }
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
            convertView = new ImageView(parent.getContext());
        }

        ImageView iv = (ImageView) convertView;
        iv.setImageResource(data.get(position));

        return convertView;
    }
}
