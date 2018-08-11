package com.fycmd.imageloader.floaderlib.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by fy on 2018/8/11.
 */
public class FLoadBuilder {
    private LoaderBuilder builder;

    public FLoadBuilder(String url) {
        builder = new LoaderBuilder(url);
    }

    public FLoadBuilder(Uri uri) {
        builder = new LoaderBuilder(uri);
    }

    public FLoadBuilder(int drawableResId) {
        builder = new LoaderBuilder(drawableResId);
    }

    public FLoadBuilder(File file) {
        builder = new LoaderBuilder(file);
    }

    /**
     * @param context
     * @return
     */
    public FLoadBuilder with(Context context) {
        builder.context = context;
        return this;
    }

    /**
     * 占位图
     *
     * @param placeholderResId 资源ID
     * @return builder
     */
    public FLoadBuilder placeholder(@DrawableRes int placeholderResId) {
        builder.placeholderResId = placeholderResId;
        return this;
    }

    /**
     * 占位图
     *
     * @param placeholder drawable
     * @return builder
     */
    public FLoadBuilder placeholder(Drawable placeholder) {
        builder.placeholder = placeholder;
        return this;
    }

    /**
     * 加载失败占位图
     *
     * @param errorResId 资源Id
     * @return builder
     */
    public FLoadBuilder error(@DrawableRes int errorResId) {
        builder.errorResId = errorResId;
        return this;
    }

    /**
     * CenterCrop
     *
     * @return builder
     */
    public FLoadBuilder centerCrop() {
        builder.isCenterCrop = true;
        builder.isCenterInside = false;
        builder.isfitCenter = false;
        return this;
    }

    /**
     * CenterInside
     *
     * @return builder
     */
    public FLoadBuilder centerInside() {
        builder.isCenterCrop = false;
        builder.isCenterInside = true;
        builder.isfitCenter = false;
        return this;
    }

    public FLoadBuilder fitCenter() {
        builder.isfitCenter = true;
        builder.isCenterCrop = false;
        builder.isCenterInside = false;
        return this;
    }

    /**
     * 是否缓存本地
     *
     * @param skipLocalCache 缓存
     * @return builder
     */
    public FLoadBuilder skipLocalCache(boolean skipLocalCache) {
        builder.skipLocalCache = skipLocalCache;
        return this;
    }

    /**
     * 是否内存缓存
     *
     * @param skipMemCache 内存缓存
     * @return builder
     */
    public FLoadBuilder skipMemCache(boolean skipMemCache) {
        builder.skipMemCache = skipMemCache;
        return this;
    }

    /**
     * Bitmap Config
     *
     * @param config 配置
     * @return builder
     */
    public FLoadBuilder config(Bitmap.Config config) {
        builder.config = config;
        return this;
    }

    /**
     * 重新指定加载宽高
     *
     * @param cutWidth
     * @param cutHeight
     * @return builder
     */
    public FLoadBuilder resize(int cutWidth, int cutHeight) {
        builder.cutWidth = cutWidth;
        builder.cutHeight = cutHeight;
        return this;
    }

    /**
     * 圆角
     *
     * @param bitmapAngle 圆角半径
     * @return builder
     */
    public FLoadBuilder angle(float bitmapAngle) {
        builder.bitmapAngle = bitmapAngle;
        return this;
    }

    public FLoadBuilder transformation(FTransformation transformation) {
        if (builder.transformations == null) {
            builder.transformations = new ArrayList<>();
        }
        builder.transformations.add(transformation);
        return this;
    }

    /**
     * 旋转
     *
     * @param degrees
     * @return builder
     */
    public FLoadBuilder degrees(float degrees) {
        builder.degrees = degrees;
        return this;
    }

    /**
     * 启用高斯模糊
     *
     * @return builder
     */
    public FLoadBuilder usingBlur() {
        builder.usingBlur = true;
        return this;
    }

    /**
     * 高斯模糊度
     *
     * @param blurRadius
     * @return builder
     */
    public FLoadBuilder blurRadius(float blurRadius) {
        builder.blurRadius = blurRadius;
        return this;
    }

    /**
     * @param target
     */
    public void bitmap(Target target) {
        builder.target = target;
        // TODO: 2018/6/9  ....
        ImageLoader.getInstance().intoView(builder);
    }

    /**
     * @param targetView
     */
    public void into(View targetView) {
        builder.targetView = targetView;
        // TODO: 2018/6/9  ....
        ImageLoader.getInstance().intoView(builder);
    }
}
