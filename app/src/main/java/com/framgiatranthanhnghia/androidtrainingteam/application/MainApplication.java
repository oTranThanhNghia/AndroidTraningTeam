package com.framgiatranthanhnghia.androidtrainingteam.application;

import android.app.Application;
import android.graphics.Bitmap;

import com.framgiatranthanhnghia.androidtrainingteam.R;
import com.framgiatranthanhnghia.androidtrainingteam.activities.MainActivity;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 25/08/2015.
 */
public class MainApplication extends Application {
    private static MainApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        DisplayImageOptions options = new DisplayImageOptions.Builder()

                .showImageOnLoading(R.drawable.default_image)
                .showImageForEmptyUri(R.drawable.default_image)
                .showImageOnFail(R.drawable.default_image)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPoolSize(2)
                .diskCacheExtraOptions(50, 50, null)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .imageDecoder(new BaseImageDecoder(true))
                .defaultDisplayImageOptions(options)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
    public static MainApplication get(){
        return instance;
    }
}
