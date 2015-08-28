package com.mayhub.doingsomething.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by daihai on 2015/8/28.
 */
public class ImageViewerAdapter extends PagerAdapter {

    private ArrayList<String> data = new ArrayList<>();

    private Stack<View> cacheViews = new Stack<>();

    public ImageViewerAdapter(){

    }

    public ImageViewerAdapter(ArrayList<String> datas){
        if(datas != null){
            data.addAll(datas);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
