package com.example.pc.login.MySql;

import android.content.Context;
import android.widget.ImageView;

import com.example.pc.login.R;
import com.squareup.picasso.Picasso;

public class PicassoClient {

    public static void downloadImage(Context c, String imageUrl, ImageView img) {
        if (imageUrl.length() > 0 && imageUrl != null) {
            Picasso.with(c).load(imageUrl).placeholder(R.mipmap.placeholder).into(img);
        } else {
            Picasso.with(c).load(R.mipmap.placeholder).into(img);
        }
    }

}
