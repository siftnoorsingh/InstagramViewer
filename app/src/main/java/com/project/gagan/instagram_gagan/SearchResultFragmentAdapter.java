package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;


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
 * Created by Fenglin on 10/10/2015.
 * This Adapter takes care of the search result view
 */

public class SearchResultFragmentAdapter extends ParseQueryAdapter<ParseObject> {
    private ParseImageView SearchResult;
    public SearchResultFragmentAdapter(Context context, final String query) {


        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {

                // Get the query user's photos
                ParseQuery photosFromCurrentUserQuery = new ParseQuery("_User");
                photosFromCurrentUserQuery.whereContains("username", query);
                photosFromCurrentUserQuery.whereExists("thumbnail");

                return photosFromCurrentUserQuery;
            }
        });
    }


    @Override
    public View getItemView(final ParseObject photo, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.search_tab_layout, null);
        }

        super.getItemView(photo, v, parent);

        SearchResult = (ParseImageView) v.findViewById(R.id.ParseSearchImgView);
        final ParseFile image = photo.getParseFile("thumbnail");
        if (image != null) {

            SearchResult.setParseFile(image);

            SearchResult.loadInBackground();
        }
        SearchResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),TranActivity.class);
                intent.putExtra("userObjectId",photo.getObjectId());
                getContext().startActivity(intent);

            }
        });

        return v;
    }

}

