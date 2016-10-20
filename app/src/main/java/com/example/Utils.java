package com.example;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by yangshirong on 2016/10/20 20:14.
 * 邮箱 ysr200808@163.com
 */
public class Utils {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageResource(com.example.R.mipmap.ic_launcher);
        } else {
            Glide.with(imageView.getContext())
                    .load(url)
                    .placeholder(com.example.R.mipmap.ic_launcher)
                    .error(com.example.R.mipmap.ic_launcher)
                    .into(imageView);
        }
    }
}
