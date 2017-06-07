package com.dev.prepcarapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.utilities.TouchImageView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by this on 3/7/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {

    ArrayList<String> alias = null;

    private String nickname;
    Context mcov;

    public ViewPagerAdapter(Context context, ArrayList<String> checkAlias, String nickname) {

        mcov = context;
        this.alias = checkAlias;
        this.nickname = nickname;
    }

    @Override
    public int getCount() {

        Log.e("Sze", alias.size() + "");
        return alias.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    public Bitmap decodeFile(File f) {

        try {
            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            //The new size we want to scale to
            final int REQUIRED_SIZE = 290;

            //Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            //Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {


        LayoutInflater mlAy = (LayoutInflater) mcov
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View m = mlAy.inflate(R.layout.custom_photo, container, false);
        ImageView mimg = (ImageView) m.findViewById(R.id.mainImageFeed);
        TextView tc = (TextView) m.findViewById(R.id.texr);
        String path = alias.get(position);
        if (!nickname.equals("")) {
            tc.setVisibility(View.VISIBLE);
            tc.setText(nickname);
        } else
            tc.setVisibility(View.GONE);
        if (!path.equals("")) {
            Picasso.with(mcov).load(alias.get(position)).placeholder(R.drawable.loading).error(android.R.drawable.ic_delete).into(mimg);
        } else {
            mimg.setImageResource(android.R.drawable.ic_delete);
        }
        mimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showView(alias.get(position));
            }
        });
        container.addView(m);
        return m;
    }

    private void showView(String path) {

        final Dialog dialog = new Dialog(mcov);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.fragment_full_image_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ImageView docView = (TouchImageView) dialog.findViewById(R.id.docView);
        ImageView closeImage = (ImageView) dialog.findViewById(R.id.closeImage);

        if (path.contains("http")) {
            Picasso.with(mcov).load(path)
                    .into(docView);
        } else {
            Bitmap myBitmap = BitmapFactory.decodeFile(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            docView.setImageBitmap(myBitmap);
        }

        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((android.support.v4.view.ViewPager) container).removeView((RelativeLayout) object);

    }
}
