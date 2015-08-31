package com.mayhub.doingsomething.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by daihai on 2015/3/12.
 */
public class AnimationBuilder {

    private Animation.AnimationListener animationListener;
    private Animation.AnimationListener beforeAnimationListener;
    private Animation.AnimationListener afterAnimationListener;

    private int animationRes;
    private int afterAnimationRes;
    private int beforeAnimationRes;

    private View animateView;

    private View beforeAnimateView;

    private View afterAnimateView;

    public AnimationBuilder(){

    }

    public void startAniamtion(final Context context){
        if(animateView == null)
            throw new NullPointerException("animateView can not be null");

        final Animation mAnimation = AnimationUtils.loadAnimation(context, animationRes);
        if(mAnimation == null)
            throw new NullPointerException("animation Resource is not corrent");

        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(animationListener != null)
                    animationListener.onAnimationStart(animation);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(animationListener != null)
                    animationListener.onAnimationEnd(animation);
                if(afterAnimateView != null){
                    final Animation afterAnimation = AnimationUtils.loadAnimation(context, afterAnimationRes);
                    if(afterAnimation == null)
                        throw new NullPointerException("before animation Resource is not corrent");


                    afterAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            if(afterAnimationListener != null)
                                afterAnimationListener.onAnimationStart(animation);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if(afterAnimationListener != null)
                                afterAnimationListener.onAnimationEnd(animation);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            if(afterAnimationListener != null)
                                afterAnimationListener.onAnimationRepeat(animation);
                        }
                    });
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if(animationListener != null)
                    animationListener.onAnimationRepeat(animation);
            }
        });

        if(beforeAnimateView != null){
            final Animation beforeAnimation = AnimationUtils.loadAnimation(context, beforeAnimationRes);
            if(beforeAnimation == null)
                throw new NullPointerException("before animation Resource is not corrent");


                beforeAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        if(beforeAnimationListener != null)
                            beforeAnimationListener.onAnimationStart(animation);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if(beforeAnimationListener != null)
                            beforeAnimationListener.onAnimationEnd(animation);
                        animateView.startAnimation(mAnimation);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if(beforeAnimationListener != null)
                        beforeAnimationListener.onAnimationRepeat(animation);
                    }
                });
            beforeAnimateView.startAnimation(beforeAnimation);
        }else{
            animateView.startAnimation(mAnimation);
        }
    }

    public int getAnimationRes() {
        return animationRes;
    }

    public AnimationBuilder setAnimationRes(int animationRes) {
        this.animationRes = animationRes;return this;
    }

    public int getAfterAnimationRes() {
        return afterAnimationRes;
    }

    public AnimationBuilder setAfterAnimationRes(int afterAnimationRes) {
        this.afterAnimationRes = afterAnimationRes;return this;
    }

    public int getBeforeAnimationRes() {
        return beforeAnimationRes;
    }

    public AnimationBuilder setBeforeAnimationRes(int beforeAnimationRes) {
        this.beforeAnimationRes = beforeAnimationRes;return this;
    }

    public Animation.AnimationListener getAnimationListener() {
        return animationListener;
    }

    public AnimationBuilder setAnimationListener(Animation.AnimationListener animationListener) {
        this.animationListener = animationListener;return this;
    }

    public Animation.AnimationListener getBeforeAnimationListener() {
        return beforeAnimationListener;
    }

    public AnimationBuilder setBeforeAnimationListener(Animation.AnimationListener beforeAnimationListener) {
        this.beforeAnimationListener = beforeAnimationListener;return this;
    }

    public Animation.AnimationListener getAfterAnimationListener() {
        return afterAnimationListener;
    }

    public AnimationBuilder setAfterAnimationListener(Animation.AnimationListener afterAnimationListener) {
        this.afterAnimationListener = afterAnimationListener;return this;
    }

    public View getAnimateView() {
        return animateView;
    }

    public AnimationBuilder setAnimateView(View animateView) {
        this.animateView = animateView;return this;
    }

    public View getBeforeAnimateView() {
        return beforeAnimateView;
    }

    public AnimationBuilder setBeforeAnimateView(View beforeAnimateView) {
        this.beforeAnimateView = beforeAnimateView;return this;
    }

    public View getAfterAnimateView() {
        return afterAnimateView;
    }

    public AnimationBuilder setAfterAnimateView(View afterAnimateView) {
        this.afterAnimateView = afterAnimateView;
        return this;
    }
}
