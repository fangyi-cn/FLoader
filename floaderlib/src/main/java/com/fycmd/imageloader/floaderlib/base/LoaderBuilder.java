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

}
