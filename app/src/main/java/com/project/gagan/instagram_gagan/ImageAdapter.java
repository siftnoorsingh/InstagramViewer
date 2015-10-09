package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fenglin on 3/10/15.
 * This class provides pictures for the gridView in SearchTab
 */
public class ImageAdapter extends ParseQueryAdapter<ParseObject> {

    public ImageAdapter(Context context) {


        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {

                // Get the current user's photos
                ParseQuery photosFromCurrentUserQuery = new ParseQuery("_User");
                //photosFromCurrentUserQuery.whereEqualTo("user", ParseUser.getCurrentUser());
                photosFromCurrentUserQuery.whereExists("thumbnail");

//                photosFromCurrentUserQuery.include("user");
//                photosFromCurrentUserQuery.orderByDescending("createdAt");

                return photosFromCurrentUserQuery;
            }
        });
    }


    @Override
    public View getItemView(ParseObject photo, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.search_tab_layout, null);
        }

        super.getItemView(photo, v, parent);

        ParseImageView DiscoverPhotoView = (ParseImageView) v.findViewById(R.id.ParseSearchImgView);
        final ParseFile image = photo.getParseFile("thumbnail");
        if (image != null) {

            DiscoverPhotoView.setParseFile(image);

            DiscoverPhotoView.loadInBackground();
        }

        return v;
    }

}
