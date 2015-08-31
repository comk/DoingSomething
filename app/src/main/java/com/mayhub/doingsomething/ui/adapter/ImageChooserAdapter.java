package com.mayhub.doingsomething.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.mayhub.doingsomething.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by daihai on 2015/8/28.
 */
public class ImageChooserAdapter extends RecyclerView.Adapter<ImageChooserAdapter.ImageChooserViewHolder> {

    private ArrayList<String> data = new ArrayList<>();

    private static DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().
            bitmapConfig(Bitmap.Config.RGB_565).
            considerExifParams(true).decodingOptions(new BitmapFactory.Options()).
            build();

    private ArrayList<String> selectedItems = new ArrayList<>();

    public ImageChooserAdapter(){

    }

    public ImageChooserAdapter(ArrayList<String> datas){
        if(datas != null){
            data.addAll(datas);
        }
    }

    public void resetData(ArrayList<String> datas){
        if(datas != null){
            data.clear();
            data.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public ArrayList<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(ArrayList<String> selectedItems1){
        selectedItems.addAll(selectedItems1);
    }

    @Override
    public ImageChooserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageChooserViewHolder imageChooserViewHolder = new ImageChooserViewHolder(ViewGroup.inflate(parent.getContext(),R.layout.activity_image_chooser_item,null));
        imageChooserViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.getTag() != null && buttonView.getTag() instanceof Integer){
                    String item = data.get((Integer) buttonView.getTag());
                    if(isChecked && !selectedItems.contains(item)) {
                        selectedItems.add(item);
                    }else if(selectedItems.contains(item)){
                        selectedItems.remove(item);
                    }
                }
            }
        });
        return imageChooserViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageChooserViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(data.get(position),holder.imageView,displayImageOptions);
        holder.checkBox.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ImageChooserViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;

        public CheckBox checkBox;

        public ImageChooserViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.chooser_item_image);
            checkBox = (CheckBox) itemView.findViewById(R.id.chooser_item_checkbox);
        }
    }

}
