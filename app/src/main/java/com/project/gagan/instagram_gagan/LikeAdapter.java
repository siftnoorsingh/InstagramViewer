package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by lenovo on 13-10-2015.
 */
public class LikeAdapter extends ParseQueryAdapter<Activity> {

    public LikeAdapter(final Context context, final String photo) {
        super(context, new ParseQueryAdapter.QueryFactory<Activity>() {
            public ParseQuery<Activity> create() {


                ParseQuery<Activity> queryPhoto = new ParseQuery<>("Photo");
                queryPhoto.whereEqualTo("objectId", photo);


                ParseQuery<Activity> query = new ParseQuery<>("Activity");
                query.whereMatches("type", "like");
                query.whereMatchesQuery("photo", queryPhoto);
                query.include("fromUser");
                query.orderByDescending("createdAt");
                return query;

            }
        });
    }


    @Override
    public View getItemView(Activity object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.likelist, null);

        }
        super.getItemView(object, v, parent);
        final TextView user = (TextView)v.findViewById(R.id.lusername);

        ParseObject parseObject = ParseObject.create("Activity");
        parseObject = object.getParseObject("fromUser");
        String u = parseObject.getString("username");
        user.setText(u);

        return v;

    }
}
