package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import org.xml.sax.helpers.ParserAdapter;

import java.util.List;

/**
 * Created by Fenglin on 11/10/2015.
 */
public class FollowTabAdapter extends ParseQueryAdapter<ParseObject> {


    int i = 0;
    private String queryString;
    private ParseImageView thumbPhotoView;

    public FollowTabAdapter(final Context context) {


        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {
                Log.d("userObjectId: ", ParseUser.getCurrentUser().getObjectId());

                ParseQuery users = new ParseQuery("_User");
                users.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());

                ParseQuery ActivityQuery = new ParseQuery("Activity");
                ActivityQuery.whereMatchesQuery("fromUser", users);


                ParseQuery users3 = new ParseQuery("_User");
                //  users3.whereMatchesQuery("objectId",)


                ActivityQuery.whereMatchesQuery("toUser", users3);


                final ParseQuery users2 = new ParseQuery("_User");
                users2.whereMatchesQuery("ObjectId", ActivityQuery);

                final ParseQuery photoQuery = new ParseQuery("Photo");
                photoQuery.whereMatchesQuery("user", users3);
//

//
//                ParseQuery<ParseObject> query = ParseQuery.getQuery("Activity");
//
//                query.findInBackground(new FindCallback<ParseObject>() {
//                    @Override
//                    public void done(List<ParseObject> list, ParseException e) {
//                        for (ParseObject parseObject : list) {
//                           String s = parseObject.getString("to");
//                            users2.whereEqualTo("objectId", s);
//
//                        }
//                    }
//
//                });
//                photoQuery.whereMatchesQuery("user", users2);

//
//
//                ParseQuery toUser = new ParseQuery("Activity");
//                toUser.whereMatchesQuery("toUser",users2);


                photoQuery.whereExists("image");


                photoQuery.include("user");
                photoQuery.orderByDescending("createdAt");


                Log.d("tag1", "Here1");


                return photoQuery;
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
            v = View.inflate(getContext(), R.layout.userprofilephotos, null);
        }
        super.getItemView(photo, v, parent);


        // Set up the current user's all uploaded photos
        thumbPhotoView = (ParseImageView) v.findViewById(R.id.icon);
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
