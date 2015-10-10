package com.project.gagan.instagram_gagan;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

/*
 * The UserViewAdapter is an extension of ParseQueryAdapter
 * that has a custom layout for photos for the current user
 */

public class UserProfileAdapter extends ParseQueryAdapter<ParseObject> {



    public UserProfileAdapter(Context context) {


        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {



                // Get the current user's photos
                ParseQuery photosFromCurrentUserQuery = new ParseQuery("Photo");
                photosFromCurrentUserQuery.whereEqualTo("user", ParseUser.getCurrentUser());
                photosFromCurrentUserQuery.whereExists("image");



                photosFromCurrentUserQuery.include("user");
                photosFromCurrentUserQuery.orderByDescending("createdAt");

                return photosFromCurrentUserQuery;
            }
        });
    }

    /**
     * This class is overridden to provide a custom view for each item in the
     * User's List View. It sets the user's profile picture, the user name,
     * and then displays the actual photo.
     *
     * See profile_tab.xml for the layout file
     *
     * @see com.parse.ParseQueryAdapter#getItemView(com.parse.ParseObject, android.view.View, android.view.ViewGroup)
     */

    @Override
    public View getItemView(ParseObject photo, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.user_profile, null);
        }

        super.getItemView(photo, v, parent);



        // Set up the current user's all uploaded photos
        ParseImageView thumbPhotoView = (ParseImageView) v.findViewById(R.id.icon);
        final ParseFile image = photo.getParseFile("image");
        if (image != null) {
            thumbPhotoView.setParseFile(image);
            thumbPhotoView.loadInBackground();
        }

        // Set up the timestamp
        TextView uploadedAt = (TextView) v.findViewById(R.id.timestamp);
        uploadedAt.setText(photo.getCreatedAt().toString());

        // Set up the description
        TextView descriptionImage = (TextView) v.findViewById(R.id.imageDescription);
        descriptionImage.setText(photo.getString("description"));



        return v;
    }

}