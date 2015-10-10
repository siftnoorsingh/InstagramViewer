package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

/**
 * Created by Fenglin on 10/10/2015.
 */
public class SearchUserAdapter extends ParseQueryAdapter<ParseObject> {

    int i = 0;
    private String queryString;

    public SearchUserAdapter(final Context context, final String username) {


        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {

                Toast.makeText(context, username, Toast.LENGTH_SHORT).show();
                // Get the current user's photos
                ParseQuery photosFromCurrentUserQuery = new ParseQuery("Photo");
                photosFromCurrentUserQuery.whereEqualTo("user", "qQwXlllOBV");
//                photosFromCurrentUserQuery.whereContains("username", query);
                photosFromCurrentUserQuery.whereExists("image");


//                try {
//                    i=photosFromCurrentUserQuery.count();
//
//                } catch (ParseException e) {
//                }


                photosFromCurrentUserQuery.include("user");
                photosFromCurrentUserQuery.orderByDescending("createdAt");

                return photosFromCurrentUserQuery;
            }
        });

      //  queryString = query;
    }

    /**
     * This class is overridden to provide a custom view for each item in the
     * User's List View. It sets the user's profile picture, the user name,
     * and then displays the actual photo.
     * <p/>
     * See profile_tab.xml for the layout file
     *
     * @see com.parse.ParseQueryAdapter#getItemView(com.parse.ParseObject, android.view.View, android.view.ViewGroup)
     */

    @Override
    public View getItemView(ParseObject photo, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.userprofilephotos, null);
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

        // Set up the number of pictures/posts of current users
        TextView numPosts = (TextView) v.findViewById(R.id.imageDescription);
        numPosts.setText("Posts" + i);


        return v;
    }


}
