package com.fycmd.imageloader.floaderlib.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2018/6/8.
 *
 */
public class LoaderBuilder {

    public Context context;
    public int placeholderResId;
    public int errorResId;
    public boolean isCenterCrop;
    public boolean isCenterInside;
    public boolean isfitCenter;
    public boolean skipLocalCache; //是否跳过缓存到本地（true 不缓存）
    // public boolean skipNetCache;
    public boolean skipMemCache;   //是否跳过内存缓存（true 不缓存）
    public Bitmap.Config config = Bitmap.Config.RGB_565;
    public Target target;
    public String url;
    public File file;
    public int drawableResId;
    public Uri uri;
    public int cutWidth;
    public int cutHeight;
    public float bitmapAngle; //圆角角度
    public float degrees; //旋转角度.注意:picasso针对三星等本地图片，默认旋转回0度，即正常位置。此时不需要自己rotate
    public Drawable placeholder;
    public View targetView;//targetView展示图片

    public boolean usingBlur;
    public float blurRadius = 20f;   //模糊度（25f为最大模糊度）


    public List<FTransformation> transformations;


    public LoaderBuilder(String url) {
        this.url = url;
    }

    public LoaderBuilder(Uri uri) {
        this.uri = uri;
    }

    public LoaderBuilder(int drawableResId) {
        this.drawableResId = drawableResId;
    }

    public LoaderBuilder(File file) {
        this.file = file;
    }

    public LoaderBuilder with(Context context) {
        this.context = context;
        return this;
    }

    /**
     * 占位图
     *
     * @param placeholderResId 资源ID
     * @return builder
     */
    public LoaderBuilder placeholder(@DrawableRes int placeholderResId) {
        this.placeholderResId = placeholderResId;
        return this;
    }

    /**
     * 占位图
     *
     * @param placeholder drawable
     * @return builder
     */
    public LoaderBuilder placeholder(Drawable placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    /**
     * 加载失败占位图
     *
     * @param errorResId  资源Id
     * @return builder
     */
    public LoaderBuilder error(@DrawableRes int errorResId) {
        this.errorResId = errorResId;
        return this;
    }

    /**
     * CenterCrop
     *
     * @return builder
     */
    public LoaderBuilder centerCrop() {
        this.isCenterCrop = true;
        this.isCenterInside = false;
        this.isfitCenter = false;
        return this;
    }

    /**
     * CenterInside
     *
     * @return builder
     */
    public LoaderBuilder centerInside() {
        this.isCenterCrop = false;
        this.isCenterInside = true;
        this.isfitCenter = false;
        return this;
    }

    public LoaderBuilder fitCenter() {
        this.isfitCenter = true;
        this.isCenterCrop = false;
        this.isCenterInside = false;
        return this;
    }

    /**
     * 是否缓存本地
     *
     * @param skipLocalCache 缓存
     * @return builder
     */
    public LoaderBuilder skipLocalCache(boolean skipLocalCache) {
        this.skipLocalCache = skipLocalCache;
        return this;
    }

    /**
     * 是否内存缓存
     *
     * @param skipMemCache 内存缓存
     * @return builder
     */
    public LoaderBuilder skipMemCache(boolean skipMemCache) {
        this.skipMemCache = skipMemCache;
        return this;
    }

    /**
     * Bitmap Config
     *
     * @param config 配置
     * @return builder
     */
    public LoaderBuilder config(Bitmap.Config config) {
        this.config = config;
        return this;
    }

    /**
     * 重新指定加载宽高
     *
     * @param cutWidth
     * @param cutHeight
     * @return builder
     */
    public LoaderBuilder resize(int cutWidth, int cutHeight) {
        this.cutWidth = cutWidth;
        this.cutHeight = cutHeight;
        return this;
    }

    /**
     * 圆角
     *
     * @param bitmapAngle 圆角半径
     * @return builder
     */
    public LoaderBuilder angle(float bitmapAngle) {
        this.bitmapAngle = bitmapAngle;
        return this;
    }

    public LoaderBuilder transformation(FTransformation transformation) {
        if (this.transformations == null) {
            this.transformations = new ArrayList<>();
        }
        this.transformations.add(transformation);
        return this;
    }

    /**
     * 旋转
     *
     * @param degrees
     * @return builder
     */
    public LoaderBuilder degrees(float degrees) {
        this.degrees = degrees;
        return this;
    }

    /**
     * 启用高斯模糊
     *
     * @return builder
     */
    public LoaderBuilder usingBlur() {
        this.usingBlur = true;
        return this;
    }

    /**
     * 高斯模糊度
     *
     * @param blurRadius
     * @return builder
     */
    public LoaderBuilder blurRadius(float blurRadius) {
        this.blurRadius = blurRadius;
        return this;
    }

    /**
     * @param target
     */
    public void bitmap(Target target) {
        this.target = target;
        // TODO: 2018/6/9  ....
        ImageLoader.getInstance().intoView(this);
    }

    /**
     * @param targetView
     */
    public void into(View targetView) {
        this.targetView = targetView;
        // TODO: 2018/6/9  ....
        ImageLoader.getInstance().intoView(this);
    }
}
