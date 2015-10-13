package com.project.gagan.instagram_gagan;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;
import com.parse.Parse;
import com.parse.ParseImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fenglin on 5/10/2015.
 * This adapter was for local testing only
 * Not used in the final app
 */
public class ActivityFeedAdapter extends BaseAdapter {

    private Context mContext;


    public ActivityFeedAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    // Holder class for the TextView and ImageView
    public class Holder {
        TextView textView;
        ImageView imageView;
    }

    // create a new activityFeedView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View activityFeedView;
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();

        activityFeedView = inflater.inflate(R.layout.activity_feed_layout, null);
        Holder holder = new Holder();
        holder.textView = (TextView) activityFeedView.findViewById(R.id.textViewFeed);
        holder.imageView = (ImageView) activityFeedView.findViewById(R.id.imageViewFeed);

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            holder.imageView = new ImageView(mContext);
            holder.imageView.setLayoutParams(new GridView.LayoutParams(400, 400));

            holder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //   holder.imageView.setPadding(8, 8, 8, 8);


            //  } else {
            //    holder.imageView = (ImageView) convertView;
            //     holder.textView = (TextView) convertView;
        }

        String Name = ParseUser.getCurrentUser().getUsername();

        holder.imageView.setImageResource(mThumbIds[position]);

        holder.textView.setText("\n \n" + Name + " has uploaded a new photo");
        return activityFeedView;
    }

    private Integer[] mThumbIds = {
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_enample,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie,

    };

}
