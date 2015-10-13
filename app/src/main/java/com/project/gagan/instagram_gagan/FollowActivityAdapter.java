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

                ParseQuery followingActivitiesQuery2 = new ParseQuery("Activity");

                followingActivitiesQuery2.whereEqualTo("toUser", ParseUser.getCurrentUser());


                return followingActivitiesQuery2;

            }
        });

    }

    @Override
    public View getItemView(ParseObject activity, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.follow_bottom_layout, null);
        }
        super.getItemView(activity, v, parent);

        final String type = activity.getString("type");

        TextView uploadedAt = (TextView) v.findViewById(R.id.timestamp_bottom);

        String date = activity.getCreatedAt().toString();
        String dateString = " ";
        String[] tokens = date.split(dateString);
        int timecount = tokens.length;
        String k = "";
        for(int i =0;i<timecount;i++)
        {
            if(i==1)
            {

                k = k+tokens[i];
            }
            else if(i==2||i==(timecount-1))
            {
                k = k+","+tokens[i];
            }

        }

        uploadedAt.setText(k);

        // Set up the description
        TextView descriptionImage = (TextView) v.findViewById(R.id.imageDescription_bottom);

        // get username from the pointer in Activity table
        ParseObject parseObject = ParseObject.create("Activity");
        parseObject = activity.getParseObject("fromUser");
        String u = parseObject.getString("username");


        if (type.equals("follow")) {
            descriptionImage.setText(u + " has started to follow you");

        } else if (type.equals("comment")) {
            descriptionImage.setText(u + " has commented your photo");

        } else if (type.equals("like")) {
            descriptionImage.setText(u + " has liked your photo");
        }


        return v;
    }
}
