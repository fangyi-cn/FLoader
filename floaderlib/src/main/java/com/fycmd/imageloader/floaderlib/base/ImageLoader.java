package com.fycmd.imageloader.floaderlib.base;

import android.net.Uri;

import java.io.File;

/**
 * Created by fy on 2018/6/8.
 */
public class ImageLoader {
    private ILoaderFactory loaderFactory;
    private static volatile ImageLoader mInstance;

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }

    /**
     * 可以动态设置加载图片的工厂
     *
     * @param loaderFactory 工厂
     */
    public void setLoaderFactory(ILoaderFactory loaderFactory) {
        if (loaderFactory != null) {
            this.loaderFactory = loaderFactory;
        }
    }

    /**
     * @param builder 构造
     */
    protected void intoView(LoaderBuilder builder) {
        loaderFactory.load(builder);
    }

    /**
     * 清内存缓存
     */
    public void clearMemoryCache() {
        loaderFactory.clearMemoryCache();
    }

    /**
     * 清磁盘缓存
     */
    public void clearDiskCache() {
        loaderFactory.clearDiskCache();
    }

    /**
     * @param url url
     * @return builder
     */
    public FLoadBuilder load(String url) {
        return new FLoadBuilder(url);
    }

    /**
     * @param drawableResId 资源
     * @return builder
     */
    public FLoadBuilder load(int drawableResId) {
        return new FLoadBuilder(drawableResId);
    }


    /**
     * @param uri uri
     * @return builder
     */
    public FLoadBuilder load(Uri uri) {
        return new FLoadBuilder(uri);
    }

    /**
     * @param file 文件
     * @return builder
     */
    public FLoadBuilder load(File file) {
        return new FLoadBuilder(file);
    }
}
