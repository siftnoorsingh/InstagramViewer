package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    SearchIDs searchIDs;
    ParseImageView DiscoverPhotoView;

    public ImageAdapter(Context context, SearchIDs searchID) {


        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {

            public ParseQuery<ParseObject> create() {

                // Get the current user's photos
                ParseQuery photosFromCurrentUserQuery = new ParseQuery("_User");

                photosFromCurrentUserQuery.whereExists("thumbnail");

                photosFromCurrentUserQuery.include("user");

                return photosFromCurrentUserQuery;
            }
        });

    }


    @Override
    public View getItemView(final ParseObject photo, View v, final ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.search_tab_layout, null);
        }

        super.getItemView(photo, v, parent);

        DiscoverPhotoView = (ParseImageView) v.findViewById(R.id.ParseSearchImgView);

        final ParseFile image = photo.getParseFile("thumbnail");
        if (image != null) {

            DiscoverPhotoView.setParseFile(image);

            DiscoverPhotoView.loadInBackground();
        }
        DiscoverPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v = View.inflate(getContext(), R.layout.profile_tab, null);

                // start new activity to show searched user profile, passing user's ObjectId and username through intent
                Intent intent = new Intent(v.getContext(), TranActivity.class);
                intent.putExtra("userObjectId", photo.getObjectId());
                intent.putExtra("username", photo.getString("username"));
                // start new activity
                getContext().startActivity(intent);
            }


        });

        return v;

    }
}
