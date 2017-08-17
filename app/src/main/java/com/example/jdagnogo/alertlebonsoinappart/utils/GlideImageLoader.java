package com.example.jdagnogo.alertlebonsoinappart.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.veinhorn.scrollgalleryview.loader.MediaLoader;

public class GlideImageLoader implements MediaLoader {
    String url ;
    @Override
    public boolean isImage() {
        return true;
    }

    public GlideImageLoader(String url) {
        this.url = url;
    }

    @Override
    public void loadMedia(Context context, ImageView imageView, SuccessCallback callback) {
        Glide
                .with( context )
                .load( url)
                .asBitmap()
                .centerCrop()
                .thumbnail( 0.1f )
                .into( imageView );
    }

    @Override
    public void loadThumbnail(Context context, ImageView thumbnailView, SuccessCallback callback) {
        Glide
                .with( context )
                .load( url)
                .asBitmap()
                .centerCrop()
                .thumbnail( 0.1f )
                .into( thumbnailView );
    }
}
