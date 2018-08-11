package com.fycmd.imageloader.floaderlib.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.fycmd.imageloader.floaderlib.FLoader;
import com.fycmd.imageloader.floaderlib.base.FTransformation;
import com.fycmd.imageloader.floaderlib.base.ILoaderFactory;
import com.fycmd.imageloader.floaderlib.base.LoaderBuilder;

/**
 * Created by fy on 2018/6/9.
 *
 */
public class GlideLoader implements ILoaderFactory {

    @Override
    public void load(final LoaderBuilder builder) {
        if (builder.context == null) {
            throw new RuntimeException("place call 'with(Context context)' first after 'load(..)'");
        }
        RequestBuilder<Drawable> requestBuilder = null;
        if (builder.url != null) {
            requestBuilder = Glide.with(builder.context).load(builder.url);
        } else if (builder.drawableResId != 0) {
            requestBuilder = Glide.with(builder.context).load(builder.drawableResId);
        } else if (builder.file != null) {
            requestBuilder = Glide.with(builder.context).load(builder.file);
        } else if (builder.uri != null) {
            requestBuilder = Glide.with(builder.context).load(builder.uri);
        }
        if (requestBuilder == null) {
            throw new NullPointerException("requestBuilder must not be null");
        }

        RequestOptions options = new RequestOptions();

        if (builder.placeholderResId != 0) {
            options = options.placeholder(builder.placeholderResId);
        } else if (builder.placeholder != null) {
            options = options.placeholder(builder.placeholder);
        }
        if (builder.errorResId != 0) {
            options = options.placeholder(builder.errorResId);
        }
        if (builder.isCenterCrop) {
            options = options.centerCrop();
        } else if (builder.isCenterInside) {
            options = options.centerInside();
        } else if (builder.isfitCenter) {
            options = options.fitCenter();
        }
        if (builder.skipMemCache) {
            options = options.skipMemoryCache(true);
        }
        if (builder.skipLocalCache) {
            options = options.diskCacheStrategy(DiskCacheStrategy.NONE);
        }
        if (builder.bitmapAngle != 0) {
            options = options.transform(new GlideRoundTransform((int) builder.bitmapAngle));
        }
        if (builder.degrees != 0) {
            options = options.transform(new RotateTransformation(builder.context, builder.degrees));
        }
        if (builder.cutHeight != 0 || builder.cutWidth != 0) {
            options = options.override(builder.cutWidth, builder.cutHeight);
        }
        if (builder.transformations != null && !builder.transformations.isEmpty()) {
            for (FTransformation transformation : builder.transformations) {
                if (transformation instanceof Transformation) {
                    options = options.transform((Transformation<Bitmap>) transformation);
                } else {
                    throw new RuntimeException("transformation must be implements 'com.bumptech.glide.load.Transformation'");
                }
            }
        }
        if (builder.usingBlur) {
            options = options.bitmapTransform(new GlideBlurformation(builder.context, builder.blurRadius));
        }
        if (builder.targetView instanceof ImageView) {
            requestBuilder.apply(options).into((ImageView) builder.targetView);
        } else if (builder.target != null) {
            requestBuilder.apply(options).into(new ViewTarget<View, Drawable>(builder.targetView) {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    builder.target.onBitmapLoaded(ImageUtils.drawableToBitmap(resource));
                }
            });
        }
    }

    @Override
    public void clearMemoryCache() {
        Glide.get(FLoader.getInstance().getContext()).clearMemory();
    }

    @Override
    public void clearDiskCache() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(FLoader.getInstance().getContext()).clearDiskCache();  //不能再UI线程调用
            }
        });

    }
}
