package com.example.xyzreader.utils;

import android.databinding.BindingAdapter;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xyzreader.R;

@SuppressWarnings("unused")
public class BindingAdaper {
    @BindingAdapter("loadImage")
    public static void loadImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .centerCrop()
                .into(imageView);
    }
}
