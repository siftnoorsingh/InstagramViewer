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

import android.util.Log;

/**
 * Created by Fenglin on 10/10/2015.
 */
public class SearchUserAdapter extends ParseQueryAdapter<ParseObject> {

    int i = 0;
    private String queryString;
    private ParseImageView thumbPhotoView;

    public SearchUserAdapter(final Context context, final String userObjectId) {


        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {
                Log.d("userObjectId: ", userObjectId);
                Toast.makeText(context, userObjectId, Toast.LENGTH_SHORT).show();

                ParseQuery users = new ParseQuery("_User");
                users.whereEqualTo("objectId", userObjectId);
                ParseQuery searchResultUserQuery = new ParseQuery("Photo");
                searchResultUserQuery.whereMatchesQuery("user", users);

                //    photosFromCurrentUserQuery.whereEqualTo("userObjId", userObjectId);
//                photosFromCurrentUserQuery.whereContains("username", query);
                searchResultUserQuery.whereExists("image");


                searchResultUserQuery.include("user");
                searchResultUserQuery.orderByDescending("createdAt");


                Log.d("tag1", "Here1");


                return searchResultUserQuery;
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
        Log.d("tag2", "Here2");
        if (v == null) {
            v = View.inflate(getContext(), R.layout.search_result_activity_layout, null);
        }
        super.getItemView(photo, v, parent);


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
        descriptionImage.setText(photo.getString("description"));

        // Set up the number of pictures/posts of current users
        TextView numPosts = (TextView) v.findViewById(R.id.imageDescription);
        numPosts.setText("Posts" + i);


        return v;
    }


}
