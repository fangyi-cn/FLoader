package com.fycmd.imageloader.floaderlib.base;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by fy on 2018/6/8.
 *
 */
public interface Target {
    void onBitmapLoaded(Bitmap bitmap);
    void onBitmapFailed(Drawable drawable);
}
