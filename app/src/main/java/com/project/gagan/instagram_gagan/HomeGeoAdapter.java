package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.Arrays;

/**
 * Created by lenovo on 07-10-2015.
 */
public class HomeGeoAdapter extends ParseQueryAdapter<Photo> {

    private static final int MAX_POST_SEARCH_DISTANCE = 100;

    public HomeGeoAdapter(Context c, final ParseGeoPoint myPoint){

        super(c, new ParseQueryAdapter.QueryFactory<Photo>() {

            public ParseQuery<Photo> create() {


                ParseQuery<com.project.gagan.instagram_gagan.Activity> followingActivitiesQuery = new ParseQuery<com.project.gagan.instagram_gagan.Activity>("Activity");
                followingActivitiesQuery.whereMatches("type", "follow");
                followingActivitiesQuery.whereEqualTo("fromUser", ParseUser.getCurrentUser());


                ParseQuery<Photo> photosFromFollowedUsersQuery = new ParseQuery<Photo>("Photo");
                photosFromFollowedUsersQuery.whereMatchesKeyInQuery("user", "toUser", followingActivitiesQuery);
                photosFromFollowedUsersQuery.whereExists("image");

                ParseQuery<Photo> photosFromCurrentUserQuery = new ParseQuery<Photo>("Photo");
                photosFromCurrentUserQuery.whereEqualTo("user", ParseUser.getCurrentUser());
                photosFromCurrentUserQuery.whereExists("image");

               // Location myLoc = (currentLocation == null) ? lastLocation : currentLocation;


                ParseGeoPoint p = myPoint;


                ParseQuery<Photo> query = ParseQuery.or(Arrays.asList(photosFromFollowedUsersQuery, photosFromCurrentUserQuery));
                query.include("user");
                query.whereWithinMiles("location",p,MAX_POST_SEARCH_DISTANCE );

               // query.whereWithinKilometers("location", location, distance);


                query.orderByAscending("createdAt");

                return query;
            }
        });


    }


    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(final Photo object, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.home_photo, null);
        }


        super.getItemView(object, v, parent);


        ///THUMBNAIL
        final ImageView userThumbnail = (ImageView) v.findViewById(R.id.thumbnail);
        ParseUser user = object.getUser();
        ParseFile imageFile = user.getParseFile("thumbnail");
        if (imageFile != null) {
            imageFile.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    int size = 150;
                    if (e == null) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        float scale = (bmp.getWidth() < bmp.getHeight()) ? (float)bmp.getWidth() / (float)size : (float)bmp.getHeight() / (float)size;
                        int width = (int)(bmp.getWidth() / scale);
                        int height = (int)(bmp.getHeight() / scale);
                        userThumbnail.setImageBitmap(Bitmap.createScaledBitmap(bmp,
                                width, height, true));
                    }
                }
            });

        }
        else
        {
            userThumbnail.setImageResource(R.drawable.userplaceholder);
        }

        ////USER NAME

        TextView usernameView = (TextView) v.findViewById(R.id.username);
        usernameView.setText(user.getString("username"));




///FEED PHOTO
        final ImageView feedImage = (ImageView) v.findViewById(R.id.feed_photo);
        ParseFile imageFile2 = object.getImage();
        if(imageFile2!=null)
        {
            imageFile2.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    int size = 500;
                    if (e == null) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        float scale = (bmp.getWidth() < bmp.getHeight()) ? (float) bmp.getWidth() / (float) size : (float) bmp.getHeight() / (float) size;
                        int width = (int) (bmp.getWidth() / scale);
                        int height = (int) (bmp.getHeight() / scale);
                        feedImage.setImageBitmap(Bitmap.createScaledBitmap(bmp,
                                width, height, true));
                    } else {
                        feedImage.setImageResource(R.drawable.placeholder);
                    }
                }
            });

        }
        else
        {
            feedImage.setImageResource(R.drawable.placeholder);
        }


        ////DATE VIEW

        TextView timestampView = (TextView) v.findViewById(R.id.created);
        String date = object.getCreatedAt().toString();
        String delim = " ";
        String[] tokens = date.split(delim);
        int tcount = tokens.length;
        String k = "";
        for(int i =0;i<tcount;i++)
        {
            if(i==1)
            {

                k = k+tokens[i];
            }
            else if(i==2||i==(tcount-1))
            {
                k = k+","+tokens[i];
            }

        }
        timestampView.setText(k);


        ///Like buttton & Likes

        final ImageButton like = (ImageButton)v.findViewById(R.id.like);
        final TextView Likes = (TextView) v.findViewById(R.id.likecount);
        int no_of_likes = object.getInt("likeCount");
        if(no_of_likes!=0)
        {

            if(no_of_likes==1)
            {
                String c = Integer.toString(no_of_likes);
                like.setImageResource(R.drawable.likeplaceholder);

                Likes.setText(c+" like");

            }
            else
            {
                String c = Integer.toString(no_of_likes);
                like.setImageResource(R.drawable.likeplaceholder);

                Likes.setText(c+" likes");

            }
        }
        else
        {
            like.setImageResource(R.drawable.likeplaceholder);
            String c = "";
            Likes.setText(c);
        }

        final String photoId = object.getObjectId();







///LIKE BUTTON
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),Like_Activity.class);
                intent.putExtra("photo",photoId);
                getContext().startActivity(intent);




            }
        });



        ////Comments

        ImageButton comm = (ImageButton)v.findViewById(R.id.comment);
        comm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),Comment_Activity.class);
                intent.putExtra("photo",photoId);
                getContext().startActivity(intent);

            }
        });

        ////LIKES LIST

        Likes.setOnClickListener(new View.OnClickListener()

                                 {
                                     @Override
                                     public void onClick (View v){

                                     }
                                 }

        );


        object.saveInBackground();
        return v;
    }

}
