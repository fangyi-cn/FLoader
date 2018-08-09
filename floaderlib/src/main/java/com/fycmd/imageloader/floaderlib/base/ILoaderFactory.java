package com.fycmd.imageloader.floaderlib.base;


/**
 * Created by fy on 2018/6/9.
 *
 */
public interface ILoaderFactory {

    /**
     * 加载图片
     *
     * @param builder  构造
     */
    void load(LoaderBuilder builder);

    /**
     * 清楚内存缓存
     */
    void clearMemoryCache();

    /**
     * 清楚本地磁盘缓存
     */
    void clearDiskCache();
}
