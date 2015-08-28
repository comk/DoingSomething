package com.mayhub.doingsomething.app;

import android.app.Application;

import com.mayhub.doingsomething.R;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by daihai on 2015/8/27.
 */
public class ApplicationInstance extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.add_slot_add_image)
                .showImageOnFail(R.drawable.add_slot_add_image).showImageForEmptyUri(R.drawable.add_slot_add_image).build();

        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 1).threadPoolSize(2)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))  //修改后增加的 修改日期：2015-03-03 修改人：daihai
                .diskCacheSize(80*1024*1024)
                .diskCacheFileCount(300)
                .denyCacheImageMultipleSizesInMemory()
                .defaultDisplayImageOptions(displayImageOptions)
                .imageDownloader(new BaseImageDownloader(getApplicationContext()))
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);

    }
}
