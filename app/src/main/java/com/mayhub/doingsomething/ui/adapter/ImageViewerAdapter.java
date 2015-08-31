package com.mayhub.doingsomething.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.mayhub.doingsomething.R;
import com.mayhub.doingsomething.util.AnimationBuilder;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by daihai on 2015/8/28.
 */
public class ImageViewerAdapter extends PagerAdapter implements View.OnClickListener{

    private ArrayList<String> data = new ArrayList<>();
    /**
     * enable pagerAdapter's view cache feature
     */
    private Stack<View> cacheViews = new Stack<>();

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

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
        final View rootView;
        final ImageViewerHolder holder;
        boolean isFromCache = false;
        if(cacheViews.isEmpty()){
            rootView = ViewGroup.inflate(container.getContext(), R.layout.activity_image_viewer_item,null);
            holder = new ImageViewerHolder();
            holder.view_option = rootView.findViewById(R.id.imageviewer_item_option);
            holder.iv_comment = (ImageView) rootView.findViewById(R.id.imageviewer_item_comment_it);
            holder.iv_delete = (ImageView) rootView.findViewById(R.id.imageviewer_item_delete);
            holder.iv_item = (ImageView) rootView.findViewById(R.id.imageviewer_item_image);
            holder.iv_like = (ImageView) rootView.findViewById(R.id.imageviewer_item_like_it);
            holder.iv_save = (ImageView) rootView.findViewById(R.id.imageviewer_item_save);
            holder.view_comment = rootView.findViewById(R.id.imageviewer_item_view_comment);
            holder.view_comment_ok = rootView.findViewById(R.id.imageviewer_item_comment_ok);
            holder.view_comment_cancel = rootView.findViewById(R.id.imageviewer_item_comment_cancel);
            holder.view_delete = rootView.findViewById(R.id.imageviewer_item_delete_view);
            holder.view_delete_cancel = rootView.findViewById(R.id.imageviewer_item_delete_cancel);
            holder.view_delete_ok = rootView.findViewById(R.id.imageviewer_item_delete_cofirm);

            //set listener to views
            holder.iv_item.setOnClickListener(this);
            holder.iv_like.setOnClickListener(this);
            holder.iv_save.setOnClickListener(this);
            holder.iv_delete.setOnClickListener(this);
            holder.iv_comment.setOnClickListener(this);
            holder.view_comment_ok.setOnClickListener(this);
            holder.view_comment_cancel.setOnClickListener(this);
            holder.view_delete_ok.setOnClickListener(this);
            holder.view_delete_cancel.setOnClickListener(this);

            rootView.setTag(holder);
        } else {
            isFromCache = true;
            //cache view need to reset some status ,like view's visibility etc;
            rootView = cacheViews.pop();
            holder = (ImageViewerHolder) rootView.getTag();
        }

        bindView(holder, position, isFromCache);

        return rootView;
    }

    /**
     * method bind image resource and position info into views
     * @param holder ImageViewerHolder
     * @param position int
     */
    private void bindView(ImageViewerHolder holder, int position, boolean isFromCache){
        //if holder come from view cache ,we need to reset it status
        if(isFromCache){
            holder.view_comment.setVisibility(View.INVISIBLE);
            holder.view_delete.setVisibility(View.INVISIBLE);
            holder.view_option.setVisibility(View.INVISIBLE);
        }
        holder.view_delete_ok.setTag(position);
        holder.view_comment_ok.setTag(position);
        holder.iv_like.setTag(position);
        holder.iv_save.setTag(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //save view cache into stack ,when instantiate item check stack whether has cache first
        cacheViews.push((View) object);
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {
        //get the holder here
        final ImageViewerHolder holder;
        if(v.getParent() != null && v.getParent().getParent() != null){
            holder = (ImageViewerHolder) ((View)(v.getParent().getParent())).getTag();
        }else{
            holder = null;
            return;
        }

        switch (v.getId()){
            case R.id.imageviewer_item_comment_it:
                // when click this view we show comment view
                hideThenShowAnimation(holder.view_option,holder.view_comment);
                break;
            case R.id.imageviewer_item_comment_ok:
                // when click this view we hide option view
                hideAnimation(holder.view_comment);
                break;
            case R.id.imageviewer_item_comment_cancel:
                //when click this view we show option view
                hideThenShowAnimation(holder.view_comment,holder.view_option);
                break;
            case R.id.imageviewer_item_delete:
                //when click this view we show delete confirm view
                hideThenShowAnimation(holder.view_option,holder.view_delete);
                break;
            case R.id.imageviewer_item_delete_cancel:
                //when click this view we show option view
                hideThenShowAnimation(holder.view_delete,holder.view_option);
                break;
            case R.id.imageviewer_item_delete_cofirm:
                //when click this view we hide option view
                hideAnimation(holder.view_option);
                break;
            case R.id.imageviewer_item_like_it:
                //when click this view we hide option view
                hideAnimation(holder.view_option);
                break;
            case R.id.imageviewer_item_image:
                //when click this view we show or hide option view
                if(holder.view_option.getVisibility() == View.VISIBLE) {
                    hideAnimation(holder.view_option);
                }else{
                    showAnimation(holder.view_option);
                }
                break;
            case R.id.imageviewer_item_save:
                //when click this view we hide option view
                hideAnimation(holder.view_option);
                break;
        }
    }

    private void showAnimation(final View showView){
        new AnimationBuilder().setAnimateView(showView).
                setAnimationRes(R.anim.overshoot_slide_footer_up).setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        }).startAniamtion(showView.getContext());
    }

    private void hideAnimation(final View hideView){
        new AnimationBuilder().setAnimateView(hideView).
                setAnimationRes(R.anim.anticipate_slide_footer_down).setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hideView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        }).startAniamtion(hideView.getContext());
    }

    private void hideThenShowAnimation(final View hideView,final View showView){
        new AnimationBuilder().setBeforeAnimateView(hideView).setBeforeAnimationRes(R.anim.anticipate_slide_footer_down).setBeforeAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hideView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        }).setAnimateView(showView).setAnimationRes(R.anim.overshoot_slide_footer_up).setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                showView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        }).startAniamtion(hideView.getContext());
    }

    /**
     * ViewHolder
     */
    public static class ImageViewerHolder{
        /**
         * option view
         */
        public View view_option;
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
        /**
         * comment layout view
         */
        public View view_comment;
        /**
         * comment finish btn
         */
        public View view_comment_ok;
        /**
         * comment cancel btn
         */
        public View view_comment_cancel;
        /**
         * delete confirm layout view
          */
        public View view_delete;
        /**
         * sure to delete
         */
        public View view_delete_ok;
        /**
         * cancel delete
         */
        public View view_delete_cancel;

    }

    public interface OnClickListener{
        public void onSaveClicked(String imageUrl);
        public void onLikeClicked(int pos);
        public void onCommentOkBtnClicked(int pos);
        public void onDeleteConfirmClicked(int pos);
    }

}
