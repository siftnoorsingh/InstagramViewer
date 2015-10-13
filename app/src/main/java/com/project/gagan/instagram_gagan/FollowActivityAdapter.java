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
 * Created by Fenglin on 13/10/2015.
 * This adapter handles the display of the followers' activities in follow_tab
 */
public class FollowActivityAdapter extends ParseQueryAdapter<ParseObject> {

    private ParseUser toUser;
    int i = 0;
    private String queryString;
    private ParseImageView thumbPhotoView;

    public FollowActivityAdapter(final Context context) {


        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {
//
//                ParseQuery followingActivitiesQuery = new ParseQuery("Activity");
//                followingActivitiesQuery.whereMatches("type", "follow");
//                followingActivitiesQuery.whereEqualTo("fromUser", ParseUser.getCurrentUser());
//                // Get the photos from the Users returned in the previous query
//                ParseQuery<Photo> photosFromFollowedUsersQuery = new ParseQuery<Photo>("Photo");
//                photosFromFollowedUsersQuery.whereMatchesKeyInQuery("user", "toUser", followingActivitiesQuery);
//                photosFromFollowedUsersQuery.whereExists("image");


                ParseQuery followingActivitiesQuery2 = new ParseQuery("Activity");
                //     followingActivitiesQuery2.whereMatches("type", "comment");
                followingActivitiesQuery2.whereEqualTo("toUser", ParseUser.getCurrentUser());
                // Get the photos from the Users returned in the previous query
                //ParseQuery<Photo> photosFromFollowedUsersQuery2 = new ParseQuery<Photo>("Photo");
                //  photosFromFollowedUsersQuery2.whereMatchesKeyInQuery("user", "toUser", followingActivitiesQuery2);
                //  photosFromFollowedUsersQuery2.whereExists("image");
//
//
//
//
//
//
//                ParseQuery query = ParseQuery.or(Arrays.asList(photosFromFollowedUsersQuery));
//                query.include("user");
//
//                query.orderByDescending("createdAt");

                return followingActivitiesQuery2;

            }
        });

    }

    @Override
    public View getItemView(ParseObject activity, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.userprofilephotos, null);
        }
        super.getItemView(activity, v, parent);


        // Set up the current user's all uploaded photos
        thumbPhotoView = (ParseImageView) v.findViewById(R.id.icon_thumb);
        final String type = activity.getString("type");
        Log.d(type, type);

        TextView uploadedAt = (TextView) v.findViewById(R.id.timestamp);
        uploadedAt.setText(activity.getCreatedAt().toString());

        // Set up the description
        TextView descriptionImage = (TextView) v.findViewById(R.id.imageDescription);
        if (type.equals("follow")) {
            descriptionImage.setText(type);

        } else if (type.equals("comment")) {
            descriptionImage.setText(type);

        } else if (type.equals("comment")) {
            descriptionImage.setText(type);
        }

//
//        ParseQuery userQuery = new ParseQuery("Activity");
//        ParseQuery users = ParseUser.getQuery();
//        users.whereEqualTo("objectId", userQuery);
//
//        users.findInBackground(new FindCallback<ParseUser>() {
//            @Override
//            public void done(List<ParseUser> list, ParseException e) {
//                if (e == null) {
//                    for (ParseUser p : list) {
//
//                        thumbnail = p.getParseFile("thumbnail");
//                        toUser = p;
//
//                        imageView.setParseFile(thumbnail);
//                        imageView.loadInBackground();
//                        Log.d("111!!!!", thumbnail.getName());
//                    }
//                } else {
//
//                }
//            }
//
//        });

        ParseQuery queryActivity = new ParseQuery("Activity");

//        queryActivity.whereEqualTo("objectId",activity.getObjectId());
//        queryActivity.whereMatchesQuery(queryName);

            //    photosFromFollowedUsersQuery.whereMatchesKeyInQuery("user", "toUser", followingActivitiesQuery);

        // Set up the number of pictures/posts of current users
        final TextView username = (TextView) v.findViewById(R.id.imageDescription);

        return v;
    }
}