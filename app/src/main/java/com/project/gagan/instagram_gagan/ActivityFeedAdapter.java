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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flin on 5/10/15.
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

    public class Holder {
        TextView textView;
        ImageView imageView;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View activityFeedView;
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        //     ImageView imageView;
        //  TextView textView;
        activityFeedView = inflater.inflate(R.layout.activity_feed_layout, null);
        Holder holder = new Holder();
        holder.textView = (TextView) activityFeedView.findViewById(R.id.textViewFeed);
        holder.imageView = (ImageView) activityFeedView.findViewById(R.id.imageViewFeed);

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            // --------------

            //   activityFeedView = inflater.inflate(R.layout.activity_feed_layout, null);
            //     TextView txt = (TextView) activityFeedView.findViewById(R.id.gridView_ActivityFeed);
            // --------------
            holder.imageView = new ImageView(mContext);
            holder.imageView.setLayoutParams(new GridView.LayoutParams(460, 460));
           // holder.textView.setText("User ");

            holder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //   holder.imageView.setPadding(8, 8, 8, 8);


            //  } else {
            //    holder.imageView = (ImageView) convertView;
            //     holder.textView = (TextView) convertView;
        }

        List<String> users = new ArrayList<>();

        users.add(0, "Fenglin");
        users.add(1, "Gagan");
        users.add(2, "Sift");
        users.add(3, "Prasanna");


        holder.imageView.setImageResource(mThumbIds[position]);

        // TODO when the database is connected, each imageView entry and textView entry need to be matched
        holder.textView.setText("\n \n" + users.get(position % 4) + " has uploaded a new photo");
        return activityFeedView;
    }

    private Integer[] mThumbIds = {
            R.mipmap.ic_exampleselfie, R.mipmap.ic_enample,
            R.mipmap.ic_exampleselfie, R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie, R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie, R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie, R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie, R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie, R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie, R.mipmap.ic_exampleselfie,
            R.mipmap.ic_exampleselfie, R.mipmap.ic_exampleselfie,

    };

}
