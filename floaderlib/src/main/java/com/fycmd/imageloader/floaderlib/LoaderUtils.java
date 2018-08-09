package com.fycmd.imageloader.floaderlib;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.fycmd.imageloader.floaderlib.base.ILoaderFactory;
import com.fycmd.imageloader.floaderlib.base.ImageLoader;
import com.fycmd.imageloader.floaderlib.base.LoaderBuilder;

/**
 * Created by fy on 2018/6/9.
 *
 */
public class LoaderUtils {
    private Application application;
    private static volatile LoaderUtils mInstance;

    public static LoaderUtils getInstance() {
        if (mInstance == null) {
            synchronized (LoaderUtils.class) {
                if (mInstance == null) {
                    mInstance = new LoaderUtils();
                }
            }
        }
        return mInstance;
    }
    /**
     * @param application app
     */
    public void init(Application application) {
        this.application = application;
//        ImageLoader.getInstance().setLoaderFactory(new PicassoLoader());
    }

    /**
     * 指定底层加载框架
     *
     * @param loaderFactory 工厂
     */
    public void setLoaderFactory(ILoaderFactory loaderFactory) {
        if (loaderFactory != null) {
            ImageLoader.getInstance().setLoaderFactory(loaderFactory);
        }
    }

    public Context getContext() {
        return application;
    }

    public void loadImage(View view, String path, int placeholderId, int errorId) {
        loadImage(view, path, placeholderId, errorId, false);
    }

    public void loadImage(View view, int drawableResId, int placeholderId, int errorId) {
        loadImage(view, drawableResId, placeholderId, errorId, false);
    }

    public void loadImage(View view, String path, int placeholderId, int errorId, boolean skipMemory) {
        loadImage(view, path, placeholderId, errorId, skipMemory, false);
    }

    public void loadImage(View view, int drawableResId, int placeholderId, int errorId, boolean skipMemory) {
        loadImage(view, drawableResId, placeholderId, errorId, skipMemory, false);
    }

    public void loadImage(View view, String path, int placeholderId, int errorId, boolean skipMemory, boolean skipLocal) {
        loadImage(view, path, placeholderId, errorId, skipMemory, skipLocal, 0, 0);
    }

    public void loadImage(View view, int drawableResId, int placeholderId, int errorId, boolean skipMemory, boolean skipLocal) {
        loadImage(view, drawableResId, placeholderId, errorId, skipMemory, skipLocal, 0, 0);
    }

    public void loadImage(View view, String path, int placeholderId, int errorId, boolean skipMemory, boolean skipLocal, int reWidth, int reHeight) {
        loadImage(view, path, placeholderId, errorId, skipMemory, skipLocal, reWidth, reHeight, 0);
    }

    public void loadImage(View view, int drawableResId, int placeholderId, int errorId, boolean skipMemory, boolean skipLocal, int reWidth, int reHeight) {
        loadImage(view, drawableResId, placeholderId, errorId, skipMemory, skipLocal, reWidth, reHeight, 0);
    }

    public void loadImage(View view, String path, int placeholderId, int errorId, boolean skipMemory, boolean skipLocal, int reWidth, int reHeight, float bitmapAngle) {
        LoaderBuilder builder = ImageLoader.getInstance().load(path).with(view.getContext()).placeholder(placeholderId).error(errorId).centerCrop().skipMemCache(skipMemory).skipLocalCache(skipLocal);
        if (reWidth != 0 && reHeight != 0) {
            builder.resize(reWidth, reHeight);
        }
        if (bitmapAngle != 0) {
            builder.angle(bitmapAngle);
        }
        builder.into(view);
    }

    public void loadImage(View view, int drawableResId, int placeholderId, int errorId, boolean skipMemory, boolean skipLocal, int reWidth, int reHeight, float bitmapAngle) {
        LoaderBuilder builder = ImageLoader.getInstance().load(drawableResId).with(view.getContext()).placeholder(placeholderId).error(errorId).centerCrop().skipMemCache(skipMemory).skipLocalCache(skipLocal);
        if (reWidth != 0 && reHeight != 0) {
            builder.resize(reWidth, reHeight);
        }
        if (bitmapAngle != 0) {
            builder.angle(bitmapAngle);
        }
        builder.into(view);
    }


    /**
     * 自定义（链式调用）
     *
     * @param path 图片地址
     * @return builder
     */
    public LoaderBuilder load(String path) {
        return ImageLoader.getInstance().load(path);
    }
}
