package com.mayhub.doingsomething.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mayhub.doingsomething.R;
import com.mayhub.doingsomething.util.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by daihai on 2015/8/27.
 */
public class SlotImageAdapter extends RecyclerView.Adapter<SlotImageAdapter.SlotImageViewHolder> implements View.OnClickListener{

    private onTtemClickListener onTtemClickListener;

    private ArrayList<String> data = new ArrayList<>();

    public SlotImageAdapter(){
        data.add("");
    }

    public SlotImageAdapter(ArrayList<String> datas){
        data.add("");
        if(datas != null){
            data.addAll(data.size() - 1,datas);
        }
    }

    public ArrayList<String> getData(){
        return data;
    }

    public String getListString(){
       return StringUtils.getStringFromArrayList(new ArrayList<String>(data.subList(0,data.size() - 1)));
    }

    public SlotImageAdapter.onTtemClickListener getOnTtemClickListener() {
        return onTtemClickListener;
    }

    public void setOnTtemClickListener(SlotImageAdapter.onTtemClickListener onTtemClickListener) {
        this.onTtemClickListener = onTtemClickListener;
    }

    public void addImageList(ArrayList<String> datas){
        data.addAll(data.size()-1, datas);
        notifyItemRangeInserted(data.size()-1-datas.size(),datas.size());
    }

    public void addImage(String url){
        data.add(data.size()-1, url);
        notifyItemInserted(data.size() - 2);
    }

    public void deleteImage(int pos){
        data.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos,data.size() - pos - 1);
    }

    @Override
    public SlotImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SlotImageViewHolder slotImageViewHolder = new SlotImageViewHolder(ViewGroup.inflate(parent.getContext(), R.layout.activity_timeslot_add_image_recycler_item,null));
        slotImageViewHolder.imageViewClose.setOnClickListener(this);
        slotImageViewHolder.imageView.setOnClickListener(this);
        return slotImageViewHolder;
    }

    @Override
    public void onBindViewHolder(SlotImageViewHolder holder, int position) {
        if(position == getItemCount() - 1){
            holder.imageView.setImageResource(R.drawable.add_slot_add_image);
            holder.imageViewClose.setVisibility(View.INVISIBLE);
            holder.imageView.setTag(-1);
        }else {
            ImageLoader.getInstance().displayImage(data.get(position),holder.imageView);
            holder.imageView.setTag(position);
            holder.imageViewClose.setTag(position);
            holder.imageViewClose.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class SlotImageViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public ImageView imageViewClose;
        public SlotImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
            imageViewClose = (ImageView) itemView.findViewById(R.id.item_delete);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getTag() == null){
            return;
        }

        int pos = (int) v.getTag();
        switch (v.getId()){
            case R.id.item_image:
                if(pos ==  - 1) {
                    onTtemClickListener.onAddClick(pos);
                }else{
                    onTtemClickListener.onImageClick((ImageView) v, pos, data.get(pos));
                }
                break;
            case R.id.item_delete:
                onTtemClickListener.onDeleteClick((ImageView) v,pos,data.get(pos));
                break;
        }
    }

    public interface onTtemClickListener{
        public void onImageClick(ImageView imageView, int position, String url);
        public void onDeleteClick(ImageView imageView,int position, String url);
        public void onAddClick(int position);
    }

}
