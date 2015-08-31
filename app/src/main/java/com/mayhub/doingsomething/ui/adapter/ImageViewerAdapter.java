package com.mayhub.doingsomething.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by daihai on 2015/8/28.
 */
public class ImageViewerAdapter extends PagerAdapter {

    private ArrayList<String> data = new ArrayList<>();
    /**
     * enable pagerAdapter's view cache feature
     */
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
        //check whether the cache stack is empty, if it is empty,we need to create from layout,otherwise we use the cache one;
        if(cacheViews.isEmpty()){

        }else{

        }
        return super.instantiateItem(container, position);
    }

    private void bindView(){

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //save view cache into stack ,when instantiate item check stack whether has cache first
        cacheViews.push((View) object);
        container.removeView((View) object);
    }

    /**
     * ViewHolder
     */
    public static class ImageViewerHolder{
        /**
         * The Image
         */
        public ImageView iv_item;
        /**
         * delete image option
         */
        public ImageView iv_delete;
        /**
         * save image option
         */
        public ImageView iv_save;
        /**
         * comment image option
         */
        public ImageView iv_comment;
        /**
         * like image option
         */
        public ImageView iv_like;
    }

}
