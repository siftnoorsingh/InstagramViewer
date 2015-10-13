package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;


import java.util.Arrays;
import java.util.List;

/**
 * Created by Fenglin on 11/10/2015.
 * This adapter handles the display of the following's activities in follow_tab
 */
public class FollowTabAdapter extends ParseQueryAdapter<ParseObject> {

    private ParseUser toUser;
    int i = 0;
    private String queryString;
    private ParseImageView thumbPhotoView;

    public FollowTabAdapter(final Context context) {


        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {

                ParseQuery followingActivitiesQuery = new ParseQuery("Activity");
                followingActivitiesQuery.whereMatches("type", "follow");
                followingActivitiesQuery.whereEqualTo("fromUser", ParseUser.getCurrentUser());
                // Get the photos from the Users returned in the previous query
                ParseQuery<Photo> photosFromFollowedUsersQuery = new ParseQuery<Photo>("Photo");
                photosFromFollowedUsersQuery.whereMatchesKeyInQuery("user", "toUser", followingActivitiesQuery);
                photosFromFollowedUsersQuery.whereExists("image");


                ParseQuery followingActivitiesQuery2 = new ParseQuery("Activity");
                //     followingActivitiesQuery2.whereMatches("type", "comment");
                followingActivitiesQuery2.whereEqualTo("toUser", ParseUser.getCurrentUser());
                // Get the photos from the Users returned in the previous queryx

                ParseQuery query = ParseQuery.or(Arrays.asList(photosFromFollowedUsersQuery));
                query.include("user");

                query.orderByDescending("createdAt");

                return query;

            }
        });

    }

    /**
     * This class is overridden to provide a custom view for each item in the
     * User's List View. It uses userprofilephotos.xml as layout
     *
     * @see com.parse.ParseQueryAdapter#getItemView(com.parse.ParseObject, android.view.View, android.view.ViewGroup)
     */

    @Override
    public View getItemView(ParseObject photo, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.userprofilephotos, null);
        }
        super.getItemView(photo, v, parent);

        ParseObject parseObject = ParseObject.create("Photo");
        parseObject = photo.getParseObject("user");
        String u = parseObject.getString("username");


        // Set up the current user's all uploaded photos
        thumbPhotoView = (ParseImageView) v.findViewById(R.id.icon_thumb);
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
        descriptionImage.setText(u);

        return v;
    }
}
