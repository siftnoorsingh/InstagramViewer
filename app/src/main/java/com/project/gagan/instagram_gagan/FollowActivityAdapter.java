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

        // Convert the time into a another format
        int timetoken = tokens.length;
        String time = "";
        for (int i = 0; i < timetoken; i++) {
            if (i == 1) {

                time = time + tokens[i];
            } else if (i == 2 || i == (timetoken - 1)) {
                time = time + "," + tokens[i];
            }
        }

        // set time
        uploadedAt.setText(time);

        // set description
        TextView descriptionImage = (TextView) v.findViewById(R.id.imageDescription_bottom);

        // get username from the pointer in Activity table
        ParseObject parseObject = ParseObject.create("Activity");
        parseObject = activity.getParseObject("fromUser");
        String u = parseObject.getString("username");

        // check for each type of activity, show different text
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
